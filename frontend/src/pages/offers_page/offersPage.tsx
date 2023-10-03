import React, { useEffect, useState } from 'react'
import { Product } from '../../interfaces/product.interface'
import axios from 'axios'
import OffersCard from '../../components/offers_card/offersCard';
import './offersPage.css';

interface maxProductDiscount {
	product: Product;
	maxDiscount: number;
}
export default function OffersPage() {
	const [products, setProducts] = useState([] as Product[]);
	const [maxDiscount, setMaxDiscount] = useState([] as maxProductDiscount[]);
	
	useEffect(() => {
		axios.get(`${process.env.REACT_APP_API_URL}/api/products/discounts`)
		.then(res => {
			setProducts(res.data);
		});
		
	}, []);

	// Runs after products changes
	useEffect(() => {
		const tmp: maxProductDiscount[] = [];
		for (let i in products) {
			let maxDiscountIndex = 0;
			for (let j: number = 0; j < products[i].details.length; j++) {
				if (products[i].details[j].discount > products[i].details[maxDiscountIndex].discount) {
					maxDiscountIndex = j;
				}
			}
			tmp.push({
				product: products[i],
				maxDiscount: maxDiscountIndex
			});
		}
		setMaxDiscount(tmp);
	}, [products]);

	return (
		<div className='OffersPage'>
			<h1>Current Offers</h1>
			<div className='offers-cards'>
				{maxDiscount.map(p => (
					<OffersCard product={p.product} maxIndex={p.maxDiscount} />
				))}
			</div>
			
		</div>
	)

}
