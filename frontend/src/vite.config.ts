/// <reference types="vitest" />
import { defineConfig } from 'vite'

import react from '@vitejs/plugin-react'
import checker from 'vite-plugin-checker';
import path from 'path'

export default defineConfig({
  plugins: [
    react(),
    checker({ typescript: true }),
  ],
  test: {
    include: ['src/**/*.{test,spec}.{js,mjs,cjs,ts,mts,cts,jsx,tsx}'],
    globals: true,
    // setupFiles: './setupTest.js',
    setupFiles: './tests/setup.ts',
    environment: 'jsdom',
  },
  resolve: {
    alias: {
        '~bootstrap': path.resolve(__dirname, 'node_modules/bootstrap'),
    },
  },
})
