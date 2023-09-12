import { describe, test, expect } from 'vitest'
import { render, screen } from '@testing-library/react';
import App from '../App';

test('renders learn react link', () => {
  // render(<App />);
  const linkElement = screen.getByText(/a/i);
  expect(linkElement).toBeInTheDocument();
});
