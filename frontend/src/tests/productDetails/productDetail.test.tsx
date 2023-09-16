import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ProductDetailWrapper from '../../components/Wrappers/ProductDetailWrapper';

describe('Product Detail page tests', () => {
        const view = render(<ProductDetailWrapper/>);
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
            const productreviews = await screen.findAllByTestId('productreview');
            expect(productreviews.length).toBeGreaterThan(0);
        });

        // test('Check ProductDetailParagraph component exist', async () => {
        //     const pdp = await screen.findByTestId('pdp');
        //     expect(pdp).toBeInTheDocument();
        // });
    });
