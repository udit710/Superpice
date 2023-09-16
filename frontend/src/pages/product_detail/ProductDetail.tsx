import React, { Component } from 'react';
import axios from 'axios';
import "./ProductDetail.css";
import ProductReview from '../../components/product_review/ProductReview';

interface Product {
    productName: string;
    description: string;
    details: {store: {storeName: string}; price: number; available: number; discount: number }[];
    images: { imageUrl: string }[];
}

interface Review {
    reviewId: number;
    userId: number;
    rating: number;
    comment: string;
}

export default class ProductDetail extends Component {
    state = {
        product: null as Product | null,
        reviews: [] as Review[]
    };

    componentDidMount() {
        // const urlList = window.location.href.split("/");
        // const productId = urlList[urlList.length - 1];
        const productId = 1;
        axios.get(`http://localhost:8080/api/products/${productId}`)
            .then(response => {
                this.setState({ product: response.data });
            })
            .catch(error => console.error('Error fetching product data:', error));

        axios.get(`http://localhost:8080/api/reviews?product=${productId}`)
            .then(response => {
                this.setState({ reviews: response.data });
            })
            .catch(error => console.error('Error fetching reviews:', error));
    }

    render() {
        const { product, reviews } = this.state;

        if (!product) {
            return <div className="alert alert-danger">No such product</div>;
        }

        const productName:string = product.productName;
        const description:string = product.description;
        const price:number = product.details[0]?.price || 0;
        const availability:boolean = product.details.some(detail => detail.available > 0);


        return (
            <div className="container mt-5">
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
                                </tr>
                            </thead>
                            <tbody>
                                {product.details.map((detail, index) => (
                                    <tr key={index}>
                                        <td>{detail.store.storeName}</td>
                                        <td>{detail.discount*100}%</td>
                                        <td>${detail.price.toFixed(2)}</td>
                                        <td>{detail.available}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <div className="mt-5 col-md-8 offset-md-2">
                            <h2>Reviews & Ratings</h2>
                            {reviews.map(review => (
                                <ProductReview id = {review.reviewId} 
                                rating = {review.rating} 
                                body = {review.comment}/>
                            ))}
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
