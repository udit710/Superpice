import React, { Component } from 'react';
import './notifications.css';

export default class Notifications extends Component {
  // constructor(props: any) {
  //   super(props);
  //   this.toggleNotificationTypes = this.toggleNotificationTypes.bind(this);
  // }

  // componentDidMount(): void {
  //     const notifications =  document.querySelector('.Notifications') as Element | null;
  //     notifications?.addEventListener('click', function(event) {
  //       event.stopPropagation();
  //    });
  // }
  toggleNotificationTypes(e: any) {
    console.log(e.target.value);
    const types: string[] = ["discount", "order", "account"];

    if (e.target.value === null || !(e.target.value in types)) return;

    for (let t in types) {
      const notifs = document.getElementById(t + "-notifs")?.style as CSSStyleDeclaration;
      if (t === e.target.value) {
        notifs.display = "block";
      }
      else {
        notifs.display = "none";
      }
    }
  }

  render() {
    return (
      <div className='Notifications' data-testid='notifs'>

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
