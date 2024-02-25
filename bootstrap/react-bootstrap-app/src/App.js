import {Button, Container} from "react-bootstrap";
// 아래꺼를 해줘야한다.
import 'bootstrap/dist/css/bootstrap.min.css'
function App() {
  return (
    <Container>
      <h1>Hello Bootstrap!!</h1>
        <Button variant={"danger"}>Click me!!</Button>
    </Container>
  );
}

export default App;
