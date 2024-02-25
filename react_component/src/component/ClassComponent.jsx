import React from "react";

class ClassComponent extends React.Component {
    // 생성자 함수 -> react컴포넌트 실행시 가장 먼저 호출되는 메소드
    // 이벤트처리 메소드 바인딩, state 초기화
    // this.handleClickAloha = this.handleClickAloha.bind(this)
    constructor(props) {

        // 부모 컴포넌트의 props를 전달
        super(props);

        // 상태 정의 -> 기본값 지정
        this.state = {
            name: 'Aloha',
            age: '1'
        };

        // 컴포넌트에 함수를 바인딩 해줘야함 ** 생성된 컴포넌트에 바인딩을 해줘야 알수 있다.
        this.handleClickAloha = this.handleClickAloha.bind(this);
        this.handleClickJoeun = this.handleClickJoeun.bind(this);
    }
    // Aloha click button
    handleClickAloha(){
        console.log('Aloha Click');

        // 상태 정의
        this.setState({name : 'Aloha',age :'1'})
    }
    handleClickJoeun(){
        console.log('Joeun Click');

        // 상태 정의
        this.setState({name : 'Joeun',age :'2'})
    }
    handleClick(newName,age) {
        console.log(`Click ${newName}`);

        this.setState({name : newName, age: age})
    }



    // render() -> html을 DOM에 출력하는 메소드 필수!
    render() {
        const {name,age} = this.state
        // const name = this.state.name
        // const age = this.state.age

        return (
            <div>
                <h1>클래스 컴포넌트</h1>
                <h2>Hello I'm {name+' 나이 : '+age}</h2>
                <button onClick={this.handleClickAloha}>Aloha</button>
                <button onClick={this.handleClickJoeun}>Joeun</button>
                <button onClick={()=>{this.handleClick('handleClick Aloha',11)}}>Aloha</button>
                <button onClick={()=>{this.handleClick('handleClick Joeun',22)}}>Joeun</button>
            </div>
        );
    }
}
export default ClassComponent;