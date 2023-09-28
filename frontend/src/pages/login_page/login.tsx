import './login.css';
import { Outlet } from 'react-router';

export default function Login(){
  return (
    <div className='login'>
      <div className='login-header'>
        <h1>LOG IN</h1>
        <form action="#" method="post">
          <div className='form-container'>
              <div className="input-field">
                  <label htmlFor="email">Email or Username:</label>
                  <input type="text" id="email" name="email" placeholder='email' required></input>
              </div>
              <div className="input-field">
                  <label htmlFor="password">Password:</label>
                  <input type="password" id="password" name="password" placeholder='password' required></input>
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
}
