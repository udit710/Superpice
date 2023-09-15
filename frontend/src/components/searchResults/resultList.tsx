import React, { Component, ReactElement } from 'react';
import { Product } from '../../interfaces/product.interface';
import './resultList.css';

export default class ResultList extends Component<{products: Product[]}> {

  render() {
    const items = this.dataToElements(null)
    return (
      <div className='ResultList' data-testid='resultlist'>
        {items}
      </div>
    );
  }

  dataToElements(data: string | null): ReactElement[] | ReactElement {    
    if (this.props.products.length === 0) return <h2>No Results Found</h2>;

    const products: Product[] = this.props.products;

		const items: ReactElement[] = [];

    for (let i in products) {
      items.push(<hr className='item-divider' />);
      items.push(
        <a href={'/ProductDetail/' + products[i].id} >
          <div className='item'>
            <img src={products[i].images[0].imageUrl} alt='product' height='160' width='160' />
            <div>
              <p data-testid='product_name'>{products[i].productName}</p>
              <p data-testid='price'>$ {products[i].details[0].price}</p>
            </div>
          </div>
        </a>
      )
    }

		return items;
	}

}
