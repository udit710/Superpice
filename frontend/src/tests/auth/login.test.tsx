import { render, screen } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import Login from "../../pages/login_page/login";

describe('OffersPage tests', () => {
    const view = render(<Login/>);
    expect(view).toBeTruthy();

    test('Check title', () => {
        const title = screen.getByTestId('login-title');
        expect(title).toBeDefined();
    });

    test('Check email', () => {
        const input = screen.getByTestId('login-email');
        expect(input).toBeDefined();
    });

    test('Check password', () => {
        const input = screen.getByTestId('login-pass');
        expect(input).toBeDefined();
    });

    test('Check submit', () => {
        const input = screen.getByTestId('login-submit');
        expect(input).toBeDefined(); 
    });

});
