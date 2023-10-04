import { Product_Details } from "./product_details.interface";
import { user } from "./user.interface";

export interface Cart_Item{
    cart_item_id: number;
    user_id: user;
    product_id: Product_Details;
    quantity: number;
}