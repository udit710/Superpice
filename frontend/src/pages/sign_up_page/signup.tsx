import './signup.css';
import { Outlet } from 'react-router';

export default function SignUp(){
  return (
    <div className='signup'>
      <div className='signup-header'>
        <h1>SIGN UP</h1>
      </div>
      <div className='signup-form'>
        <form action="#" method="post">
          <div className='form-container'>
              <div className="input-field">
                  <label htmlFor="first-name">First name:</label>
                  <input type="text" id="first-name" name="first-name" placeholder='first name' required></input>
              </div>
              <div className="input-field">
                  <label htmlFor="last-name">Last name:</label>
                  <input type="text" id="last-name" name="last-name" placeholder='last name' required></input>
              </div>
              <div className="input-field">
                  <label htmlFor="email">Email:</label>
                  <input type="text" id="email" name="email" placeholder='email' required></input>
              </div>
              <div className="input-field">
                  <label htmlFor="email-confirmation">Confirm Email:</label>
                  <input type="text" id="email-confirmation" name="email-confirmation" placeholder='email confirmation' required></input>
              </div>
              <div className="input-field">
                  <label htmlFor="phone">Telephone:</label>
                  <input type="text" id="phone" name="phone" placeholder='phone' required></input>
              </div>
              <div className="input-field">
                  <label htmlFor="username">Username:</label>
                  <input type="text" id="username" name="username" placeholder='username' required></input>
              </div>
              <div className="input-field">
                  <label htmlFor="password">Password:</label>
                  <input type="password" id="password" name="password" placeholder='password' required></input>
              </div>
              <div className="input-field">
                  <label htmlFor="password-confirmation">Confirm Password:</label>
                  <input type="text" id="password-confirmation" name="password-confirmation" placeholder='password confirmation' required></input>
              </div>
              <button className="signup-button" type="submit">Login</button>
            </div>
        </form>
      </div>
        <Outlet/>
    </div>
  )
}