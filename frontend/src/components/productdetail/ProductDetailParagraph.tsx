import {Component} from 'react'
import "./ProductDetailParagraph.css"

export default class ProductDetailParagraph extends Component{
    render(){
        return(
            <div className='product_detail_paragraph_container' data-testid="pdp">
                <div>
                    <h1><u>Product Detail</u></h1>
                    <p>Whole cream Milk is a 4L contianer</p>
                </div>
                <div>
                    <h1><u>Allergens</u></h1>
                    <p>Milk, Lactose</p>
                </div>
                <div>
                    <h1><u>Ingredients</u></h1>
                    <p>Whole cream Milk, emulsifiers 690, Salt</p>
                </div>
                <div>
                    <h1><u>Storage Instructions</u></h1>
                    <p>Keep refrigerated at or below 5C. 
                        Do not freeze. Once opened, consume within 5 days</p>
                </div>
            </div>
        )
    }
}