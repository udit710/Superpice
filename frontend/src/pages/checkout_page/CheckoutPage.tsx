import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Address, { AddressType } from '../../interfaces/address.interface';
import { Cart_Item } from '../../interfaces/cart_item.interface';
import Product_And_Quantity from '../../interfaces/product_and_quantity.interface';
import { OrderStatus, PaymentMethod } from '../../interfaces/order.interface';
import { Product } from '../../interfaces/product.interface';
import './CheckoutPage.css'
import Form from 'react-bootstrap/Form';
import { Button, Table } from 'react-bootstrap';
import { user } from '../../interfaces/user.interface';
import { Product_Details } from '../../interfaces/product_details.interface';
import { useNavigate } from 'react-router';


const CheckoutPage = () => {
  const [currUser,  setCurrUser] = useState<user | null>(null); //to store current logged in user
  const [total, setTotal] = useState(0); //to store total value of the prodcuts
  const [cartItems, setCartItems] = useState([] as Cart_Item[]); //to store the list of cartItems
  let paymentMethod: PaymentMethod; //CHECK if it is used??
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState<PaymentMethod | null>(null);
  const [user_address, set_user_address] = useState<Address | null>(null);
  const navigate = useNavigate();

  //handle order submition
  const handleSubmit = async (event: any) => {
    //1. add final order to ORDER Table.
    await axios.post(`${process.env.REACT_APP_API_URL}/api/orders`, {
      userId: currUser?.userId,
      orderDate: new Date(),
      orderStatus: OrderStatus.PENDING,
      totalAmount: total,
      orderAddressId: currUser?.addressId,
      paymentMethod: selectedPaymentMethod,
    })
    console.log("FINISHED: Creating a new Order")

    //2. reduce item quantity
    const updateCartItems = async ()=>{cartItems.map(async (item)=>{
      await axios.put(`${process.env.REACT_APP_API_URL}/api/product-details/${item.product_id}/update-quantity?newQuantity=${item.quantity}`)
      .then((response)=>{
         axios.delete(`${process.env.REACT_APP_API_URL}/api/cartItems/${item.cart_item_id}`)
      })
      .catch((error)=>{
        console.error("Error updating product details:", error);
      })
    })}
    await updateCartItems();
    console.log("FINISHED: Reducing quantity in Product_Detail & Deleted Items from the CartItem")

    setCartItems([]);
    console.log("Cleared: Cleared Cart Item")

    //4. show alert that order is submitted
    localStorage.setItem('showSubmitedPopUp', 'true');

    //5. redirect to home-page
    navigate("/");
    console.log("NAVIGATED: to homepage")
  };

  //handle payment method change
  const handlePaymentMethodChange = (event: any) => {
    setSelectedPaymentMethod(event.target.value);
  };

  //calculate total of the items
  function calculateTotal(){
    let tmp_total = 0;
    for(const item of cartItems){
      tmp_total += (item.quantity * item.product_id.price);
    }
    setTotal(tmp_total)
  }
  
  //fetch data
  async function fetchData(){
    
    // fetch user address
    await axios.get<Address>(`${process.env.REACT_APP_API_URL}/api/address/${currUser?.userId}`)
    .then((response)=>{
      set_user_address(response.data);
    })
    .catch((error)=>{
      console.error('Error fetching address using user id: ', error)
    });
     
    //Fetch Cart_Items for a UserID
    await axios.get<Cart_Item[]>(`${process.env.REACT_APP_API_URL}/api/cartItems/userId/${currUser?.userId}`)
                .then((response) => {setCartItems(response.data)})
                .catch ((error)=>{
                    console.error('Error from Fetching Cart Items by UserId:', error)
                });
  }

  //setup user
  async function setupUser(){
    const token = window.sessionStorage.getItem('userToken');

    if (token === undefined || token === null) return;
    
    await axios.post(`${process.env.REACT_APP_API_URL}/api/auth/validate`, { token: token })
    .then(res => {setCurrUser(res.data)})
    .catch(err => {
      console.log(err);
    });
  }

  useEffect(()=>{
    setupUser();
  },[]);

  useEffect(()=>{
    fetchData();
  }, [currUser]);

  useEffect(()=>{
    calculateTotal();
  },[cartItems]);

  return (
    <>
    <h1>CheckoutPage</h1>
    <div className='main-container'>
      <div className='left-container'>

        {/* Address */}
        <div className='address_container'>
          <h3>Delivery Address</h3>
          {/* Check how to use conditional rendering here */}
          <div style={{ whiteSpace: 'pre-line' }}>
            {`${user_address?.addressLine1},
              ${user_address?.addressLine2},
              ${user_address?.city}, ${user_address?.country},
              ${user_address?.postalCode}`}
          </div>
        </div>
        <hr/>

        {/* Select Payment Method */}
        <h3>Payment Method</h3>
          <Form>
            {Object.values(PaymentMethod).map((method) => (
              <div key={`default-${method}`} className="mb-3">
                <Form.Check 
                  name='payment method'
                  type='radio'
                  id={`default-${method}`}
                  label={`${method}`}
                  value={method}
                  onChange={handlePaymentMethodChange}
                />
              </div>
            ))}
          </Form>

        <hr/>
        {/* Items Selected */}
        <h3>Items</h3>
          <Table striped bordered hover>
            <thead>
              <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              {
                //List of products in the cart
                cartItems.map((item) => (
                  // Render each item here
                  <tr key={item.cart_item_id}>
                    <td>{item.product_id.product.productName} </td>
                    <td>${item.product_id.price}</td>
                    <td>{item.quantity}</td>
                    <td>${item.quantity * item.product_id.price}</td>
                  </tr>
                ))

              }
            </tbody>
          </Table>
      </div>


      <div className='right-container'>
        {/* Total[On the Right Side] */}
        <div>
          <h3>Order Summary</h3>
          <h4>Total: {total}</h4>
          <Button variant="primary" onClick={handleSubmit}>Submit</Button>
        </div>
        <hr/>
      </div>
    </div>

    </>
      
  )
}

export default CheckoutPage