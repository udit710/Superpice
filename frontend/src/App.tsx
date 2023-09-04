// import React from 'react';
// import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';

import './App.css';
import Navbar from './components/navbar/navbar';
import PriceCompare from './components/pricecompare/pricecompare';
import ProductImg from './components/pricecompare/productimg';


function App() {
  return (
    <div className="App">
      <Navbar/>
      <div className='space'></div>
      <div className="flex hstack">
      <ProductImg/>
      <PriceCompare/>
      </div>
    </div>
  );
}

export default App;
