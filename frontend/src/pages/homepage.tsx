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
        localStorage.setItem('showSubmitedPopUp', 'true');
        setShowPopUp('false');
      }, 5000)
    }
  },[])

  return (
    <div className='home-header'>
      {showPopUp === 'true' && <div style={{width: '200px', height:'100px', backgroundColor: 'Green'}}><h1>Order Submitted</h1></div>}
      <h1>SUPERPRICE</h1>
      <h5>Your place for shopping smart</h5>
      <OfferList data-testid='offerlist' />
    </div>
  )
}
