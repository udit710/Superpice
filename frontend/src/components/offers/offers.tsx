import React, { Component } from 'react'
import './offers.css';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from 'react-router-dom';


export default class Offers extends Component {
    state = {
        offerList: [] as number[]
    };
    offerList = [20, 50 , 70];
    render() {
        const {offerList} = this.state;
        return (
            <div className='offers'>
                <h1>Ongoing Offers:</h1>
                <div className='offer-container'>
                    
                    { 
                        this.offerList.map((discount: number) => (
                            <Card style={{ width: '18rem' }}>
                                <Card.Body>
                                    <Card.Title><h2>Offer: {discount}%</h2></Card.Title>
                                    <Link to={`/#`}><Button variant="primary">Show Offer</Button></Link>
                                </Card.Body>
                            </Card>
                        ))
                    }
                </div>
            </div>

        )
    }
}
