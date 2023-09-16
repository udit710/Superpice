import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, createBrowserRouter, RouterProvider, createRoutesFromElements, Route } from 'react-router-dom';
import ProductDetailWrapper from './components/Wrappers/ProductDetailWrapper';
// import SearchResults from './pages/searchResults/searchResults';


// Updated router that allows data transfer
const router = createBrowserRouter([
  {
    // Default path 
    path: '/*',
    element: <App/>,

    children: [
      // Specify each path as a json object
      // No need to use loader unless getting form data
      // IMPORTANT: Also need to add route in App component
      {
        path: 'search/',
        loader: async ({ request }) => {
          let url = new URL(request.url);
          let searchItem = url.searchParams.get("item");
          console.log(searchItem);
          return searchItem;
        },
        // element: <SearchResults searchItem='' />
      },
      {
        path: 'ProductDetail/:id'
      },
      {
        path: '',
      }
    ]
    
  }
])

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    {/* <BrowserRouter>
      <App />
    </BrowserRouter> */}
    <RouterProvider router={router} />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
