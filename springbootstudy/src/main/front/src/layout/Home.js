import Col from 'react-bootstrap/Col';
import ListGroup from 'react-bootstrap/ListGroup';
import Row from 'react-bootstrap/Row';
import Tab from 'react-bootstrap/Tab';
import {Button, Table} from "react-bootstrap";
import Pagination from 'react-bootstrap/Pagination';
import {useEffect, useState} from "react";
import axios from "axios";
import {useHref, useParams} from "react-router";
import {NavLink} from "react-router-dom";
function Home(){
    // const [active,setActive] = useState(1);
    // let items = [];
    const [posts,setPosts] = useState([]);
    // const{str} = useParams();

    useEffect(()=>{
        // console.log("before " + str);
        axios.get('http://localhost:8080/select').then(res=>{
            console.log(res.data)
            setPosts(res.data);
        }).catch(error=>{
            console.log(error)
        })
    },[]);

    //for 방법 1
    const html = posts.map((data,key)=>{
        return(

            <tr>
                <td key={data.id}>{data.id}</td>
                <td><NavLink to={"/posts/detail/"+data.id}>{data.title}</NavLink></td>
                <td>{data.content}</td>
                <td>{data.author}</td>
            </tr>
        )
    });
//pagenation
    // for (let number = 1; number <= 5; number++) {
    //     items.push(
    //         <Pagination.Item key={number} active={number === active}>
    //             {number}
    //         </Pagination.Item>,
    //     );
    // }

    return (
        <>

            <br/>
            <div className="container">
                <h1>게시판 version 2 react-bootstrap</h1>
                <h1></h1>
                <Table striped bordered hover className={"table-bordered rounded"}>
                    <thead>
                    <tr>
                        <th>게시글 번호</th>
                        <th>title</th>
                        <th>author</th>
                        <th>recent update</th>
                    </tr>
                    </thead>
                    <tbody className={"table-hover"}>
                    <tr>
                        <td>1</td>
                        <td>Mark</td>
                        <td>Otto</td>
                        <td>@mdo</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Jacob</td>
                        <td>Thornton</td>
                        <td>@fat</td>
                    </tr>
                    {html}
                    </tbody>
                </Table>
                {/*<div className={"d-flex justify-content-center"}>*/}
                {/*    <Pagination style={{float:'right'}}>*/}
                {/*        {items}*/}
                {/*    </Pagination>*/}
                {/*</div>*/}
                <Button variant="dark" style={{float: 'right'}} href={"/posts/save"}>new post</Button>
            </div>


        </>
    );
}
export default Home;