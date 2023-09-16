import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ProductReview from '../../components/product_review/ProductReview';

describe('ProductReview component tests', () => {
    const view = render(<ProductReview id={1} rating={1} body='test'/>);
    expect(view).toBeTruthy();

    test('Check rating exists', async () => {
        const rating = await screen.findByTestId("rating");
        expect(rating).toBeDefined();
    });

    test('Check comments exists', async () => {
        const comment = await screen.findByTestId("comment");
        expect(comment).toBeDefined();
    });

});
