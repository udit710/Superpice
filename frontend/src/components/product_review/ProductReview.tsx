import { Component } from "react";
import "./ProductReview.css"
export default class ProductReview extends Component{
    render(){

        //creating Fake Review
        

        return(
            <div className='product_review_container' data-testid="productreview">
                <div className="review_body">
                    <h4>Person Name</h4>
                    <p>Lorem ipsum dolor sit amet, cons commodo consequat. Duis 
                        aute irure dolor in reprehenderit in voluptate velit esse 
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat
                         cupidatat non proident, sunt in culpa qui officia deserunt 
                         mollit anim id est laborum.</p>
                    <hr/>
                </div>
            </div>
        )
    }
}