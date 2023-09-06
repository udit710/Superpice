// import React from 'react';
// import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';
import { Route, Routes } from 'react-router';


import './App.css';
import Navbar from './components/navbar/navbar';
import ProductDetail from './pages/product_detail/ProductDetail';


function App() {
  return (
    <div className="App">
      <Navbar/>
      <div className='space'></div>
      
      {/**IDK how to do next.js routing so i did this for the time being */}
      <Routes>
        <Route path='/ProductDetail/:id' element={<ProductDetail/>}/>
      </Routes>
      
    </div>
  );
}

export default App;
