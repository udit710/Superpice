import { Component } from "react";
import axios from "axios";
import { BsThreeDotsVertical } from "react-icons/bs";
import "./ProductReview.css"
import { user } from "../../interfaces/user.interface";

interface ProductReviewProps {
    id: number;
    rating: number;
    body: string;
    user: number;
    isUser: boolean;
}

export default class ProductReview extends Component<ProductReviewProps>{
    state = {
        review: null as string | null,
        username: null as string | null,
    };

    render() {
        const {id, rating, body, isUser} = this.props;

        return (
            <div key={id} className="card mb-3" data-testid="productreview">
                <div className="card-body">
                    <p className="card-user-name">By { this.state.username } {isUser ? "(You)" : ""}</p>
                    <h5 className="card-title" data-testid="rating" >Rating: {rating}/5</h5>
                    <p className="card-text" data-testid="comment" >{body}</p>

                    
                </div>

                {isUser ? (
                    <div className="dropdown menu">
                            <a href="/#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                <BsThreeDotsVertical size={20}/>
                            </a>

                            <ul className="dropdown-menu">
                                <li><a className="dropdown-item" href="/#" data-bs-toggle="modal" data-bs-target="#edit-review">Edit</a></li>
                                <li><a className="dropdown-item" href="/#" onClick={this.onDelete}>Delete</a></li>

                            </ul>


                            <div className="modal fade" id="edit-review" tabIndex={-1} aria-labelledby="reviewLabel" aria-hidden="true">
                                    <div className="modal-dialog modal-dialog-centered">
                                        <div className="modal-content">
                                        <div className="modal-header">
                                            <h1 className="modal-title fs-5" id="edit-review-modal-title">Edit Review</h1>
                                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div className="modal-body">
                                            <form id='edit-review-form' onSubmit={this.onUpdate}>
                                                <label id='edit-review-lable' className="form-label" htmlFor='review-text'>New Review</label>
                                                <input className="form-control" type='text' name='comment' id='review-text' onChange={this.handleChangeText} placeholder='New Review' required />
                                            </form>
                                        </div>
                                        <div className="modal-footer">
                                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button type="submit" className="btn btn-primary" form='edit-review-form'>Save Review</button>
                                        </div>
                                        </div>
                                    </div>
                                </div>
                        </div>
                ):
                (<div />)}
            </div>
        )
    }

    handleChangeText = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({ review: e.target.value });
    }

    onDelete = (e: React.MouseEvent) => {
        e.preventDefault();
        axios.delete(`${process.env.REACT_APP_API_URL}/api/reviews/${this.props.id}`)
        .then(res => {
            window.location.reload();
        });
    }

    onUpdate = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        axios.put(`${process.env.REACT_APP_API_URL}/api/reviews/${this.props.id}`, { comment: this.state.review })
        .then(res => {
            window.location.reload();
        });
    }

    componentDidMount() {
        axios.get(`${process.env.REACT_APP_API_URL}/api/users/${this.props.user}`)
        .then(res => {
            const user: user = res.data;
            this.setState({ username: user.username})
        });
    }
}