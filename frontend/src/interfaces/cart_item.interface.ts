import { Product_Details } from "./product_details.interface";
import { user } from "./user.interface";

export interface Cart_Item{
    id: any;
    userId: any;
    productId: number;
    productDetailsId: any;
    quantity: number;
}