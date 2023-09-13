import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ProductImg from '../../components/pricecompare/productimg';

describe('PriceCompare component tests', () => {
    const view = render(<ProductImg/>);
    expect(view).toBeTruthy();

    test('Product image exists', async () => {
        const img = await screen.findAllByAltText('product');
        expect(img).toBeDefined();
    });
    
    test('Nutrition facts image exist', async () => {
        const nutrition = await screen.findAllByAltText('nutrition-facts');
        expect(nutrition).toBeDefined();
    });
    
});