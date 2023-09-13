import React, { Component, ReactElement } from 'react';
import './resultList.css';
import logo from '../../logo.svg'; // Dummy image


export default class ResultList extends Component {
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

		const items: ReactElement[] = [];

		const num = 10;
		for (let i:number = 0; i < num; i++) {
      items.push(<hr className='item-divider' />)
			items.push(
				<div className='item' id={'item-' + i} onClick={() => {alert("dummy item index " + i)}}>
					<img src={logo} alt='logo' height='160' width='160' />
          <div>
            <p data-testid='product_name'>Dummy Item {i}</p>
            <p data-testid='price'>$ 9.99</p>
          </div>
				</div>
			);
		}

		return items;
	}
}
