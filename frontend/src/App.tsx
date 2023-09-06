// import React from 'react';
// import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';

import './App.css';
import Navbar from './components/navbar/navbar';
import Footer from './components/footer/footer';
import Homepage from './pages/homepage';


function App() {
  return (
    <div className="App">
      <Navbar/>
      <Footer/>
      <Homepage/>
    </div>
  );
}

export default App;
