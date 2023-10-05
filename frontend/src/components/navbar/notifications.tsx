import React, { Component, useState, useEffect } from 'react';
import axios from 'axios'
import './notifications.css';
import { user } from '../../interfaces/user.interface';

interface Notifs {
  message: string;
  type: string;
  timestamp: string;
  notifId: number;
  user: { userId: number };
}

export default class Notifications extends Component {
  state = {
    notifs: [] as Notifs[],
    userId: null as number | null
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
      <div className='Notifications' data-testid='notifs'>

        <div className="dropdown-menu dropdown-menu-end notif-dropdown p-0 m-0" >

          <div className="notifications-heading">
            <h3>Notifications</h3>
          </div>

            <div>

                {this.state.notifs.map(notif => (
              <div className='notif'>
                    <p>{notif.message}</p>
                    <p>{notif.type}</p>
                    <p>{notif.timestamp}</p>
              </div>
                ))}

          </div>
        </div>
      </div>
    );
  }
}
