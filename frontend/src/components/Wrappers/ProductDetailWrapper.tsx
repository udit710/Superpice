import React from "react";
import { useParams } from "react-router";
import ProductDetail from "../../pages/product_detail/ProductDetail";


function ProductDetailWrapper(){
    const {id} = useParams();
    return (
        <ProductDetail id = {id}/>
    )
}
export default ProductDetailWrapper