import { render, screen } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import OffersPage from "../../pages/offers_page/offersPage";

describe('OffersPage tests', () => {
    const view = render(<OffersPage/>);
    expect(view).toBeTruthy();

    test('Check title', () => {
        const title = screen.getByTestId('offers-page-title');
        expect(title).toBeDefined();
    });

    test('Check slider', () => {
        const slider = screen.getByTestId('offers-page-slider');
        expect(slider).toBeDefined();
    });

});
