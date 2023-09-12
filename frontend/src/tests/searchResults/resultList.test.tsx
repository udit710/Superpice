import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import ResultList from '../../components/searchResults/resultList';

describe('ResultsList comonent tests', () => {
    const view = render(<ResultList/>);
    expect(view).toBeTruthy();

    test('Check image exists', () => {
        const img = screen.getAllByAltText('logo');
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
