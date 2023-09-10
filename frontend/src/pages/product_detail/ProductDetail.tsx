import React, { Component } from 'react'
import PriceCompare from '../../components/pricecompare/pricecompare';
import ProductImg from '../../components/pricecompare/productimg';
import ProductDetailParagraph from '../../components/productdetail/ProductDetailParagraph';
import ProductReview from '../../components/product_review/ProductReview';
import "./ProductDetail.css"

export default class ProductDetail extends Component{
    render(){
        //Sample Product Detail
        const productName: string = "Milk";
        const description: string = "Whole cream Milk in a 4L container.";
        const price: number = 3.4;
        const imageURL = "#";
        const availability: boolean = true;
        const productReviewIds =[1, 2];


        return (
            <div className='product_detail_container'>
                <div className="flex hstack">
                    <ProductImg/>
                    <PriceCompare/>
                </div>
                <div className="product_detail_body">
                    <div className="product_detail_paragraph">
                        <ProductDetailParagraph/>
                    </div>   
                    <div className="product_detail_reviews">
                        <h1><u>Review & Ratings</u></h1>
                            {
                                productReviewIds.map((reviewId)=>{
                                    return <ProductReview/>;
                                })
                            }
                    </div>
                </div>
            </div>
        )
    }
}