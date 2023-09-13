import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import SearchResults from '../../pages/searchResults/searchResults';

describe('Search Results page tests', () => {
    const view = render(<SearchResults searchItem=''/>);
    expect(view).toBeTruthy();

    test('Check header', async () => {
        const h3 = await screen.findAllByText('Search Page');
        expect(h3).toBeDefined();
    });

    test('Check Subcategory menu exist', async () => {
        const subcategory = await screen.findByTestId('subcategory');
        expect(subcategory).toBeDefined();
    });

    test('Check ResultList menu exist', async () => {
        const subcategory = await screen.findByTestId('resultlist');
        expect(subcategory).toBeDefined();
    });

});
