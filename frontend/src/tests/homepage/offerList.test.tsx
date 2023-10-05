import { render, screen } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import OfferList from "../../components/offerList/offerList";

describe('OfferList tests', () => {
    const view = render(<OfferList/>);
    expect(view).toBeTruthy();

    test('Check title', () => {
        const title = screen.getByTestId('offer-title');
        expect(title).toBeDefined();
    });

    test('Check card container', () => {
        const container = screen.getByTestId('offer-container');
        expect(container).toBeDefined();
    });

});
