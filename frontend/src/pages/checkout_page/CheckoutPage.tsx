import axios from 'axios';
import React, { useEffect, useState } from 'react'
import Address, { AddressType } from '../../interfaces/address.interface';
import { Cart_Item } from '../../interfaces/cart_item.interface';
import Product_And_Quantity from '../../interfaces/product_and_quantity.interface';
import { PaymentMethod } from '../../interfaces/order.interface';
import { Product } from '../../interfaces/product.interface';
import './CheckoutPage.css'
import Form from 'react-bootstrap/Form';
import { Button, Table } from 'react-bootstrap';


const CheckoutPage = () => {
  const user_id: number = 1; //get user id
  const [total, setTotal] = useState(0); //to store total value
  const [product_and_quantity, set_product_and_quantity] = useState([] as Product_And_Quantity[]);
  let paymentMethod: PaymentMethod;
  const [selectedPaymentMethod, setSelectedPaymentMethod] = useState('');
  const [user_address, set_user_address] = useState<Address | null>(null);

  //TEMP Variable to store products
  let product_list: Product[];

  //handle order submittion
  const handleSubmit = (event: any) => {
    //1. add final order to ORDER Table.
    //2. show alert that order is submitted
    //3. reduce item quantity
    //4. remove items from the cart
    //3. redirect to home-page
    console.log('order submitted');
  };

  //handle payment method change
  const handlePaymentMethodChange = (event: any) => {
    setSelectedPaymentMethod(event.target.value);
  };

  //get minimum price of an item from all its variant
  function getMinimumPriceForItem(item: Product_And_Quantity): number{
    let min_price: number = Number.MAX_SAFE_INTEGER;
      for(const detail of item.product.details){
        min_price = (detail.price < min_price)? detail.price : min_price;
      }
      return min_price
  }
  //calculate total of the items
  function calculateTotal(){
    let tmp_total = 0;
    for(const item of product_and_quantity){
      tmp_total += (item.quantity * getMinimumPriceForItem(item));
    }
    setTotal(tmp_total)
  }
  
  //fetch data
  async function fetchData(){
    //fetch address
    // await axios.get<Address>(`${process.env.REACT_APP_API_URL}/api/user/address/${user_id}`)
    // .then((response)=>{
    //   user_address = response.data;
    // })
    // .catch((error)=>{
    //   console.error('Error fetching address using user id: ', error)
    // });
    //TEMP: user addresse
    set_user_address({ 
      id: 1,
      addressLine1: '715 Sydney Rd',
      addressLine2: 'Brunswick',
      city: 'Melbourne',
      state: 'Victoria',
      postalCode: '3056',
      country: 'Australia',
      addressType: AddressType.STORE
    });
      

    //fetch cart items list
    // await axios.get<Product_And_Quantity[]>(`${process.env.REACT_APP_API_URL}/api/cart/${user_id}`)
    // .then((response)=>{
    //   product_and_quantity = response.data;
    // })
    // .catch((error)=>{
    //   console.error('Error fetching all cart items using user id: ', error)
    // });

    //TEMP CART ITEMS
    await axios.get<Product[]>(`${process.env.REACT_APP_API_URL}/api/products/offer/50`)
                .then((response) => {product_list = response.data})
                .catch ((error)=>{
                    console.error('Error fetching temp product data at checkout:', error)
                });

    set_product_and_quantity([
        {
          product: product_list[0],
          quantity: 2
        },
        {
          product: product_list[1],
          quantity: 2
        }
      ]
    )
  }

  
  // async function setupPage(){
  //   await fetchData();
  //   calculateTotal();
  // }

  useEffect(()=>{
    // setupPage();
    fetchData();

  },[]);

  useEffect(()=>{
    // setupPage();
    // fetchData();
    calculateTotal();
  },[product_and_quantity]);

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
                <Form.Check // prettier-ignore
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
                // use table here instead to create structure
                product_and_quantity ? (
                  product_and_quantity.map((item) => (
                    // Render each item here
                    <tr key={item.product.id}>
                      <td>{item.product.productName} </td>
                      <td>{getMinimumPriceForItem(item)}</td>
                      <td>{item.quantity}</td>
                      <td>{item.quantity * getMinimumPriceForItem(item)}</td>
                    </tr>
                  ))
                ) : (
                  // Render a loading indicator or placeholder content here
                  <div>Loading Items...</div>
                )
              }
            </tbody>
          </Table>
      </div>


      <div className='right-container'>
        {/* Total[On the Right Side] */}
        <div>
          <h3>Order Summary</h3>
          <h4>Total: {total}</h4>
          <Button variant="primary">Submit</Button>
        </div>
        <hr/>
      </div>
    </div>

    </>
      
  )
}

export default CheckoutPage