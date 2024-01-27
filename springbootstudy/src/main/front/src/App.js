import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from "./layout/Home";

function App() {
    // const [data, setData] = useState('');
    return (
        <>
            <Router>
                <Header/>
                <Routes>
                    <Route exact path={"/"} element={<Home/>}></Route>
                </Routes>
                <Footer/>
            </Router>
        </>
    );
}

export default App;
