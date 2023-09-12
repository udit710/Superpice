import { expect, afterAll, afterEach, beforeAll } from 'vitest'
import { cleanup } from '@testing-library/react';
import '@testing-library/jest-dom/vitest';
import matchers from '@testing-library/jest-dom/matchers';
import { JSDOM } from 'jsdom';

afterEach(() => {
    cleanup();
});
console.log("server")
beforeAll(() => {
    const dom = new JSDOM();
    global.document = dom.window.document;
});

expect.extend(matchers);


// import { server } from './mocks/server'

// beforeAll(() => server.listen({ onUnhandledRequest: 'error' }))
// afterAll(() => server.close())
// afterEach(() => server.resetHandlers())
