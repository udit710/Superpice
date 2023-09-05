import React, { Component, ReactElement } from 'react';
import './subcategory.css';
import logo from '../../logo.svg'; // Dummy image

export default class Subcategory extends Component {
	// props accepts subcategoryData as string to pass json data as string once apis are in place
	// May change later 

	// note: to use props in typescript react, pass the props key to the Component class like
	// "class Subcategory extends Component<{subcategoryData: string}>"

	// constructor(props: {subcategoryData: string}) {
	// 	super(props);
	//
	// }

  render() {
	const subcategories = this.dataToElements(null); // Pass subcategoryData if using that
    return (
      <div className='Subcategory'>
		{subcategories}
      </div>
    );
  }

	dataToElements(data: string | null): ReactElement[] {
		// TODO: Do something to get the data from string once apis are implemented

		const subcategories: ReactElement[] = [];

		const numCat = 10;
		for (let i:number = 0; i < numCat; i++) {
			subcategories.push(
				<div className='subcategories' id={'subcaregory-' + i} onClick={() => {alert("dummy subcategory index " + i)}}>
					<img src={logo} alt='logo' height='160' width='160' />
					<p>Dummy Item {i}</p>
				</div>
			);
		}

		return subcategories;
	}
}
