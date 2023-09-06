import React, { Component } from 'react'
import Subcategory from '../components/searchResults/subcategory'

export default class SearchResults extends Component {
  render() {
    return (
      <div className='SearchResults'>
        <h3>Search Page</h3>
        <br/>
        <Subcategory/>
        
      </div>
    )
  }
}
