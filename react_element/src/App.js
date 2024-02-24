// 클래스형은 컴포넌트를 상속 받아야한다.
import React from 'react';
import './App.css';
class App extends React.Component {
  render() {
      //   React javascript 엘리먼트 생성 자주 사용 하지 않는다.

      /* 엘리먼트 내부에 엘리먼트 생성 내부에서는 , 로 구분해준다.
          <div>
            <h1>hello Element</h1>
            <p>This is Element</p>
          </div>
       */
      const link = React.createElement('a', {
          href: 'http://www.google.com',
          target: '_blank',
          style: {color: 'blue'}
      }, '구글 사이트로 가기');
      const box = React.createElement('div',{
          className : 'box',
      },'Box element')

    const element = React.createElement('div', null,
        React.createElement('h1', null, 'hello Element'),
        React.createElement('p', null, 'This is Element'),
        link,
        box
    );
      // 필수 속성이 있는 경우 엘리멘트 a 태그
    // JSX 로 엘레멘트 생성시
    const element2 = (
        <div>
          <h1>hello Element2</h1>
          <p>This is Element2</p>
            <a href={'http://www.google.com'} target={'_blank'} style={{color:"blue"}}>구글 사이트로 가기</a>
            <div className={'box'}>Box element2</div>
        </div>
    )
    return element2;
  }
}

export default App;
