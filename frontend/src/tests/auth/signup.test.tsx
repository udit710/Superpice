import { render, screen } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import SignUp from "../../pages/sign_up_page/signup";

describe('OffersPage tests', () => {
    const view = render(<SignUp/>);
    expect(view).toBeTruthy();

    test('Check title', () => {
        const title = screen.getByTestId('signup-title');
        expect(title).toBeDefined();
    });

    test('Check email', () => {
        const input = screen.getByTestId('signup-email');
        expect(input).toBeDefined();
    });

    test('Check password', () => {
        const input = screen.getByTestId('signup-pass');
        expect(input).toBeDefined();
    });

    test('Check password confirm', () => {
        const input = screen.getByTestId('signup-pass-conf');
        expect(input).toBeDefined();
    });

    test('Check submit', () => {
        const input = screen.getByTestId('signup-submit');
        expect(input).toBeDefined(); 
    });

    test('Check first name', () => {
        const input = screen.getByTestId('signup-first');
        expect(input).toBeDefined(); 
    });

    test('Check last name', () => {
        const input = screen.getByTestId('signup-last');
        expect(input).toBeDefined(); 
    });

    test('Check phone', () => {
        const input = screen.getByTestId('signup-phone');
        expect(input).toBeDefined(); 
    });

    test('Check username', () => {
        const input = screen.getByTestId('signup-username');
        expect(input).toBeDefined(); 
    });

});
