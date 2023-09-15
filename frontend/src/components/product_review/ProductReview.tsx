import { Component } from "react";
import "./ProductReview.css"

interface ProductReviewProps {
    id: number;
    rating: number;
    body: string;
}

export default class ProductReview extends Component<ProductReviewProps>{

    render() {
        const {id, rating, body} = this.props;

        return (
            <div key={id} className="card mb-3" data-testid="productreview">
                <div className="card-body">
                    <h5 className="card-title">Rating: {rating}/5</h5>
                    <p className="card-text">{body}</p>
                </div>
            </div>
        )
    }
}