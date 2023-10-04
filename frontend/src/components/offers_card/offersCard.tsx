import React from 'react'
import { Card } from 'react-bootstrap'
import { Product } from '../../interfaces/product.interface'
import './offersCard.css';

export default function OffersCard(props: {product: Product, maxIndex: number}) {
  return (
    <div className='OffersCard'>
        <Card className="card_container" style={{ width: '18rem' }}>
            <a href={`ProductDetail/${props.product.id}`} className="link-style">
                <Card.Img variant="top" src={ props.product.images[0].imageUrl }  style={{width: '100%', height: '250px'}}/>
                <Card.Body>
                    <Card.Title>{ props.product.productName } ({ props.product.details[props.maxIndex].discount }%)</Card.Title>
                    <Card.Text>{ props.product.description }</Card.Text>
                </Card.Body>
            </a>
        </Card>
    </div>
  )
}
