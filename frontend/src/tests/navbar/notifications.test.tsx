import { render, screen } from "@testing-library/react";
import { describe, expect, test } from "vitest";
import Notifications from "../../components/navbar/notifications";

describe('Notification tests', () => {
    const view = render(<Notifications/>);
    expect(view).toBeTruthy();

    test('Notification header', () => {
        const text = screen.getByTestId('notif-header');
        expect(text).toBeDefined();
    });

    test('Notifications div exists', () => {
        const div = screen.getByTestId('notifications');
        expect(div).toBeDefined();
    });

});
