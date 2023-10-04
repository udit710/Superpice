import { render, screen } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import OffersCard from "../../components/offers_card/offersCard";
import { Product } from "../../interfaces/product.interface";

describe('OffersPage tests', () => {
    const p: Product = {
        id: 0,
        productName: 'test',
        description: 'test',
        subCategoryId: 0,
        details: [],
        images: []
    };
    const view = render(<OffersCard product={p} maxIndex={0}/>);
    expect(view).toBeTruthy();

    test('Check card', () => {
        const card = screen.getByTestId('offers-page-card');
        expect(card).toBeDefined();
    });

    test('Check img', () => {
        const img = screen.getByTestId('offers-page-img');
        expect(img).toBeDefined();
    });

    test('Check title', () => {
        const title = screen.getByTestId('offers-page-card-title');
        expect(title).toBeDefined();
    });

    test('Check body text', () => {
        const text = screen.getByTestId('offers-page-card-text');
        expect(text).toBeDefined();
    })

});
