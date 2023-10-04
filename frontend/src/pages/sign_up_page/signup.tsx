import React, { ReactElement, useState } from 'react';
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

  function postSignUp(e: React.FormEvent<HTMLFormElement>) {
    e.preventDefault();

    if (password !== conf) {
      window.location.href = `/signup?error=0`; 
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
      window.location.href = `/login`;
    })
    .catch(err => {
      window.location.href = `/signup?error=1`;
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
