import React, { ReactElement, useEffect, useState } from 'react';
import './signup.css';
import { Outlet } from 'react-router';
import axios from 'axios';

export default function SignUp(){
  const [first, setFirst] = useState(null as string | null);
  const [last, setLast] = useState(null as string | null);
  const [email, setEmail] = useState(null as string | null);
  // const [conf, setConf] = useState(null as string | null);
  const [phone, setPhone] = useState(null as string | null);
  const [username, setUsername] = useState(null as string | null);
  const [password, setPassword] = useState(null as string | null);
  const [conf, setConf] = useState(null as string | null);

  const search = window.location.search;
  const params = new URLSearchParams(search);
	const error = params.get('error');

  useEffect(() => {
    document.title = 'Sign up - SUPERPRICE';
    const token = window.sessionStorage.getItem('userToken');

    if (token === undefined || token === null) return;

    axios.post(`${process.env.REACT_APP_API_URL}/api/auth/validate`, { token: token })
    .then(res => {
      const search = window.location.search;
      const params = new URLSearchParams(search);
      const url = params.get('next');
      window.location.href = 
        (url !== null && url !== '') ? 
        url :
        `/`;
    })
    .catch(err => {
      console.log(err);
    });
  }, [])

  return (
    <div className='signup'>
      <div className='signup-header'>
        <h1 data-testid='signup-title'>SIGN UP</h1>
      </div>
      <div className='signup-form'>
        <form id='signup-form' onSubmit={postSignUp} >
          <div className='form-container'>
              <div data-testid='signup-first' className="input-field">
                  <label htmlFor="first-name">First name:</label>
                  <input type="text" id="first-name" name="first-name" placeholder='first name' required onChange={(e) => {setFirst(e.target.value)}} ></input>
              </div>
              <div data-testid='signup-last' className="input-field">
                  <label htmlFor="last-name">Last name:</label>
                  <input type="text" id="last-name" name="last-name" placeholder='last name' required onChange={(e) => {setLast(e.target.value)}} ></input>
              </div>
              <div data-testid='signup-email' className="input-field">
                  <label htmlFor="email">Email:</label>
                  <input type="text" id="email" name="email" placeholder='email' required onChange={(e) => {setEmail(e.target.value)}} ></input>
              </div>
              {/* <div className="input-field">
                  <label htmlFor="email-confirmation">Confirm Email:</label>
                  <input type="text" id="email-confirmation" name="email-confirmation" placeholder='email confirmation' required></input>
              </div> */}
              <div data-testid='signup-phone' className="input-field">
                  <label htmlFor="phone">Telephone:</label>
                  <input type="text" id="phone" name="phone" placeholder='phone' required onChange={(e) => {setPhone(e.target.value)}} ></input>
              </div>
              <div data-testid='signup-username' className="input-field">
                  <label htmlFor="username">Username:</label>
                  <input type="text" id="username" name="username" placeholder='username' required onChange={(e) => {setUsername(e.target.value)}} ></input>
              </div>
              <div data-testid='signup-pass' className="input-field">
                  <label htmlFor="password">Password:</label>
                  <input type="password" id="password" name="password" placeholder='password' required onChange={(e) => {setPassword(e.target.value)}} ></input>
              </div>
              <div data-testid='signup-pass-conf' className="input-field">
                  <label htmlFor="password-confirmation">Confirm Password:</label>
                  <input type="password" id="password-confirmation" name="password-confirmation" placeholder='password confirmation' required onChange={(e) => {setConf(e.target.value)}} ></input>
              </div>
              {errorMessage(error)}
              <button data-testid='signup-submit' className="signup-button" type="submit">Login</button>
            </div>
        </form>
      </div>
        <Outlet/>
    </div>
  )


  function addWelcomeNotif() {
    
    axios.get(`${process.env.REACT_APP_API_URL}/api/users/email/` + email)
    .then(res => {

        const notification = {
          user: res.data,
          message: 'Welcome to the site! Enjoy our offers!',
          type: 'OFFERS',
        }

        axios.post(`${process.env.REACT_APP_API_URL}/api/notifications`, notification)
        .then(res => {
          console.log(res.data);
        })
        .catch(err => console.error('Error creating welcome notification:', error));
      })
      .catch(error => console.error('Error fetching user ID:', error));

  }

  function postSignUp(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();
    
    const search = window.location.search;
    const params = new URLSearchParams(search);
    const url = params.get('next');

    if (password !== conf) {
      window.location.href = 
        (url !== null && url !== '') ? 
        `/signup?error=0&next=${url}` :
        `/signup?error=0`;
      return;
    }

    const inputs = {
      username: username,
      email: email,
      first_name: first,
      last_name: last,
      phone: phone,
      password: password,
    }

    axios.post(`${process.env.REACT_APP_API_URL}/api/auth/sign-up`, inputs)
    .then(res => {
      
      addWelcomeNotif();
      if (url !== null && url !== '') window.location.href = `/login?next=${url}`
      else window.location.href = `/login`;
    })
    .catch(err => {
      window.location.href = 
        (url !== null && url !== '') ? 
        `/signup?error=1&next=${url}` :
        `/signup?error=1`;
    });
  }

  function errorMessage(error: string | null): ReactElement | null {
    if (error === '0') {
      return (
        <div className="alert alert-danger" role="alert">
          Passwords do not match!
        </div>
      )
    }
    if (error === '1') {
      return (
        <div className="alert alert-danger" role="alert">
          Username or Email already used!
        </div>
      )
    }
    return null;
  }
}
