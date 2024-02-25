import './App.css';
import ClassComponent from "./component/ClassComponent";
import FunctionComponent from "./component/FunctionComponent";
import CardList from "./props/CardList";
import LifecycleExample from "./lifecycle/LifecycleExample";

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
        </>
    );
};
export default App;
