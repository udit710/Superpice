import React, { Component } from 'react';
import Notifications from './notifications';
import { Link } from 'react-router-dom';
import './navbar.css';
import { BsBell } from 'react-icons/bs';
import axios from 'axios';
import { user } from '../../interfaces/user.interface';

export default class Navbar extends Component {
  state = {
    isLogedIn: false as boolean,
    user: null as string | null
  }

  render() {
    return (
      <div className='Navbar'>
        <nav className="navbar navbar-expand-lg bg-body-tertiary navbar-container">
          <div className="container-fluid nav-content">
              <a className="navbar-brand" href="/#">SUPERPRICE</a>
              
              <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav me-auto mb-3 mb-lg-0">
                    <li className="nav-item">
                      <a className="nav-link active" aria-current="page" href="/#">Home</a>
                    </li>
                    
                    <li className="nav-item dropdown">
                      <a className="nav-link dropdown-toggle" href="/#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                          Category
                      </a>
                      <ul className="dropdown-menu">
                        <li><a className="dropdown-item" href="/#">Action</a></li>
                        <li><hr className="dropdown-divider"/></li>
                        {/**WARNING: REMOVE LINK FROM HERE ---Added By Rashik */}
                        <li><Link to="/checkout-page"><a className="dropdown-item" href="/#">Checkout-Page</a> </Link></li>
                        <li><hr className="dropdown-divider"/></li>
                        <li><a className="dropdown-item" href="/#">Something else here</a></li>
                      </ul>
                    </li>

                    <li className="nav-item">
                      <a className="nav-link" href="/#">Current Sales</a>
                    </li>
                    
                </ul>
                <form className="d-flex" role="search" method="get" action="/search">
                    <input name="item" className="form-control me-2" type="search" placeholder="Search" aria-label="Search" required />
                    <button className="btn btn-outline-success" type="submit">Search</button>
                </form>

                <a className='nav-link post-nav-items' href='/#'>Saved</a>

                <div className='post-nav-items dropdown'>
                  <a aria-label='Notification' className='nav-link dropdown-toggle' role="button" data-bs-toggle="dropdown" href='/#'><BsBell size={25} /></a>
                  <Notifications/>
                </div>
                
                {this.state.isLogedIn ? (
                  <div className="dropdown post-nav-items">
                    <a href='/#' role="button" className='nav-link username' data-bs-toggle="dropdown" aria-expanded="false">{this.state.user}</a>
                    <ul className="dropdown-menu dropdown-menu-end">
                        <li><a className="dropdown-item" href="/#" onClick={this.logout} >Logout</a></li>
                    </ul>
                  </div>
                ):
                (
                  <a className='nav-link post-nav-items' href='/login'>Login</a>
                )}
              </div>
          </div>
        </nav>
      </div>
    );
  }

  componentDidMount() {
    const token = window.sessionStorage.getItem('userToken');

    if (token === undefined || token === null) return;

    let currUser: user;
    axios.post(`${process.env.REACT_APP_API_URL}/api/auth/validate`, { token: token })
    .then(res => {
      currUser = res.data;
      this.setState({ user: currUser.username });
      this.setState({ isLogedIn: true });
    })
    .catch(err => {
      console.log(err);
    });
  }

  logout = (e: React.MouseEvent) => {
    e.preventDefault();
    window.sessionStorage.removeItem("userToken");
    window.location.reload();
  }

}
