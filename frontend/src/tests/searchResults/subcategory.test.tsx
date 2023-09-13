import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import Subcategory from '../../components/searchResults/subcategory';

describe('Subcategory component tests', () => {
    const view = render(<Subcategory/>);
    expect(view).toBeTruthy();

    test('Check image exists', () => {
        const img = screen.getAllByAltText('logo');
        expect(img.length).greaterThanOrEqual(1);
    });

    test('Check name exists', async () => {
        const category = await screen.findAllByTestId('subcategory-names');
        expect(category.length).greaterThanOrEqual(1);
    });

});
