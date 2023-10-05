import React, { ReactElement, useEffect, useState } from 'react'
import { Product } from '../../interfaces/product.interface'
import axios from 'axios'
import OffersCard from '../../components/offers_card/offersCard';
import './offersPage.css';

interface maxProductDiscount {
	product: Product;
	maxDiscount: number;
}

export default function OffersPage() {
	const DEFAULT_MIN_OPTION = 30;

	const [products, setProducts] = useState([] as Product[]);
	const [maxDiscount, setMaxDiscount] = useState([] as maxProductDiscount[]);
	const [minDiscountOption, setMinDiscountOption] = useState(DEFAULT_MIN_OPTION as number);
	
	useEffect(() => {
		axios.get(`${process.env.REACT_APP_API_URL}/api/products/discounts`)
		.then(res => {
			setProducts(res.data);
		})
		.catch(err => {
			console.error(err);
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
			<h1 data-testid='offers-page-title'>Current Offers</h1>

			<label htmlFor="min-discount" className="form-label min-label">Minimum Discount</label><br/>
			<div data-testid='offers-page-slider' className='min-slider'>
				<input type="range" className="form-range slider" min="0" max="100" id="min-discount" defaultValue={minDiscountOption} onChange={e => {setMinDiscountOption(+e.target.value)}} />
				<p>{ minDiscountOption }%</p>
			</div>

			<div className='offers-cards'>
				{maxDiscount.map(p => {
					if (p.product.details[p.maxDiscount].discount >= minDiscountOption)
						return (
							<OffersCard product={p.product} maxIndex={p.maxDiscount} />
						)
				})}
			</div>
			
		</div>
	)

}
