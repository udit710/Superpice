import React, { Component } from 'react';
import './notifications.css';

export default class Notifications extends Component {

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
