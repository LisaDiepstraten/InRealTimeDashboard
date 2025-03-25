import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
    return (
        <nav className="navbar navbar-light bg-light">
            <div className="container-fluid">
               
                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav ms-auto"> {/* 'ms-auto' aligns items to the right */}
                    <Link to="/unified" className="navbar-brand">
                    <img 
                        src="https://basdakar.com/wp-content/uploads/2022/05/Logo-BAS-World.png" 
                        width="180" 
                        height="40" 
                        className="navbar-logo" 
                        alt="Brand Logo"
                    />
                </Link>

                        <li className='nav-item'>
                            <Link className="nav-link" to="/">Live Feed</Link>
                        </li>
                        <li className='nav=item'>
                            <Link className="nav-link" to="/slideshow"> Slide Show</Link>
                        </li>
                        {/* <li className="nav-item">
                            <Link className="nav-link" to="/pages">Product Categories</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/sessions">Sessions</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/devices">Devices</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/marketing">Marketing</Link>
                        </li>
                        <li className="nav-item">
                            <Link className="nav-link" to="/world">Locations</Link>
                        </li> */}
                    </ul>
                </div>
            </div>
        </nav>
    );
}

export default Navbar;
