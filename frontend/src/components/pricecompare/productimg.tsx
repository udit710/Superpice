import React from "react";
import "./productimg.css";
import productImage1 from "../../assets/product1.png";
import productImage2 from "../../assets/product2.png";

export default function ProductImg() {
    return (
        <div className="ProductImg">
            <div className="container">
                <img className="nutrition" src={productImage2} alt="nutrition-facts" />
                <img className="imgx" src={productImage1} alt="product"/>
            </div>
        </div> 
    )
}