// import React from 'react';
// import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';
import { Route, Routes } from 'react-router';


import './App.css';
import Navbar from './components/navbar/navbar';
import Footer from './components/footer/footer';
import Homepage from './pages/homepage';

import ProductDetail from './pages/product_detail/ProductDetail';
import SearchResults from './pages/searchResults/searchResults';
import OfferList from './components/offerList/offerList';
import CheckoutPage from './pages/checkout_page/CheckoutPage';
// import ResultList from './components/searchResults/resultList';


function App() {
  return (
    <div className="App">
        <div className='pg-content'>
      <Navbar/>
      {/* <div className='space'></div> */}

      {/**IMPORTANT: Also need to add path to index.tsx */}
      {/**See index.tsx for mor info */}
        <Routes>
          <Route path='/ProductDetail/:id' element={<ProductDetail/>}/>
          <Route path='/search' element={<SearchResults searchItem=''/>} />
          <Route path='/' element={<Homepage/>}>
            <Route path="/offer/:discount" element={<OfferList/>}/>
          </Route>
          <Route path="/checkout-page" element={<CheckoutPage/>}></Route>
        </Routes>
      </div>

      <Footer/>     
       
    </div>
  );
}

export default App;
