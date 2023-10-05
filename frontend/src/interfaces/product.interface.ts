export interface Product {
    id: number;
    productName: string;
    description: string;
    subCategoryId: number;
    details: {
        id: number;
        store: {storeName: string};
        price: number;
        available: number;
        discount: number
    }[];
    images: { imageUrl: string }[];
}
