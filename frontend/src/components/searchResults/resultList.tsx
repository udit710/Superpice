import React, { Component, ReactElement } from 'react';
import './resultList.css';
import logo from '../../logo.svg'; // Dummy image


export default class ResultList extends Component {
  render() {
    const items = this.dataToElements(null);
    return (
      <div className='ResultList'>
        {items}
      </div>
    );
  }

  dataToElements(data: string | null): ReactElement[] {
		// TODO: Do something to get the data from string once apis are implemented

		const subcategories: ReactElement[] = [];

		const num = 10;
		for (let i:number = 0; i < num; i++) {
      subcategories.push(<hr className='item-divider' />)
			subcategories.push(
				<div className='item' id={'item-' + i} onClick={() => {alert("dummy item index " + i)}}>
					<img src={logo} alt='logo' height='160' width='160' />
          <div>
            <p>Dummy Item {i}</p>
            <p>$ 9.99</p>
          </div>
				</div>
			);
		}

		return subcategories;
	}
}
