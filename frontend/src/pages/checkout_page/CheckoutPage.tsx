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
import { object } from 'prop-types';


const CheckoutPage = () => {
  const [currUser,  setCurrUser] = useState<user | null>(null); //to store current logged in user
  const [total, setTotal] = useState(0); //to store total value of the prodcuts
  const [cartItems, setCartItems] = useState([] as Cart_Item[]); //to store the list of cartItems
  let paymentMethod: PaymentMethod; //CHECK if it is used??
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState<PaymentMethod | null>(null);
  const [user_address, set_user_address] = useState(null as Address | null);
  const [prodcuts, setProducts] = useState(null as Product[] | null);
  const [display, setDisp] = useState(null as any);
  const navigate = useNavigate();

  // For Address input
  const [address1, setAddress1] = useState(null as string | null);
  const [address2, setAddress2] = useState(null as string | null);
  const [city, setCity] = useState(null as string | null);
  const [state, setState] = useState(null as string | null);
  const [country, setCountry] = useState(null as string | null);
  const [postcode, setPostcode] = useState(null as string | null);

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
    const updateCartItems = async ()=>{cartItems.map((item)=>{
      //TEMP CODE: Comment this before uncommenting below code
      axios.delete(`${process.env.REACT_APP_API_URL}/api/cartItems/${item.id}`)

      // ERROR COMMENTED: Here in axios.put() there is some CORS Policy Issue
      axios.put(`${process.env.REACT_APP_API_URL}/api/productDetail/${item.productDetailsId.id}/update-quantity?newQuantity=${item.productDetailsId.available - item.quantity}`)
      .then((response)=>{
        //  axios.delete(`${process.env.REACT_APP_API_URL}/api/cartItems/${item.id}`)
        addNotif();
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

  function addNotif() {
    const notif = {
      user: currUser,
      message: `Your order of $${total.toFixed(2)} has been confirmed.`,
      type: 'ORDER',
    }
    axios.post(`${process.env.REACT_APP_API_URL}/api/notifications`, notif);
  }


  //handle payment method change
  const handlePaymentMethodChange = (event: any) => {
    setSelectedPaymentMethod(event.target.value);
  };

  //calculate total of the items
  function calculateTotal(){
    let tmp_total = 0;
    for(const item of cartItems){
      tmp_total += (item.quantity * item.productDetailsId.price);
    }
    setTotal(tmp_total)
  }
  
  //fetch data
  async function fetchData(){
    
    // fetch user address
    await axios.get<Address>(`${process.env.REACT_APP_API_URL}/api/address/${currUser?.addressId}`)
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
                    console.error('Error from Fetching Cart Items by UserId:', error);
                });
  }

  //setup user
  async function setupUser(){
    const token = window.sessionStorage.getItem('userToken');

    if (token === undefined || token === null) {
      window.location.href = `/login?next=${window.location.href}`;
      return;
    }
    
    await axios.post(`${process.env.REACT_APP_API_URL}/api/auth/validate`, { token: token })
    .then(res => {setCurrUser(res.data)})
    .catch(err => {
      console.log(err);
      window.location.href = `/login?next=${window.location.href}`;
    });
  }

  useEffect(()=>{
    setupUser();
  },[]);

  useEffect(()=>{
    if(currUser){
      fetchData();
    }
  }, [currUser]);

  useEffect(()=>{
    if(currUser){
      getProds();
      calculateTotal();
    }
  },[cartItems]);

  useEffect(()=>{
    if(currUser){
      const dispmap = () => {
          //List of products in the cart
        if (prodcuts == null) return;
        return cartItems.map((item, index) => (
          // Render each item here
          <tr key={item?.id}>
            <td>{prodcuts[index]?.productName} </td>
            <td>{item?.productDetailsId?.store.storeName}</td>
            <td>${item?.productDetailsId?.price.toFixed(2)}</td>
            <td>{item?.quantity}</td>
            <td>${(item?.quantity * item?.productDetailsId?.price).toFixed(2)}</td>
          </tr>
        ))
      }
      setDisp(dispmap());
    }
  },[prodcuts]);

  async function getProds() {
    const pros = await Promise.all(cartItems.map(async (cart_item) => {
      const productResponse = await axios.get<Product>(`${process.env.REACT_APP_API_URL}/api/products/${cart_item.productId}`);
      return productResponse.data;
    }));
    setProducts(pros);
  };

  function saveAddress(e: React.FormEvent) {
    e.preventDefault();

    const object = {
      addressLine1: address1,
      addressLine2: address2,
      city: city,
      state: state,
      postalCode: postcode,
      country: country,
      addressType: AddressType.USER
    }

    axios.put(`${process.env.REACT_APP_API_URL}/api/users/address/${currUser?.userId}`, object)
    .then(res => {
      window.location.reload();
    })
    .catch(err => {
      console.error(err);
    });
  }

  return (
    <>
    <h1>CheckoutPage</h1>
    <div className='main-container'>
      <div className='left-container'>

        {/* Address */}
        <div className='address_container'>
          <div className='add-address-container'>
            <h3>Delivery Address</h3>
            <div>
              <button type="button" id='review-button' className="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#address">Add New Address</button>
              <div className="modal fade" id="address" tabIndex={-1} aria-labelledby="reviewLabel" aria-hidden="true">
                  <div className="modal-dialog modal-dialog-centered">
                      <div className="modal-content">
                      <div className="modal-header">
                          <h1 className="modal-title fs-5" id="address-modal-title">Add New Address</h1>
                          <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                      </div>
                      <div className="modal-body">
                          <form id='address-form' onSubmit={saveAddress}>
                              <label id='Add1-lable' className="form-label" htmlFor='Add1-text' >Address Line 1</label>
                              <input className="form-control" type='text' name='comment' id='Add1-text'  placeholder='Address line 1' required onChange={e => {setAddress1(e.target.value)}} />
                              <br/>
                              <label id='Add2-lable' className="form-label" htmlFor='Add2-text'>Address Line 2</label>
                              <input className="form-control" type='text' name='comment' id='Add2-text'  placeholder='Address line 2' onChange={e => {setAddress2(e.target.value)}} />
                              <br/>
                              <label id='city-lable' className="form-label" htmlFor='city-text'>City</label>
                              <input className="form-control" type='text' name='comment' id='city-text'  placeholder='City' required onChange={e => {setCity(e.target.value)}} />
                              <br/>
                              <label id='post-code-lable' className="form-label" htmlFor='post-code-text'>Post Code</label>
                              <input className="form-control" type='text' name='comment' id='post-code-text'  placeholder='Post Code' required onChange={e => {setPostcode(e.target.value)}} />
                              <br/>
                              <label id='state-lable' className="form-label" htmlFor='state-text'>State</label>
                              <input className="form-control" type='text' name='comment' id='state-text'  placeholder='State' required onChange={e => {setState(e.target.value)}} />
                              <br/>
                              <label id='country-lable' className="form-label" htmlFor='country-text'>Country</label>
                              <input className="form-control" type='text' name='comment' id='country-text'  placeholder='Country' required onChange={e => {setCountry(e.target.value)}} />
                          </form>
                      </div>
                      <div className="modal-footer">
                          <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                          <button type="submit" className="btn btn-primary" form='address-form'>Save Address</button>
                      </div>
                      </div>
                  </div>
              </div>
            </div>
          </div>
          {/* Check how to use conditional rendering here */}
          {user_address == null ? (
            <h3>No Address Provided</h3>
          ):
          (
            <div style={{ whiteSpace: 'pre-line' }}>
              {(user_address?.addressLine2) ? 
               `${user_address?.addressLine1},
                ${user_address?.addressLine2},
                ${user_address?.city}, ${user_address?.state},
                ${user_address?.postalCode}
                ${user_address?.country}` : 
               `${user_address?.addressLine1},
                ${user_address?.city}, ${user_address?.state},
                ${user_address?.postalCode}
                ${user_address?.country}`}
            </div>
          )}
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
                <th>Store</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              {
                display
              }
            </tbody>
          </Table>
      </div>


      <div className='right-container'>
        {/* Total[On the Right Side] */}
        <div>
          <h3>Order Summary</h3>
          <h4>Total: ${total.toFixed(2)}</h4>
          <Button variant="primary" onClick={handleSubmit} disabled={user_address === null || selectedPaymentMethod === null}>Purchase</Button>
          {user_address === null ? (
            <p className='text-danger fw-semibold'>Add Address to continue purchase.</p>
          ):
          (
            <div>
              {selectedPaymentMethod === null ? (
                <p className='text-danger fw-semibold'>Select payment method.</p>
              ):
              (
                <div/>
              )}
            </div>
          )}
        </div>
        <hr/>
      </div>
    </div>

    </>
      
  )
}

export default CheckoutPage