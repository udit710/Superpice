import Offers from '../components/offers/offers';
import './homepage.css';
import { Outlet } from 'react-router';

export default function Homepage(){
  return (
    <div className='home-header'>
      <h1>SUPERPRICE</h1>
      <h5>Your place for shopping smart</h5>
      <Offers/>
      <Outlet/>
    </div>
  )
}
