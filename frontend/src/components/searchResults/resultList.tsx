import React, { Component, ReactElement } from 'react';
import './resultList.css';
import logo from '../../logo.svg'; // Dummy image
import { Product } from '../../pages/searchResults/searchResults';

export default class ResultList extends Component<{products: Product[]}> {
  render() {
    const items = this.dataToElements(null);
    return (
      <div className='ResultList' data-testid='resultlist'>
        {items}
      </div>
    );
  }

  dataToElements(data: string | null): ReactElement[] {
		// TODO: Do something to get the data from string once apis are implemented

    const products: Product[] = this.props.products;

		const items: ReactElement[] = [];

		// const num = 10;
		// for (let i:number = 0; i < num; i++) {
    //   items.push(<hr className='item-divider' />)
		// 	items.push(
		// 		<div className='item' id={'item-' + i} onClick={() => {alert("dummy item index " + i)}}>
		// 			<img src={logo} alt='logo' height='160' width='160' />
    //       <div>
    //         <p data-testid='product_name'>Dummy Item {i}</p>
    //         <p data-testid='price'>$ 9.99</p>
    //       </div>
		// 		</div>
		// 	);
		// }

    for (let i in products) {
      items.push(<hr className='item-divider' />);
      items.push(
        <div className='item' onClick={() => {alert("dummy item index ")}} >
          <img src={products[i].images[0].imageUrl} alt='product' height='160' width='160' />
          <div>
            <p data-testid='product_name'>{products[i].productName}</p>
            <p data-testid='price'>$ {products[i].details[0].price}</p>
          </div>
        </div>
      )
    }

		return items;
	}
}
