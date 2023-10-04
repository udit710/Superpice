import React, { Component, ReactElement } from 'react';
import './subcategory.css';
import { SubCategory } from '../../interfaces/subcategory.interface';

export default class Subcategory extends Component<{subcategories: SubCategory[]}> {

  render() {
	console.log(this.props.subcategories.length);
	const subcategories = this.dataToElements();
    return (
      <div className='Subcategory' data-testid='subcategory'>
				{subcategories}
      </div>
    );
  }

	dataToElements(): ReactElement[] {
		const search = window.location.search;
    const params = new URLSearchParams(search);
    const item = params.get('item');

		const subcategories: ReactElement[] = [];

		subcategories.push(
			<a href={`/search?item=${item}`} className='item-link' >
					<div className='subcategories' >
						<img src={`https://source.unsplash.com/random?groceries`} alt='logo' height='130' width='130' />
						<p data-testid='subcategory-names'>All</p>
					</div>
			</a>
		)

		if (this.props.subcategories.length === 0) return subcategories;

		const subs: SubCategory[] = this.props.subcategories;

		for (let i in subs) {
			subcategories.push(
				<a href={`/search?item=${item}&subcategory=${subs[i].subCategoryId}`} className='item-link' >
					<div className='subcategories' >
						<img src={`https://source.unsplash.com/random?${subs[i].subCategoryName}`} alt='logo' height='130' width='130' />
						<p data-testid='subcategory-names'>{subs[i].subCategoryName}</p>
					</div>
				</a>
			)
		}

		return subcategories;
	}
}
