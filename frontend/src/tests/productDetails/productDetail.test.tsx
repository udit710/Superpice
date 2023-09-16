import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ProductDetail from '../../pages/product_detail/ProductDetail';

describe('Product Detail page tests', () => {
        const view = render(<ProductDetail/>);
        expect(view).toBeTruthy();

        test('Check ProductDetails returns view', async () => {
            const details = await screen.findByTestId('product-details');
            expect(details).toBeDefined();
        });

    });
