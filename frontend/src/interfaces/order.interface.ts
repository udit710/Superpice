export default interface Order{
    id: number;
    userId: number;
    orderDate: Date;
    orderStatus: OrderStatus;
    totalAmount: number;
    orderAddress: number;
    paymentMethod: PaymentMethod;
}

export enum OrderStatus {
    PENDING ='PENDING',
    IN_PROGRESS='IN_PROGRESS',
    DELIVERED='DELIVERED',
    CANCELLED='CANCELLED',
    SHIPPED='SHIPPED'
}

export enum PaymentMethod{
    CREDIT_CARD='CREDIT_CARD',
    PAYPAL='PAYPAL',
    DEBIT_CARD='DEBIT_CARD',
    WALLET='WALLET'
}