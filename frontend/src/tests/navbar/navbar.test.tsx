import { render, screen } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import Navbar from "../../components/navbar/navbar";

describe('Navbar tests', () => {
    const view = render(<Navbar/>);
    expect(view).toBeTruthy();

    test('Displays App Name', () => {
        const title = screen.getByTestId('app-title');
        expect(title).toBeDefined();
    });

    test('Displays Home Link', () => {
        const link = screen.getByTestId('app-home-link');
        expect(link).toBeDefined();
    });

    test('Displays Offers Link', () => {
        const link = screen.getByTestId('app-offers-link');
        expect(link).toBeDefined();
    });

    test('Displays Search', () => {
        const search = screen.getByTestId('app-search');
        expect(search).toBeDefined();
    });

    test('Displays Saved Link', () => {
        const link = screen.getByTestId('app-saved');
        expect(link).toBeDefined();
    });

    test('Displays Notifications', () => {
        const notifs = screen.getByTestId('notifs');
        expect(notifs).toBeDefined();
    });

    
});