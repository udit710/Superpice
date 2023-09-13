import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ProductDetailParagraph from '../../components/productdetail/ProductDetailParagraph';

describe('Product detail paragraph component tests', () => {
    const view = render(<ProductDetailParagraph/>);
    expect(view).toBeTruthy();

    test('Product Detail text', async () => {
        const h1 = await screen.findByText('Product Detail');
        expect(h1).toBeDefined();
    });
    
    test('Allergens text', async () => {
        const h1 = await screen.findByText('Allergens');
        expect(h1).toBeDefined();
    });

    test('Ingredients text', async () => {
        const h1 = await screen.findByText('Ingredients');
        expect(h1).toBeDefined();
    });

    test('Storage Instructions text', async () => {
        const h1 = await screen.findByText('Storage Instructions');
        expect(h1).toBeDefined();
    });

    test('Data points exists', () => {
        // eslint-disable-next-line testing-library/no-node-access, testing-library/no-container
        const data = view.container.querySelectorAll('p');
        expect(data.length).equals(4);
    });
});