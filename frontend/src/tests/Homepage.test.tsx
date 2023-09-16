import { describe, test, expect } from 'vitest'
import { render, screen } from '@testing-library/react'
import Homepage from '../pages/homepage';
import { BrowserRouter } from 'react-router-dom';

describe('Home page', () => {
  test('Displays header', () => {
    const view = render(<BrowserRouter><Homepage /></BrowserRouter>);
    expect(view).toBeTruthy()
    // screen.debug();

    // Use @testing-library/react's screen utility to query the DOM
    // const h1 = screen.getByText('SUPERPRICE');
    // expect(h1).toBeInTheDocument();

    // eslint-disable-next-line testing-library/no-container, testing-library/no-node-access
    const h1 = view.container.querySelector('h1');
    expect(h1?.textContent).toBe('SUPERPRICE');
  })
});
