import React, { Component } from 'react';
import Notifications from './notifications';
import { Link } from 'react-router-dom';
import './navbar.css';
import { BsBell } from 'react-icons/bs';

export default class Navbar extends Component {
  render() {
    return (
      <div className='Navbar'>
        <nav className="navbar navbar-expand-lg bg-body-tertiary">
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
                        {/**WARNING: REMOVE LINK FROM HERE ---Added By Rashik */}
                        <li><Link to="/ProductDetail/fake_id"><a className="dropdown-item" href="/#">Temp_ProductDetailPage</a> </Link></li>
                        <li><hr className="dropdown-divider"/></li>
                        <li><a className="dropdown-item" href="/#">Something else here</a></li>
                      </ul>
                    </li>

                    <li className="nav-item">
                      <a className="nav-link" href="/#">Current Sales</a>
                    </li>
                    
                </ul>
                <form className="d-flex" role="search">
                    <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                    <button className="btn btn-outline-success" type="submit">Search</button>
                </form>

                <a className='nav-link post-nav-items' href='/#'>Saved</a>

                <div className='post-nav-items dropdown'>
                  <a aria-label='Notification' className='nav-link dropdown-toggle' role="button" data-bs-toggle="dropdown" href='/#'><BsBell size={25} /></a>
                  <Notifications/>
                </div>

                <a className='nav-link post-nav-items' href='/#'>Account</a>
              </div>
          </div>
        </nav>
      </div>
    );
  }
}
