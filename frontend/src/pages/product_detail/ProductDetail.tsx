import React, { Component } from 'react';
// import PriceCompare from '../../components/pricecompare/pricecompare';
// import ProductImg from '../../components/pricecompare/productimg';
// import ProductDetailParagraph from '../../components/productdetail/ProductDetailParagraph';
// import ProductReview from '../../components/product_review/ProductReview';
import axios from 'axios';
import "./ProductDetail.css";

interface Product {
    productName: string;
    description: string;
    details: {store: {storeName: string}; price: number; available: number }[];
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
        // const { id } = useParams();
        const productId = 1;// || this.props.match.params.id;
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
        const imageURL:string = product.images[0]?.imageUrl || "#";
        // const count:string = product.details.some(detail => detail.available > 0);
        const availability:boolean = product.details.some(detail => detail.available > 0);


        return (
            <div className="container mt-5">
                <div className="row">
                    <div className="col-md-6">
                        <img src={imageURL} alt={productName} className="img-fluid" />
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
                                <div key={review.reviewId} className="card mb-3" data-testid="productreview">
                                    <div className="card-body">
                                        <h5 className="card-title">Rating: {review.rating}/5</h5>
                                        <p className="card-text">{review.comment}</p>
                                    </div>
                                </div>
                            ))}
                </div>
            </div>
        );
    }
}
