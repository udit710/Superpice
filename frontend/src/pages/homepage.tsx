import { useEffect, useState } from 'react';
import OfferList from '../components/offerList/offerList';
import Offers from '../components/offers/offers';
import './homepage.css';
import { Outlet } from 'react-router';

export default function Homepage(){
  const [showPopUp, setShowPopUp] = useState('false');

  useEffect(()=>{
    if(localStorage.getItem('showSubmitedPopUp') === 'true'){
      setShowPopUp('true');
      setTimeout(()=>{
        localStorage.setItem('showSubmitedPopUp', 'false');
        setShowPopUp('false');
      }, 2000)
    }
    return ()=>{
      localStorage.removeItem('showSubmitedPopUp')
    }
  },[])

  return (
    <div className='home-header'>
      {showPopUp === 'true' && <div className=' alert alert-success order-submited'><h2>Order Submitted</h2></div>}
      <h1>SUPERPRICE</h1>
      <h5>Your place for shopping smart</h5>
      <OfferList data-testid='offerlist' />
    </div>
  )
}
