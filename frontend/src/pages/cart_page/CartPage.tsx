import React from "react";
import './CartPage.css';
import { Button } from 'react-bootstrap';
import { Cart_Item } from "../../interfaces/cart_item.interface";
import axios from 'axios';
import { Product } from "../../interfaces/product.interface";

export default class CartPage extends React.Component {
  state = {
    cart_items: [] as Cart_Item[],
    product: [] as Product[],
  };

  componentDidMount() {
      axios.get<Cart_Item[]>(`${process.env.REACT_APP_API_URL}/api/cartItems`)
      .then(response => {
          this.setState({ cart_items: response.data });
      }
      ).catch(error => console.error('Error fetching cart items:', error));
      let product_ids = this.state.cart_items.map((cart_item) => cart_item.productDetailsId.id);
      let sets = new Set(product_ids);
      let unique_product_ids = Array.from(sets);
      axios.get<Product[]>(`${process.env.REACT_APP_API_URL}/api/products/${unique_product_ids}`)
      .then(response => {
          this.setState({ product: response.data });
      }
      ).catch(error => console.error('Error fetching products:', error));
  }


  render() {
    const { cart_items, product } = this.state;
    // var products = product.map((product) => product.id);
    // console.log("products: ",products)

    const listItems = cart_items.map((cart_item) =>
      <tr>
        {/* <td>{products.}</td> */}
        <td>{cart_item.productDetailsId.store.storeName}</td>
        <td>${cart_item.productDetailsId.price}</td>
        <td>{cart_item.quantity}</td>
        <td><Button variant="danger">Remove</Button></td>
      </tr>
    );

    return (
        <>
        <div className="container">
          <h1>My Cart</h1>
          <ul>
            {cart_items.length > 0 ? listItems : <h2>Cart is empty</h2>}
          </ul>
    
        </div>
        </>
      );
  }
}