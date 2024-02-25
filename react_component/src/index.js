import React from 'react';                        // react 라이브러리 import
import ReactDOM from 'react-dom/client';          // ReactDOM 클라이언트 라이브러리 import
import './index.css';                             // css 설정  애플리케이션 전반의 css 파일 import
import App from './App';                          // App 컴포넌트 (최상위 컴포넌트)
import reportWebVitals from './reportWebVitals';  // 웹 성능 추적을 위한 reportWebVitals 함수 리포트

// ReactDOM.createRoot() 메소드를 호출하여 React의 루트 요소를 생성
// id 가 'root'인 React 루트 요소를 렌터링함
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  //   <React.StrictMode> -> StrictMode 활성화 하여 App 컴포넌트를 렌더링함
  <React.StrictMode>
    <App />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals(console.log());
