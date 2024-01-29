import {Button, Form} from "react-bootstrap";
import {Fragment, useEffect, useState} from "react";
import axios from "axios";
import {useNavigate, useParams} from "react-router";

function PostsDetail(){
    const [post, setPost] = useState([]);
    const {id} = useParams();
    const navigate = useNavigate();
    useEffect(() => {
        axios.get("http://localhost:8080/selectOne",{
            params:{
                id:id
            }
        }).then((res)=>{
            setPost(res.data)
            console.log(res.data);
        }).catch((error)=>{
            console.log(error);
        })
    }, []);
    const postsDelete=()=>{
        axios.get("http://localhost:8080/postDelete",{
            params:{
                id:id
            }
        }).then(res=>{
            alert("삭제완료")
            navigate("/");
        }).catch(err=>{
            console.log(err);
        })
    }
    return (
        <Fragment>
            <h1>PostSave</h1>
            <Form className={"container"} aria-readonly={"true"}>
                <Form.Group className="mb-3" controlId="ControlInput1">
                    <Form.Label>title</Form.Label>
                    <Form.Control type="text" placeholder="input title" value={post.title}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
                    <Form.Label>author</Form.Label>
                    <Form.Control name={"author"} type="text" placeholder="input author" value={post.author} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                    <Form.Label>content</Form.Label>
                    <Form.Control as="textarea" rows={3} value={post.content}/>
                </Form.Group>
                <>&nbsp;&nbsp;&nbsp;&nbsp;</>
                <Button variant="danger" style={{float: 'right'}} type={"button"} onClick={()=>navigate(-1)}>return</Button>
                <Button variant="danger" style={{float: 'right'}} type={"button"} onClick={postsDelete}>delete</Button>
            </Form>

        </Fragment>
    )
}
export default PostsDetail;