import './App.css';
import ClassComponent from "./component/ClassComponent";
import FunctionComponent from "./component/FunctionComponent";
import CardList from "./props/CardList";
import LifecycleExample from "./lifecycle/LifecycleExample";
import Product from "./event/Product";

const App = () => {
    return (
        <>
            <h1>state 실습</h1>
            <ClassComponent/>
            <hr/>
            <hr/>
            <FunctionComponent/>
            <hr/>
            <h1>props 실습</h1>
            <CardList/>
            <hr/>
            <h1>LifecycleExample</h1>
            <LifecycleExample/>
            <hr/>
            <h1>이벤트 처리 예제</h1>
            <Product/>
        </>
    );
};
export default App;
