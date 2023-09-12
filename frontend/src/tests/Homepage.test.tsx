import { describe, test, expect } from 'vitest'
import { render, screen } from '@testing-library/react'
import Homepage from '../pages/homepage';

describe('Home page', () => {
  test('Displays header', () => {
    const view = render(<Homepage />);
    expect(view).toBeTruthy()
    // screen.debug();

    // Use @testing-library/react's screen utility to query the DOM
    const h1 = screen.getByText('SUPERPRICE');
    expect(h1).toBeInTheDocument();
  })
});
