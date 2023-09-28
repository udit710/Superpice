import './login.css';
import { Outlet } from 'react-router';

export default function Login(){
  return (
    <div className='login'>
      <div className='login-header'>
        <h1>LOG IN</h1>
        <Outlet/>
      </div>
      <div className='login-form'>
        
      </div>
    </div>
  )
}
