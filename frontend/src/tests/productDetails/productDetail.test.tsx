import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ProductDetail from '../../pages/product_detail/ProductDetail';

describe('Product Detial page tests', () => {
    const view = render(<ProductDetail/>);
    expect(view).toBeTruthy();

    test('Check Productimg component exist', async () => {
        const productimg = await screen.findByTestId('productimg');
        expect(productimg).toBeDefined();
    });

    test('Check Pricecompare component exist', async () => {
        const pricecompare = await screen.findByTestId('pricecompare');
        expect(pricecompare).toBeDefined();
    });

    test('Check ProductReview component exist', async () => {
        const productreview = await screen.findAllByTestId('productreview');
        expect(productreview).toBeDefined();
    });

    test('Check ProductDetailParagraph component exist', async () => {
        const pdp = await screen.findByTestId('pdp');
        expect(pdp).toBeDefined();
    });
    
});
