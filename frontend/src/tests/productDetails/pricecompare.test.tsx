import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import PriceCompare from '../../components/pricecompare/pricecompare';

describe('PriceCompare component tests', () => {
    const view = render(<PriceCompare/>);
    expect(view).toBeTruthy();

    test('Product name exists', async () => {
        const h2 = await screen.findByTestId('product-name');
        expect(h2).toBeDefined();
    });
    
    test('Table items exist', async () => {
        const store = await screen.findAllByTestId('store-data');
        expect(store.length).toBeGreaterThanOrEqual(1);
    });
});