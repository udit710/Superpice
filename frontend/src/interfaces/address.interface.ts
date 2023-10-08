export default interface Address{
    id: number;
    addressLine1: string;
    addressLine2: string | null;
    city: string;
    state: string;
    postalCode: string;
    country: string;
    addressType: AddressType;
}

export enum AddressType{
    USER= 'USER',
    STORE= 'STORE',
    ORDER= 'ORDER'
}