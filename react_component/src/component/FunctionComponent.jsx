import React, {useState} from 'react';

const FunctionComponent = () => {
    // useState를 활용하여 상태를 관리할 수 잇다.
    // Functional Component
    const [name, setName] = useState('Aloha');

    // 버튼 클릭 시
    const handleClick = (newName)=>{
        console.log(`Click ${newName}`);

        // name update
        setName(newName);
        // setName을 javascript로
        document.getElementById('name').innerText = newName;
    }
    return (
        <div>
            <h1>함수형 컴포넌트</h1>
            <h2>Hello I'm {name}</h2>
            <h2>Hello I'm <span id={"name"}></span></h2>
            <button onClick={()=>{
                handleClick('Aloha');}}>Aloha</button>
            <button onClick={()=>{
                handleClick('Joeun');}}>Joeun</button>
        </div>
    );
};

export default FunctionComponent;