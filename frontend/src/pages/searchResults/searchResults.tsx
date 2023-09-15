import React, { Component } from 'react';
import axios from 'axios';
import Subcategory from '../../components/searchResults/subcategory';
import ResultList from '../../components/searchResults/resultList';


export interface Product {
  id: number;
  productName: string;
  description: string;
  details: {store: {storeName: string}; price: number; available: number; discount: number }[];
  images: { imageUrl: string }[];
}

export default class SearchResults extends Component<{searchItem: string}> {
  state = {
    products: [] as Product[],  
  }

  render() {
    // console.log("testing " + this.props.searchItem);
    // this.loadProductsList('milk'); // Get from url
    return (
      <div className='SearchResults'>
        <h3>Search Page</h3>
        <br/>
        <Subcategory/>
        <ResultList products={this.state.products}/>
        
      </div>
    )
  }

  async componentDidMount() {
    const searchTerm:string  = 'milk';
    await axios.get(`http://localhost:8080/api/search?name=${searchTerm}`)
      .then(res => {
        // console.log(res.data);
        this.setState({ products: res.data });
      })
      .catch(err => {
        console.error('Error while getting seach data: ', err);
      });

  }
}
