import React, { Component } from 'react';
import axios from 'axios';
import Subcategory from '../../components/searchResults/subcategory';
import ResultList from '../../components/searchResults/resultList';
import { Product } from '../../interfaces/product.interface';
// import { URLSearchParams } from 'url';

export default class SearchResults extends Component<{searchItem: string}> {
  state = {
    products: [] as Product[],
  }

  render() {
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
    const search = window.location.search;
    const params = new URLSearchParams(search);
    const searchTerm = params.get('item');

    await axios.get(`${process.env.REACT_APP_API_URL}/api/search?name=${searchTerm}`)
      .then(res => {
        this.setState({ products: res.data });
      })
      .catch(err => {
        console.error('Error while getting seach data: ', err);
      });

  }
}
