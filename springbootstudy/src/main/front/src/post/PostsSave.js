import {Button, Form} from "react-bootstrap";
import {Fragment, useState} from "react";
import {useNavigate, useParams} from "react-router";
import axios from "axios";

function PostsSave(){
    // const [title,author,content] = useParams();
    const [title,setTitle] = useState("");
    const [author, setAuthor] = useState("");
    const [content, setContent] = useState("");
    const navigate = useNavigate();
    const savePost=(e)=>{
        // console.log('ControlInput1');
        e.preventDefault();
        const posts = new FormData();
        posts.append('title', title)
        posts.append("author", author);
        posts.append("content", content);

        const ps = {
            title : title,
            author : author,
            content: content
        }
        const params = new URLSearchParams();
        params.append('title', "value1");
        params.append('author', "value2");
        params.append('content', "value3");


        console.log("submit "+title + " " + author + " " + content);

        console.log(posts.toString());
        axios.post("http://localhost:8080/save/",posts,{
            headers: {
                "Content-Type": "application/json"
            }
            // ***springboot 에서 json 방식이므로 선언해줘야한다.***
        })
            .then((res)=>{
                console.log("post post")
                navigate("/")
            }).catch((err)=>{
            console.log(err);
        })
    }
    const titleChange=(e)=>{
        setTitle(e.target.value);
        console.log(title);
    }
    const authorChange=(e)=>{
        setAuthor(e.target.value);
        console.log(author);
    }
    const contentChange=(e)=>{
        setContent(e.target.value);
        console.log(content);
    }
    return (
        <Fragment>
            <h1>PostSave</h1>
            <Form className={"container"} onSubmit={savePost} method={"post"}>
                <Form.Group className="mb-3" controlId="ControlInput1">
                    <Form.Label>title</Form.Label>
                    <Form.Control type="text" placeholder="input title" value={title} onChange={titleChange}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlInput2">
                    <Form.Label>author</Form.Label>
                    <Form.Control name={"author"} type="text" placeholder="input author" value={author} onChange={authorChange}/>
                </Form.Group>
                <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                    <Form.Label>content</Form.Label>
                    <Form.Control as="textarea" rows={3} value={content} onChange={contentChange}/>
                </Form.Group>
                <Button variant="primary" style={{float: 'right'}} type={"submit"}>save</Button>
                <>&nbsp;&nbsp;&nbsp;&nbsp;</>
                <Button variant="danger" style={{float: 'right'}} type={"button"} onClick={()=>navigate(-1)}>return</Button>
            </Form>
        </Fragment>
    );
}
export default PostsSave;