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


  getUserLoggedIn = () => {
    const token = window.sessionStorage.getItem('userToken');

    if (token === undefined || token === null) return;

    let currUser: user;
    axios.post(`${process.env.REACT_APP_API_URL}/api/auth/validate`, { token: token })
      .then(res => {
        currUser = res.data;
        console.debug(currUser);
        this.setState({ user: currUser.username });
        this.setState({ isLogedIn: true });
        this.setState({ userId: currUser.userId});
      })
      .catch(err => {
        console.log(err);
      });
  }

  componentDidMount() {
    this.getUserLoggedIn();
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
            <h3 data-testid='notif-header'>Notifications</h3>
          </div>

          <div data-testid='notifications'>

            {this.state.notifs.map(notif => (

              (notif.user.userId === this.state.userId) ?
                <div className='notif' key={notif.notifId}>
                  <p>{notif.message}</p>
                  <p>{notif.type}</p>
                  <p>{notif.timestamp}</p>
                </div>
                : null
            ))}

          </div>
        </div>
      </div>
    );
  }
}
