import { Product } from "./product.interface";
import { Store } from "./store.interface";

export interface Product_Details{
    id: number;
    product: Product;
    store: Store;
    orignal_price: number;
    discount: number;
    price: number;
    available: number;
}