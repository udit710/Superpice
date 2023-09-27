import React, { Component } from 'react'
import './offers.css';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from 'react-router-dom';


export default function Offers(){
    const offerList = [20, 50 , 70];
    return (
        <>
            <div className='offers'>
                <h1>Ongoing Offers:</h1>
                <div className='offer-container'>
                    
                    { 
                        offerList.map((discount: number) => (
                            <Card key = {discount} style={{ width: '18rem' }}>
                                <Card.Body>
                                    <Card.Title><h2>Offer: {discount}%</h2></Card.Title>
                                    <Link to={`/offer/${discount}`}><Button variant="primary">Show Offer</Button></Link>
                                </Card.Body>
                            </Card>
                        ))
                    }
                </div>
            </div>
        </>

    )
}
