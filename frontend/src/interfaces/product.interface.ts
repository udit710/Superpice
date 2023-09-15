export interface Product {
    id: number;
    productName: string;
    description: string;
    details: {
        store: {storeName: string};
        price: number;
        available: number;
        discount: number
    }[];
    images: { imageUrl: string }[];
}
