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
      <div className='Notifications'>
        <ul className="dropdown-menu dropdown-menu-end notif-dropdown" >
          <li><h3>Notifications</h3></li>
          <li><hr className="dropdown-divider"/></li>

          <li>
            <div className="type-groups" role='group' onChange={this.toggleNotificationTypes}>
                <input type="radio" value="discount" className="btn-check" name="notif-options" id="Discounts" defaultChecked />
                <label className="btn btn-outline-primary" htmlFor="Discounts">Discounts</label>

                <input type="radio" value="order" className="btn-check" name="notif-options" id="Order" />
                <label className="btn btn-outline-primary" htmlFor="Order">Order</label>

                <input type="radio" value="account" className="btn-check" name="notif-options" id="Account" />
                <label className="btn btn-outline-primary" htmlFor="Account">Account</label>
            </div>
          </li>
          <li><br/></li>
          
          <li>
            <div>
              <div className='notif-types' id='discount-notifs'>
                <p>Discount Notification!</p>
                <p>Discount Notification 2, Electric Boogaloo</p>
              </div>
              <div className='notif-types' id='order-notifs'>
                <p>Order Notification!</p>
                <p>Order Notification also</p>
              </div>
              <div className='notif-types' id='account-notifs'>
                <p>Account Notification!</p>
                <p>Account Notification? ¯\_(ツ)_/¯</p>
              </div>
            </div>
          </li>
        </ul>
      </div>
    );
  }
}
