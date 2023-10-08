import React, { Component } from 'react';
import axios from 'axios';
import Subcategory from '../../components/searchResults/subcategory';
import ResultList from '../../components/searchResults/resultList';
import { Product } from '../../interfaces/product.interface';
import { SubCategory } from '../../interfaces/subcategory.interface';

export default class SearchResults extends Component<{searchItem: string}> {
  state = {
    products: [] as Product[],
    subcategories: [] as SubCategory[],
  }

  render() {
    return (
      <div className='SearchResults'>
        <h3>Search Page</h3>
        <br/>
        <Subcategory subcategories={this.state.subcategories}/>
        <ResultList products={this.state.products}/>

      </div>
    )
  }

  async componentDidMount() {
    const search = window.location.search;
    const params = new URLSearchParams(search);
    const searchTerm = params.get('item');

    if (searchTerm !== null && searchTerm !== '') {
      document.title = `Search: ${searchTerm} - SUPERPRICE`;
    }

    await axios.get(`${process.env.REACT_APP_API_URL}/api/search?name=${searchTerm}`)
      .then(res => {
        this.setState({ products: res.data }, this.getSubcategories);        
      })
      .catch(err => {
        console.error('Error while getting seach data: ', err);
      });
  }

  getSubcategories = async () => {
    // Gets all subcategory ids
    const products: Product[] = this.state.products;
    console.log(products);

    const subs_ids: number[] = [];
    for (let p in products) {
      console.log(products[p].subCategoryId);
      subs_ids.push(products[p].subCategoryId);
    }

    // Gets required subcategories
    const subs: SubCategory[] = [];
    for (let i in subs_ids) {
      await axios.get(`${process.env.REACT_APP_API_URL}/api/subcategory/${subs_ids[i]}`)
          .then(res => {
        console.log(res.data);
            subs.push(res.data);
          })
          .catch(err => {
            console.error('Error while getting subcategory: ', err);
          });
    }
    this.setState({ subcategories: subs })
  }
}
