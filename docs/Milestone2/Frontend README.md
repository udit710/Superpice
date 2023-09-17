# Superprice App

Superprice is a frontend application designed to provide users with a seamless shopping experience by comparing prices, viewing product details, and exploring various offers. Built with React and TypeScript, the app integrates with various APIs to fetch product data and offers.

## Directory Structure

```
📦src
 ┣ 📂assets - Contains all the static assets like logos and product images.
 ┣ 📂components - Reusable UI components.
 ┃ ┣ 📂footer - Footer component.
 ┃ ┃ ┣ 📜footer.css
 ┃ ┃ ┗ 📜footer.tsx
 ┃ ┣ 📂navbar - Navbar component with notifications.
 ┃ ┃ ┣ 📜navbar.css
 ┃ ┃ ┣ 📜navbar.tsx
 ┃ ┃ ┣ 📜notifications.css
 ┃ ┃ ┗ 📜notifications.tsx
 ┃ ┣ 📂offerList - List of offers.
 ┃ ┃ ┣ 📜offerList.css
 ┃ ┃ ┗ 📜offerList.tsx
 ┃ ┣ 📂offers - Individual offers.
 ┃ ┃ ┣ 📜offers.css
 ┃ ┃ ┗ 📜offers.tsx
 ┃ ┣ 📂pricecompare - Component to compare prices.
 ┃ ┃ ┣ 📜pricecompare.css
 ┃ ┃ ┣ 📜pricecompare.tsx
 ┃ ┃ ┣ 📜productimg.css
 ┃ ┃ ┗ 📜productimg.tsx
 ┃ ┣ 📂productdetail - Detailed view of a product.
 ┃ ┃ ┣ 📜ProductDetailParagraph.css
 ┃ ┃ ┗ 📜ProductDetailParagraph.tsx
 ┃ ┣ 📂product_review - Reviews for a product.
 ┃ ┃ ┣ 📜ProductReview.css
 ┃ ┃ ┗ 📜ProductReview.tsx
 ┃ ┗ 📂searchResults - Search results components.
 ┃ ┃ ┣ 📜resultList.css
 ┃ ┃ ┣ 📜resultList.tsx
 ┃ ┃ ┣ 📜subcategory.css
 ┃ ┃ ┗ 📜subcategory.tsx
 ┣ 📂interfaces - TypeScript interfaces.
 ┃ ┗ 📜product.interface.ts
 ┣ 📂pages - Main pages of the app.
 ┃ ┣ 📂product_detail - Product detail page.
 ┃ ┃ ┣ 📜ProductDetail.css
 ┃ ┃ ┗ 📜ProductDetail.tsx
 ┃ ┣ 📂searchResults - Search results page.
 ┃ ┃ ┗ 📜searchResults.tsx
 ┃ ┣ 📜homepage.css
 ┃ ┗ 📜homepage.tsx
 ┣ 📂tests - Unit and integration tests for the components and pages.
 ┃ ┣ 📂productDetails
 ┃ ┃ ┣ 📜pricecompare.test.tsx
 ┃ ┃ ┣ 📜productDetail.test.tsx
 ┃ ┃ ┣ 📜productDetailsParagraph.test.tsx
 ┃ ┃ ┣ 📜productimg.test.tsx
 ┃ ┃ ┗ 📜productReview.test.tsx
 ┃ ┣ 📂searchResults
 ┃ ┃ ┣ 📜resultList.test.tsx
 ┃ ┃ ┣ 📜searchResults.test.tsx
 ┃ ┃ ┗ 📜subcategory.test.tsx
 ┃ ┣ 📜Homepage.test.tsx
 ┃ ┗ 📜setup.ts
 ┣ 📜App.css - Main CSS for the App component.
 ┣ 📜App.tsx - Main App component containing the routing logic.
 ┣ 📜index.css - Global styles.
 ┣ 📜index.tsx - Entry point of the application.
 ┣ 📜logo.svg - Logo SVG file.
 ┣ 📜react-app-env.d.ts - Type definitions for create-react-app.
 ┣ 📜reportWebVitals.ts - Performance metrics for the app.
 ┗ 📜vite.config.ts - Configuration file for Vite.
```

## Dependencies

The app uses a range of dependencies to enhance its functionality:

- `react` and `react-dom` for building the UI.
- `react-router` and `react-router-dom` for routing.
- `axios` for making HTTP requests.
- `bootstrap` and `react-bootstrap` for styling and UI components.
- `react-icons` for using icons.
- `typescript` for type checking.
- `web-vitals` for measuring web vitals.

## Development Scripts

- `start`: Starts the development server.
- `build`: Builds the app for production.
- `eject`: Ejects from create-react-app.
- `test`: Runs tests using Vite.
- `test:ui`: Runs UI tests.
- `test:unit`: Runs unit tests.
- `coverage`: Generates test coverage report.

## Routing

The app uses `react-router` for routing. The main routes are:

- `/ProductDetail/:id`: Displays details of a specific product.
- `/search`: Shows search results.
- `/`: The homepage. It also has a nested route for displaying offers based on discounts.

## Getting Started

1. Clone the repository.
2. Navigate to the project directory.
3. Install dependencies using `npm install`.
4. Start the development server using `npm start`.
5. Open `http://localhost:3000` in your browser.
