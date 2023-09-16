import axios from 'axios';
import React, { Component } from 'react'
import { Product } from '../../interfaces/product.interface';
import Card from 'react-bootstrap/Card';
import "bootstrap/dist/css/bootstrap.min.css";
import "./offerList.css"

export default class OfferList extends Component {
    state = {
        product: [] as Product[]
    };

    componentDidMount() {
        const urlList = window.location.href.split("/");
        const discount = urlList[urlList.length - 1];
        //Change Endpoint to get all the products with a particular discount
        axios.get(`http://localhost:8080/api/products/${discount}`)
            .then(response => {
                this.setState({ product: response.data });
            })
            .catch(error => console.error('Error fetching product data:', error));
    }

    render() {
        const { product } = this.state;
        return (
            <>
                <h3>Offer List: </h3>
                <div className="offerlist_container">
                    {
                        product.map(p => (
                            <Card style={{ width: '18rem' }}>
                                <Card.Img variant="top" src="holder.js/100px180" />
                                <Card.Body>
                                    <Card.Title>{p.productName}</Card.Title>
                                    <Card.Text>
                                        {p.description}
                                    </Card.Text>
                                </Card.Body>
                            </Card>
                        ))
                    }
                </div>
            </>
        )
    }
}
