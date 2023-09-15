import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ResultList from '../../components/searchResults/resultList';
import { Product } from '../../pages/searchResults/searchResults';

describe('ResultsList comonent tests', () => {
    const testProduct: Product = {
        productName: 'test',
        description: 'test',
        details: [{
            store: {storeName: ''},
            price: 1,
            available: 1,
            discount: 1
        }],
        images: [{imageUrl: ''}]
    };

    const view = render(<ResultList products={[testProduct]}/>);
    expect(view).toBeTruthy();

    test('Check image exists', () => {
        const img = screen.getAllByAltText('product');
        expect(img.length).greaterThanOrEqual(1);
    });

    test('Check name exists', async () => {
        const name = await screen.findAllByTestId('product_name');
        expect(name.length).greaterThanOrEqual(1);
    });

    test('Check price exists', async () => {
        const price = await screen.findAllByTestId('price');
        expect(price.length).greaterThanOrEqual(1);
    });

});
