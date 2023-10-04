import Address from "./address.interface";

export interface Store{
    id: number;
    storeName: string;
    address: Address;
    contactDetails: string;
}