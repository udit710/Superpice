import React, { Component } from 'react';
import './footer.css';
import { FaMapMarkerAlt, FaEnvelope, FaPhone, FaGithub } from 'react-icons/fa';

export default class Footer extends Component {

  render() {
    return (
    <div className='footer'>
        <footer className="footer-content">
                <div className="footer-icons">
                    <h3>SUPERPRICE</h3>
                    <a href="https://www.google.com/maps/place/Swanston+Library/@-37.8082218,144.9610897,17z/data=!3m1!4b1!4m6!3m5!1s0x6ad642cb79f0e677:0xa47212d9b3a93e0c!8m2!3d-37.8082218!4d144.9636646!16s%2Fg%2F11bc5845pc?entry=ttu"><FaMapMarkerAlt />Building 10 RMIT University, 360 Swanston St, Melbourne VIC 3000</a>
                    <a href="#"><FaEnvelope /> SEPT-Project@rmit.edu.au</a>
                    <a href="#"><FaPhone /> +61 423 549 230</a>
                    <a href="https://github.com/cosc2299-sept-2023/team-project-group-p02-07"><FaGithub /> Github Repo</a>
                </div>
        </footer>
    </div>
    );
  }
}
