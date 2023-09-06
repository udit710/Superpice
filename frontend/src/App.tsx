// import React from 'react';
// import logo from './logo.svg';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';

import './App.css';
import Navbar from './components/navbar/navbar';

import SearchResults from './pages/searchResults';
import ResultList from './components/searchResults/resultList';


function App() {
  return (
    <div className="App">
      <Navbar/>
      <SearchResults/>
      <ResultList/>
    </div>
  );
}

export default App;
