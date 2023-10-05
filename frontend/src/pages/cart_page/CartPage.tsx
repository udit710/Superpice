import React from 'react';
import Table from 'react-bootstrap/Table';
import { Cart_Item } from "../../interfaces/cart_item.interface";
import axios from 'axios';
import { Product } from "../../interfaces/product.interface";
import './CartPage.css';

export default class CartPage extends React.Component {
  state = {
    cart_items: [] as Cart_Item[],
    products: [] as Product[],
  };

  async componentDidMount() {
    try {
      const cartItemsResponse = await axios.get<Cart_Item[]>(`${process.env.REACT_APP_API_URL}/api/cartItems`);
      const cart_items = cartItemsResponse.data;
      this.setState({ cart_items });

      const products = await Promise.all(cart_items.map(async (cart_item) => {
        const productResponse = await axios.get<Product>(`${process.env.REACT_APP_API_URL}/api/products/${cart_item.productDetailsId.id}`);
        return productResponse.data;
      }));
      this.setState({ products });
    } catch (error) {
      console.error('Error fetching cart items and products:', error);
    }
  }

  
  render() {
    const { cart_items, products } = this.state;
    console.log("products: ", products);
    
    function getProduct(productId:number) {
      return products.find(product => product.id === productId);
    }

    const listItems = cart_items.map((cart_item, i) =>
    <tr key={i}>
        <td width={10}>{i + 1}</td>
        <td width={10}><img src={getProduct(cart_item.productDetailsId.id)?.images[0].imageUrl} alt={getProduct(cart_item.productDetailsId.id)?.productName} className='product-img'></img></td>
        <td width={10}>
        <a href={'/ProductDetail/' + getProduct(cart_item.productDetailsId.id)?.id} className='product-name' >
          {getProduct(cart_item.productDetailsId.id)?.productName} &rarr;
        </a>
      </td>
        <td width={10}>{cart_item.productDetailsId.store.storeName}</td>
        <td width={10}> <a className='og-price'>${cart_item.productDetailsId.original_price}</a> ${cart_item.productDetailsId.price}</td>
        <td width={10}>{cart_item.quantity}</td>
        <td width={10}>${cart_item.productDetailsId.price*cart_item.quantity}</td>
      </tr>
    );

    if (cart_items.length === 0) {
      return <h2>Your cart is empty</h2>
    }else{
      return (
          <>
          <div className="container">
            <h1>My Cart</h1>
            <Table striped bordered hover>
              <thead>
                <tr>
                  <th>#</th>
                  <th>Image</th>
                  <th>Product</th>
                  <th>Store</th>
                  <th>Price</th>
                  <th>Quantity</th>
                  <th>Total</th>
                </tr>
              </thead>
              <tbody>
                {listItems}
              </tbody>
            </Table>
          </div>
          
          </>
        );
      }
    }
}
