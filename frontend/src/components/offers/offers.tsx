import React, { Component } from 'react'
import './offers.css';

export default class Offers extends Component {
  render() {
    return (
        <div className='offers'>
            Ongoing Offers:
            <div className='offer-container'>
                <div className='offer-box'>
                    Offer 1 | 50% off
                </div>
                <div className='offer-box'>
                    Offer 2 | 20% off
                </div>
                <div className='offer-box'>
                    Offer 3 | 75% off
                </div>
            </div>
        </div>

    )
  }
}
