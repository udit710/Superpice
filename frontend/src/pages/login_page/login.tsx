import React, { useState } from 'react';
import './login.css';
import { Outlet } from 'react-router';
import axios from 'axios';
import { tokenResponse } from '../../interfaces/tokenResponse.interface';

export default function Login(){
  const [email, setEmail] = useState("" as string);
  const [password, setPassword] = useState("" as string);

  return (
    <div className='login'>
      <div className='login-header'>
        <h1>LOG IN</h1>
        <form id='login-form' onSubmit={postLogin}>
          <div className='form-container'>
              <div className="input-field">
                  <label htmlFor="email">Email:</label>
                  <input type="text" id="email" name="email" placeholder='email' required onChange={(e) => {setEmail(e.target.value)}} ></input>
              </div>
              <div className="input-field">
                  <label htmlFor="password">Password:</label>
                  <input type="password" id="password" name="password" placeholder='password' required onChange={(e) => {setPassword(e.target.value)}} ></input>
              </div>
              <button className="login-button" type="submit">Login</button>
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

    const creds = {
      email: email,
      password: password,
    }
    axios.post(`${process.env.REACT_APP_API_URL}/api/auth/login`, creds)
    .then(res => {
      response = res.data;
      // console.log(response.token);
      window.sessionStorage.setItem('userToken', response.token);
      window.location.href = `/`;
    })
    .catch(err => {
      // console.log(err);
      window.location.reload();
    });
  }
}


