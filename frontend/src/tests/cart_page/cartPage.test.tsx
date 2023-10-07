import { render, screen } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import CartPage from "../../pages/cart_page/CartPage";

describe('Cart page tests', () => {
    const view = render(<CartPage/>);
    expect(view).toBeTruthy();

    test('Check empty title', () => {
        const title = screen.getByTestId('cart-title');
        expect(title).toBeDefined();
    });
    
});