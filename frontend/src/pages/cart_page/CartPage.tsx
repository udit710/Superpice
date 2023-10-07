import React from 'react';
import Table from 'react-bootstrap/Table';
import { Cart_Item } from "../../interfaces/cart_item.interface";
import axios from 'axios';
import { Product } from "../../interfaces/product.interface";
import './CartPage.css';
import { BsTrash3Fill } from 'react-icons/bs';
import cartId_and_addressId  from '../../interfaces/cartId_and_addressId.interface';
import { user } from '../../interfaces/user.interface';
class CartPage extends React.Component {
  state = {
    cart_items: [] as Cart_Item[],
    products: [] as Product[],
    isLogedIn: false as boolean,
    userId: null as number | null,
  };

  // constructor(props: cartId_and_addressId[]) {
  //   super(props);

  // }
  componentDidMount() {
    this.getUserLoggedIn();
  }

  getUserLoggedIn = () => {
    const token = window.sessionStorage.getItem('userToken');

    if (token === undefined || token === null) {
      window.location.href = `/login?next=${window.location.href}`;
      return;
    }

    let currUser: user;
    axios.post(`${process.env.REACT_APP_API_URL}/api/auth/validate`, { token: token })
    .then(res => {
        currUser = res.data;
        // console.log(currUser.username);
        this.setState({ isLogedIn: true });
        this.setState({ userId: currUser.userId }, () => {this.get_cart_items()});
    })
    .catch(err => {
        console.log(err);
        window.location.href = `/login?next=${window.location.href}`;
    });
}

   async get_cart_items() {    

    try {
      let cart_items;
      await axios.get(`${process.env.REACT_APP_API_URL}/api/cartItems/userId/${this.state.userId}`)
      .then(res => {
        console.log(res.data);
        cart_items = res.data;
        this.setState({ cart_items: res.data }, () => {getProducts()});
      });

      const getProducts = async () => {
        const products = await Promise.all(this.state.cart_items.map(async (cart_item) => {
          const productResponse = await axios.get<Product>(`${process.env.REACT_APP_API_URL}/api/products/${cart_item.productId}`);
          return productResponse.data;
        }));
      
      
        this.setState({ products: products });
      }
    
    } catch (error) {
      console.error('Error fetching cart items and products:', error);
    }
  }
    
  async putChangeQuantity(cartItem: Cart_Item, change: string) {
    let new_cart_item = { ...cartItem, quantity: cartItem.quantity + 1 };
    if (change === '-') {
    new_cart_item = { ...cartItem, quantity: cartItem.quantity - 1 };
    }
    console.log('New cart item:', new_cart_item);
    axios
      .put<Cart_Item>(`${process.env.REACT_APP_API_URL}/api/cartItems/${cartItem.id}`, new_cart_item)
      .then((response) => {
        const updated_cart_item = response.data;
        // console.log('Updated cart item:', updated_cart_item);
        const cart_items = this.state.cart_items.map((item) => {
          if (item.id === updated_cart_item.id) {
            return updated_cart_item;
          } else {
            return item;
          }
        });
        this.setState({ cart_items });
      })
      .catch((error) => {
        console.error('Error updating cart item:', error);
      });
  }

  async deleteCartItem(cartItem: Cart_Item) {
    axios
      .delete(`${process.env.REACT_APP_API_URL}/api/cartItems/${cartItem.id}`)
      .then((response) => {
        // console.log('Deleted cart item:', cartItem);
        const cart_items = this.state.cart_items.filter((item) => item.id !== cartItem.id);
        this.setState({ cart_items });
      })
      .catch((error) => {
        console.error('Error deleting cart item:', error);
      });
  }
  
  

  
  render() {
    
    const { cart_items, products } = this.state;
    console.log("products: ", products);
    
    // function getProduct(productId:number) {
    //   return products.find(product => product.id === productId);
    // }

    const listItems = cart_items.map((cart_item, i) =>
    <tr key={i}>
        <td width={10}>{i + 1}</td>
        <td width={10}><img src={this.state.products[i]?.images[0]?.imageUrl} alt={products.at(i)?.productName} className='product-img'></img></td>
        <td width={10}>
        <a href={'/ProductDetail/' + products[i]?.id} className='product-name' >
          {products[i]?.productName} &rarr;
        </a>
      </td>
        <td width={10}>{cart_item.productDetailsId.store.storeName}</td>
        {cart_item.productDetailsId.original_price !== cart_item.productDetailsId.price ? (
          <td width={10}> <a className='og-price'>${cart_item.productDetailsId.original_price.toFixed(2)}</a> ${cart_item.productDetailsId.price.toFixed(2)}</td>
        ):
        (
          <td width={10}>${cart_item.productDetailsId.price.toFixed(2)}</td>
        )}
        <td width={10}> 
          <button className='changeQty' onClick={() => this.putChangeQuantity(cart_item,"-")} disabled={cart_item.quantity <= 1} > - </button> 
            {cart_item.quantity} 
          <button className='changeQty' onClick={() => this.putChangeQuantity(cart_item,"+")} disabled={cart_item.quantity >= cart_item.productDetailsId.available}> + </button>
          <button className='trash' onClick={() => this.deleteCartItem(cart_item)}>  <BsTrash3Fill /> </button> 
        </td>
        <td width={10}>${(cart_item.productDetailsId.price*cart_item.quantity).toFixed(2)}</td>
      </tr>
    );

    function calculateTotal(){
      let tmp_total = 0;
      for(const item of cart_items){
        tmp_total += (item.quantity * item.productDetailsId.price);
      }
      return tmp_total.toFixed(2);
    }

    if (cart_items.length === 0) {
      return <h2 data-testid='cart-title'>Your cart is empty</h2>
    }else{
      return (
          <>
          <div className="container">
            <h1 className='my_cart'>My Cart</h1>
            <Table striped bordered hover>
              <thead>
                <tr>
                  <th>#</th>
                  <th>Image</th>
                  <th>Product</th>
                  <th>Store</th>
                  <th>Price (per unit)</th>
                  <th>Quantity</th>
                  <th>Total</th>
                </tr>
              </thead>
              <tbody>
                {/* {this.state.products[0].images[0].imageUrl} */}
                {listItems}
              </tbody>
            </Table>
            <div className='subtotal'>SubTotal : ${calculateTotal()}</div>
            <div className='checkout'>
              <a href='/checkout-page' className='checkout'> Checkout &rarr; </a>
            </div>
          </div>
          </>
        );
      }
    }
}

export default CartPage;