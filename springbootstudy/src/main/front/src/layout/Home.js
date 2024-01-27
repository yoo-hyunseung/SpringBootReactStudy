import Col from 'react-bootstrap/Col';
import ListGroup from 'react-bootstrap/ListGroup';
import Row from 'react-bootstrap/Row';
import Tab from 'react-bootstrap/Tab';
import {Button, Table} from "react-bootstrap";
import Pagination from 'react-bootstrap/Pagination';
import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router";
function Home(){
    const [active,setActive] = useState(1);
    let items = [];
    const{str} = useParams();
    const [str1, setStr1] = useState("react");

    useEffect(()=>{
        // console.log("before " + str);
        axios.get('http://localhost:8080/',{
            params:{
                str : str
            }
        }).then(res=>{
            console.log(res.data)
            setStr1(res.data)
        }).catch(error=>{
            console.log(error)
        })
    },[]);


    for (let number = 1; number <= 5; number++) {
        items.push(
            <Pagination.Item key={number} active={number === active}>
                {number}
            </Pagination.Item>,
        );
    }

    return (
        <>

            <br/>
            <div className="container">
                {/*<Tab.Container id="list-group-tabs-example" defaultActiveKey="#link1">*/}
                {/*    <Row>*/}
                {/*        <Col sm={6}>*/}
                {/*            <ListGroup variant="flush">*/}
                {/*                <ListGroup.Item action href="#link1">*/}
                {/*                    Link 1*/}
                {/*                </ListGroup.Item>*/}
                {/*                <ListGroup.Item action href="#link2">*/}
                {/*                    Link 2*/}
                {/*                </ListGroup.Item>*/}
                {/*            </ListGroup>*/}
                {/*        </Col>*/}
                {/*        <Col sm={4}>*/}
                {/*            <ListGroup variant="flush">*/}
                {/*                <ListGroup.Item action href="#link1">*/}
                {/*                    Link 1*/}
                {/*                </ListGroup.Item>*/}
                {/*                <ListGroup.Item action href="#link2">*/}
                {/*                    Link 2*/}
                {/*                </ListGroup.Item>*/}
                {/*            </ListGroup>*/}
                {/*        </Col>*/}
                {/*        <Col sm={2}>*/}
                {/*            <ListGroup variant="flush">*/}
                {/*                <ListGroup.Item action href="#link1">*/}
                {/*                    Link 1*/}
                {/*                </ListGroup.Item>*/}
                {/*                <ListGroup.Item action href="#link2">*/}
                {/*                    Link 2*/}
                {/*                </ListGroup.Item>*/}
                {/*            </ListGroup>*/}
                {/*        </Col>*/}
                {/*    </Row>*/}
                {/*</Tab.Container>*/}
                {/*<hr/>*/}
                {/*<br/>*/}

                {/*<ListGroup key="sm" horizontal={"sm"} className="my-2">*/}
                {/*    <ListGroup.Item action>This ListGroup</ListGroup.Item>*/}
                {/*    <ListGroup.Item>renders horizontally</ListGroup.Item>*/}
                {/*    <ListGroup.Item>on breakpoint</ListGroup.Item>*/}
                {/*    <ListGroup.Item>and above!</ListGroup.Item>*/}
                {/*</ListGroup>*/}
                <h1>게시판 version1 html</h1>

                <div className={"col-md-12"}>
                    <div className={"row"}>
                        <div className={"col-md-6"}>
                            <a href={"/posts/save"} role={"button"} className={"btn-primary"}>save</a>
                        </div>
                    </div>
                </div>
                <table className={"table table-horizontal table table-bordered"}>
                    <thead className={"thead-strong"}>
                    <tr>
                        <th>post number</th>
                        <th>title</th>
                        <th>author</th>
                        <th>recent update</th>
                    </tr>
                    </thead>
                    <tbody className={"text-center"}>
                    <tr>
                        <th>1</th>
                        <th>title~~</th>
                        <th>author~~</th>
                        <th>date ~~</th>
                    </tr>
                    </tbody>
                </table>
                <br/>
                <h1>게시판 version 2 react-bootstrap</h1>
                <h1>{str1}</h1>
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
                    </tbody>
                </Table>
                <div className={"d-flex justify-content-center"}>
                    <Pagination style={{float:'right'}}>
                        {items}
                    </Pagination>
                </div>
                <Button variant="dark" style={{float: 'right'}}>new post</Button>
            </div>


        </>
    );
}
export default Home;