import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";
import {BrowserRouter as Router,Routes,Route} from "react-router-dom";
import Header from "./layout/Header";
import Footer from "./layout/Footer";
import Home from "./layout/Home";
import PostsSave from "./post/PostsSave";
import PostsDetail from "./post/PostsDetail";
import Login from "./loginjwt/Login";
import Join from "./loginjwt/Join";

function App() {
    // const [data, setData] = useState('');
    return (
        <>
            <Router>
                <Header/>
                <Routes>
                    <Route exact path={"/"} element={<Home/>}></Route>
                    <Route path={"/posts/save"} element={<PostsSave/>}></Route>
                    <Route path={"/posts/detail/:id"} element={<PostsDetail/>}></Route>
                    <Route path={"/login"} element={<Login/>}></Route>
                    <Route path={"/join"} element={<Join/>}></Route>
                </Routes>
                <Footer/>
            </Router>
        </>
    );
}

export default App;
