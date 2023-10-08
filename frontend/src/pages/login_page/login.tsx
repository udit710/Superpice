import React, { useEffect, useState } from 'react';
import './login.css';
import { Outlet } from 'react-router';
import axios from 'axios';
import { tokenResponse } from '../../interfaces/tokenResponse.interface';

export default function Login(){
  const [email, setEmail] = useState("" as string);
  const [password, setPassword] = useState("" as string);

  const search = window.location.search;
  const params = new URLSearchParams(search);
	const error = params.get('error');

  useEffect(() => {
    document.title = 'Login - SUPERPRICE';
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
    <div className='login'>
      <div className='login-header'>
        <h1 data-testid='login-title'>LOG IN</h1>
        <form id='login-form' onSubmit={postLogin}>
          <div className='form-container'>
              <div data-testid='login-email' className="input-field">
                  <label htmlFor="email">Email:</label>
                  <input type="text" id="email" name="email" placeholder='email' required onChange={(e) => {setEmail(e.target.value)}} ></input>
              </div>
              <div data-testid='login-pass'className="input-field">
                  <label htmlFor="password">Password:</label>
                  <input type="password" id="password" name="password" placeholder='password' required onChange={(e) => {setPassword(e.target.value)}} ></input>
              </div>
              {error === 'true' ? (
                <div className="alert alert-danger" role="alert">
                  Incorrect Email or Password!
                </div>
              ):
              (<div />)}
              <button data-testid='login-submit'className="login-button" type="submit">Login</button>
            </div>
        </form>
        <Outlet/>
      </div>
      <div className='login-form'>

      </div>
    </div>
  )

  function postLogin(e: React.FormEvent<HTMLFormElement>){
    e.preventDefault();
    // console.log(email);
    let response: tokenResponse;
    
    const search = window.location.search;
    const params = new URLSearchParams(search);
    const url = params.get('next');

    const creds = {
      email: email,
      password: password,
    }
    axios.post(`${process.env.REACT_APP_API_URL}/api/auth/login`, creds)
    .then(res => {
      response = res.data;
      // console.log(response.token);
      window.sessionStorage.setItem('userToken', response.token);
      
      if (url !== null && url !== '') window.location.href = url;
      else window.location.href = '/';
    })
    .catch(err => {
      // console.log(err);
      window.location.href = 
        (url !== null && url !== '') ? 
        `/login?error=true&next=${url}` :
        `/login?error=true`;
    });
  }
}


