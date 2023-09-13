import React, { Component } from "react";
import "./pricecompare.css";
import Logo_Coles from "../../assets/Logo_Coles.png";
import Logo_Woolies from "../../assets/Logo_Woolies.png";

export default class PriceCompare extends Component {
    // A table view with image , name of supermarket, price and a plus button in each column, upto 5 rows to display
    
    render() {
        return (
            <div className="PriceCompare" data-testid="pricecompare">
                <div className="container">
                    <h1 data-testid="product-name" className="productname">Milk Whole 4L - Meijer</h1>
                        <table className="table table-hover">
                            <thead>
                                <th className="available">Available at</th>
                            </thead>

                            <tbody>
                                <tr data-testid="store-data">
                                    <th scope="row"><img className="imgs" src={Logo_Coles} alt="Coles" /></th>
                                    <td className="storename"><a href = "https://www.coles.com.au/" target="_blank" rel="noopener noreferrer">Coles - Melbourne Central &rarr;</a></td>
                                    <td className="cost">$3.45</td>
                                    <td><button className="plus">+</button></td>
                                </tr>

                                <tr data-testid="store-data">
                                    <th scope="row"><img className="imgs" src={Logo_Coles} alt="Coles" /></th>
                                    <td className="storename"><a href = "https://www.coles.com.au/" target="_blank" rel="noopener noreferrer">Coles - QV &rarr;</a></td>
                                    <td className="cost">$3.45</td>
                                    <td><div className="plus">+</div></td>
                                </tr>

                                <tr data-testid="store-data">
                                    <th scope="row"><img className="imgs" src={Logo_Woolies} alt="Woolworths" /></th>
                                    <td className="storename"><a href = "https://www.woolworths.com.au/" target="_blank" rel="noopener noreferrer">Woolworths - QV &rarr;</a></td>
                                    <td className="cost">$3.55</td>
                                    <td><div className="plus">+</div></td>
                                </tr>

                                <tr data-testid="store-data">
                                    <th scope="row"><img className="imgs" src={Logo_Coles} alt="Coles" /></th>
                                    <td className="storename"><a href = "https://www.coles.com.au/" target="_blank" rel="noopener noreferrer">Coles - Flinders St &rarr;</a></td>
                                    <td className="cost">$3.95</td>
                                    <td><div className="plus">+</div></td>
                                </tr>

                                <tr data-testid="store-data">
                                    <th scope="row"><img className="imgs" src={Logo_Woolies} alt="Woolworths" /></th>
                                    <td className="storename"><a href = "https://www.woolworths.com.au/" target="_blank" rel="noopener noreferrer">Woolworths - Flinders St &rarr;</a></td>
                                    <td className="cost">$3.95</td>
                                    <td><div className="plus">+</div></td>
                                </tr>

                            </tbody>
                        </table>
                    <footer><div className="morelocations"><a href="about:blank" target="_blank" rel="noopener noreferrer">More locations...</a></div></footer>
                </div>
            </div>
        )
    }
}