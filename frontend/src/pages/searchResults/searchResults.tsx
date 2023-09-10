import React, { Component } from 'react'
import Subcategory from '../../components/searchResults/subcategory'
import ResultList from '../../components/searchResults/resultList'

export default class SearchResults extends Component<{searchItem: string}> {
  render() {
    // console.log("testing " + this.props.searchItem);
    return (
      <div className='SearchResults'>
        <h3>Search Page</h3>
        <br/>
        <Subcategory/>
        <ResultList/>
        
      </div>
    )
  }
}
