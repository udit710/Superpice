import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import { SubCategory } from '../../interfaces/subcategory.interface';
import Subcategory from '../../components/searchResults/subcategory';

describe('Subcategory component tests', () => {
    const testSub: SubCategory = {
        subCategoryId: 1,
        subCategoryName: "test",
    };
    const view = render(<Subcategory subcategories={[testSub]}/>);
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
