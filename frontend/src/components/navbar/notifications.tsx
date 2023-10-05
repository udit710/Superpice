import React, { Component, useState, useEffect } from 'react';
import axios from 'axios'
import './notifications.css';

interface Notifs {
  message: string;
  type: string;
  timestamp: string;
}

export default class Notifications extends Component {
  state = {
    notifs: [] as Notifs[]
  }

  
  componentDidMount() {
		axios.get(`${process.env.REACT_APP_API_URL}/api/notifications`)
		.then(res => {
			this.setState({ notifs: res.data });
		})
    .catch(error => console.error('Error fetching notifications:', error));

  }
		

  render() {
    return (
      <div className='Notifications'>

        <div className="dropdown-menu dropdown-menu-end notif-dropdown p-0 m-0" >

          <div className="notifications-heading">
            <h3>Notifications</h3>
          </div>

            <div>
              <div className='notif'>
                <p>This is the order message</p>
                <p>TYPE</p>
                <p>timestamp</p>
              </div>

              <div className='notif'>
                <p>This is the order message</p>
                <p>TYPE</p>
                <p>timestamp</p>
              </div>

              <div className='notif'>
                <p>This is the order message</p>
                <p>TYPE</p>
                <p>timestamp</p>
              </div>
          </div>
        </div>
      </div>
    );
  }
}
