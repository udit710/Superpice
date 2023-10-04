import { describe, test, expect } from 'vitest'
import { render, screen } from '@testing-library/react'
import Homepage from '../pages/homepage';
import { BrowserRouter } from 'react-router-dom';

describe('Home page', () => {
  test('Displays header', () => {
    const view = render(<BrowserRouter><Homepage /></BrowserRouter>);
    expect(view).toBeTruthy()
    
    test('Title', () => {
      // eslint-disable-next-line testing-library/no-container, testing-library/no-node-access
      const h1 = view.container.querySelector('h1');
      expect(h1?.textContent).toBe('SUPERPRICE');
    });

    test('Subtitle', () => {
      // eslint-disable-next-line testing-library/no-container, testing-library/no-node-access
      const h5 = view.container.querySelector('h5');
      expect(h5?.textContent).toBe('Your place for shopping smart');
    });

    test('OfferList', () => {
      const offer = screen.getByTestId('offerlist');
      expect(offer).toBeDefined();
    });

  })
});
