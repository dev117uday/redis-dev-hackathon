import React from 'react'
import ireact from '../assets/vite.svg'

function NavBar() {
    return (
        <>
            <nav className="navbar navbar-light bg-light">
                <div className="container-fluid">
                    <a className="navbar-brand" href="/">
                        <img src={ireact} width="30" height="24" className="d-inline-block align-text-top" />
                        URL Shortener
                    </a>
                    <a href="/save"><button class="btn btn-outline-success" type="submit">Save Link</button></a>
                </div>
            </nav>
        </>
    )
}

export default NavBar