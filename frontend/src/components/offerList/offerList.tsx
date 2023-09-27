import axios from 'axios';
import React, { Component, useEffect, useState } from 'react'
import { Product } from '../../interfaces/product.interface';
import Card from 'react-bootstrap/Card';
import "bootstrap/dist/css/bootstrap.min.css";
import "./offerList.css"
import { useParams } from 'react-router';

export default function OfferList() {
    let {discount} = useParams();
    discount = discount?.substring(1);
    const [product, setProduct] = useState<Product[]>([]);

    useEffect(() => {
        // Define an asynchronous function inside useEffect
        async function fetchData() {
          try {
            // Change Endpoint to get all the products with a particular discount
            const response = await axios.get<Product[]>(`${process.env.REACT_APP_API_URL}/api/products/offer/${discount}`);
            setProduct(response.data);
          } catch (error) {
            console.error('Error fetching product data:', error);
          }
        }
      
        // Call the async function
        fetchData();
      }, [discount]); 
    
    return (
        <>  
            <hr/>
            <h2>Offer List: </h2>
            <div className="offerlist_container">
                {
                    product.map(p => (
                        <Card style={{ width: '18rem' }}>
                            <Card.Img variant="top" src={p.images[0].imageUrl}  style={{width: '100%', height: '250px'}}/>
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
