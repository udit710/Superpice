import React, { Component } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';
import './navbar.css';

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
                    <li><a className="dropdown-item" href="/#">Another action</a></li>
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
            </div>
        </div>
        </nav>
      </div>
    );
  }
}
