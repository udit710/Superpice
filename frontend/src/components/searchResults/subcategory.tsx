import React, { Component, ReactElement } from 'react';
import './subcategory.css';
import { SubCategory } from '../../interfaces/subcategory.interface';
import logo from '../../logo.svg'; // Dummy image

export default class Subcategory extends Component<{subcategories: SubCategory[]}> {

  render() {
	const subcategories = this.dataToElements();
    return (
      <div className='Subcategory' data-testid='subcategory'>
		{subcategories}
      </div>
    );
  }

	dataToElements(): ReactElement[] | null {
		const subcategories: ReactElement[] = [];

		if (this.props.subcategories.length === 0) return null;

		const subs: SubCategory[] = this.props.subcategories;

		for (let i in subs) {
			subcategories.push(
				<a href="/#" className='item-link' >
					<div className='subcategories' >
						<img src={logo} alt='logo' height='160' width='160' />
						<p data-testid='subcategory-names'>{subs[i].subCategoryName}</p>
					</div>
				</a>
			)
		}

		return subcategories;
	}
}
