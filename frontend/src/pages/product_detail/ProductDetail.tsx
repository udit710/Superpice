import React, { Component } from 'react';
import axios from 'axios';
import { Product } from '../../interfaces/product.interface';
import "./ProductDetail.css";
import ProductReview from '../../components/product_review/ProductReview';
import { user } from '../../interfaces/user.interface';
import { Cart_Item } from '../../interfaces/cart_item.interface';
import cartId_and_addressId  from '../../interfaces/cartId_and_addressId.interface';
import { Link } from 'react-router-dom';
import '../cart_page/CartPage';
import CartPage from '../cart_page/CartPage';

// interface Product {
//     productName: string;
//     description: string;
//     details: {store: {storeName: string}; price: number; available: number; discount: number }[];
//     images: { imageUrl: string }[];
// }

interface Review {
    reviewId: number;
    userId: number;
    rating: number;
    comment: string;
}

export default class ProductDetail extends Component {
    state = {
        product: null as Product | null,
        reviews: [] as Review[],
        review: null as string | null,
        rating: 1 as number,
        user: null as string | null,
        isLogedIn: false as boolean,
        userId: null as number | null,
        listItems : [] as cartId_and_addressId[],
        stores : [] as number[],
    };

    componentDidMount() {
        this.getUserLoggedIn();

        const urlList = window.location.href.split("/");
        const productId = urlList[urlList.length - 1];
        console.log("ProductID: " + productId);
        //productId is 1
        axios.get(`${process.env.REACT_APP_API_URL}/api/products/${productId}`)
            .then(response => {
                this.setState({ product: response.data });
            })
            .catch(error => console.error('Error fetching product data:', error));
        console.log("Value of product: " + this.state.product);
        axios.get(`${process.env.REACT_APP_API_URL}/api/reviews?product=${productId}`)
            .then(response => {
                this.setState({ reviews: response.data });
            })
            .catch(error => console.error('Error fetching reviews:', error));

    }

    getUserLoggedIn = () => {
        const token = window.sessionStorage.getItem('userToken');

        if (token === undefined || token === null) return;

        let currUser: user;
        axios.post(`${process.env.REACT_APP_API_URL}/api/auth/validate`, { token: token })
        .then(res => {
            currUser = res.data;
            console.log(currUser.username);
            this.setState({ user: currUser.username });
            this.setState({ isLogedIn: true });
            this.setState({ userId: currUser.userId });
        })
        .catch(err => {
            console.log(err);
        });
    }

    render() {
        
        const { product, reviews } = this.state;

        if (!product) {
            return <div data-testid="product-details" className="alert alert-danger">No such product</div>;
        }

        const productName:string = product.productName;
        const description:string = product.description;
        const price:number = product.details[0]?.price || 0;
        const availability:boolean = product.details.some(detail => detail.available > 0);


        return (

            <div data-testid="product-details" className="container mt-5">
                <div className="row">
                    <div className="col-md-4">
                        <div id="productCarousel" className="carousel slide carousel-container" data-ride="carousel" data-testid="productimg">
                            <div className="carousel-inner">
                                {product.images.map((image, index) => (
                                    <div className={`carousel-item ${index === 0 ? 'active' : ''}`}>
                                        <img src={image.imageUrl} className="d-block w-100 carousel-image" alt={productName} />
                                    </div>
                                ))}
                            </div>
                        </div>
                    </div>
                    <div className="col-md-8">
                        <h1>{productName}</h1>
                        <p>{description}</p>
                        <p className="lead">Price: ${price.toFixed(2)}</p>
                        <p>Availability: <span className={availability ? "text-success" : "text-danger"}>{availability ? "In Stock" : "Out of Stock"}</span></p>
                        <table className="table table-hover" data-testid="pricecompare">
                            <thead>
                                <tr>
                                    <th>Shop</th>
                                    <th>Discount</th>
                                    <th>Price</th>
                                    <th>Availability</th>
                                    <th>Quantity</th>
                                    <th>Add</th>
                                </tr>
                            </thead>
                            <tbody>
                                {product.details.map((detail, index) => (
                                    <tr key={index}>

                                        <td>{detail.store.storeName}</td>
                                        <td>{detail.discount}%</td>
                                        <td>${detail.price.toFixed(2)}</td>
                                        <td>{detail.available}</td>
                                        <td><input id='quantity' type="number" className="form-control" defaultValue={1} min={1} max={detail.available} /></td>
                                        {/* {this.state.isLogedIn ? ( */}
                                            <td><button className="btn btn-primary" onClick={() => this.addToCart(product,index, detail)}>Add to Cart</button></td>
                                        {/* ) : (
                                            <p><a href='/login'>Login</a> to add to cart</p>
                                        )} */}
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <div className="mt-5 col-md-8 offset-md-2">
                            <h2>Reviews & Ratings</h2>
                            {this.state.isLogedIn ? (
                                <div>
                                    <button type="button" id='review-button' className="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#review">Add Review</button>
                                    <div className="modal fade" id="review" tabIndex={-1} aria-labelledby="reviewLabel" aria-hidden="true">
                                        <div className="modal-dialog modal-dialog-centered">
                                            <div className="modal-content">
                                            <div className="modal-header">
                                                <h1 className="modal-title fs-5" id="review-modal-title">Add Review for {this.state.product?.productName}</h1>
                                                <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                            </div>
                                            <div className="modal-body">
                                                <form id='review-form' onSubmit={this.postReview}>
                                                    <p className="form-label">Rating</p>
                                                    <div className="form-check form-check-inline">
                                                        <input className="form-check-input" type="radio" name="rating" value={1} onChange={this.handleChangeNum} />
                                                        <label className="form-check-label" htmlFor="inlineRadio1">1</label>
                                                    </div>
                                                    <div className="form-check form-check-inline">
                                                        <input className="form-check-input" type="radio" name="rating" value={2} onChange={this.handleChangeNum} />
                                                        <label className="form-check-label" htmlFor="inlineRadio2">2</label>
                                                    </div>
                                                    <div className="form-check form-check-inline">
                                                        <input className="form-check-input" type="radio" name="rating" value={3} onChange={this.handleChangeNum} />
                                                        <label className="form-check-label" htmlFor="inlineRadio3">3</label>
                                                    </div>
                                                    <div className="form-check form-check-inline">
                                                        <input className="form-check-input" type="radio" name="rating" value={4} onChange={this.handleChangeNum} />
                                                        <label className="form-check-label" htmlFor="inlineRadio3">4</label>
                                                    </div>
                                                    <div className="form-check form-check-inline">
                                                        <input className="form-check-input" type="radio" name="rating" value={5} onChange={this.handleChangeNum} />
                                                        <label className="form-check-label" htmlFor="inlineRadio3">5</label>
                                                    </div>
                                                    
                                                    <br/>
                                                    <label id='review-lable' className="form-label" htmlFor='review-text'>Review</label>
                                                    <input className="form-control" type='text' name='comment' id='review-text' onChange={this.handleChangeText} placeholder='Review' required />
                                                </form>
                                            </div>
                                            <div className="modal-footer">
                                                <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                <button type="submit" className="btn btn-primary" form='review-form'>Add Review</button>
                                            </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            ): 
                            (
                                <p><a href='/login'>Login</a> to add reviews</p>
                            )}
                            {reviews.map(review => (
                                <ProductReview id = {review.reviewId}
                                rating = {review.rating}
                                body = {review.comment}
                                user = {review.userId} 
                                isUser = {this.state.userId === review.userId} />
                            ))}
                    </div>
                </div>
            </div>
        </div>
        );
    }
    
    handleChangeText = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({ review: e.target.value });
    }
    handleChangeNum = (e: React.ChangeEvent<HTMLInputElement>) => {
        this.setState({ rating: e.target.value });
    }

    postReview = (e: React.FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        const rev = {
            userId: this.state.userId,
            productId: this.state.product?.id,
            rating: this.state.rating,
            comment: this.state.review,
        }
        axios.post(`${process.env.REACT_APP_API_URL}/api/reviews`, rev)
        .then(res => {
                window.location.href = `/ProductDetail/${this.state.product?.id}`;
        });
    }

    async addToCart(product: Product, index: number, detail: any) {
        const productId = this.state.product?.id;
        const userId = this.state.userId;
        let quantity = (document.getElementById("quantity") as HTMLInputElement).value;
        if (Number(quantity) <= 1){ quantity = '1'}else if (Number(quantity) > product.details[index].available){ quantity = product.details[index].available.toString()};
        axios.post(`${process.env.REACT_APP_API_URL}/api/cartItems`, { userId: userId, productDetailsId: product, quantity: quantity})
            .then(res => {
                console.log(res);
            })
            .catch(err => {
                console.log(err);
            });
    
        try {
            const cartItemsResponse = await axios.get<Cart_Item[]>(`${process.env.REACT_APP_API_URL}/api/cartItems`);
            const cart_items = cartItemsResponse.data;

            const cartId_and_addressId: cartId_and_addressId = {
                cartId: cart_items[-1].id,
                addressId: index,
            }

            this.setState({ listItems: [...this.state.listItems, cartId_and_addressId] });



        } catch (error) {
            console.error('Error fetching cart items:', error);
        }
    }
}
