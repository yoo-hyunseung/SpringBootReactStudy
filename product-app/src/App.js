import './App.css';
import ProductDetail from "./component/ProductDetail";

function App() {

    // 객체 가져옴
    const product = {
        productId: "P100001",
        name: '리액트',
        quantity: 1,
        price : 300000,
        img: 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/React-icon.svg/1920px-React-icon.svg.png',
    };
  return (
    <div>
      <ProductDetail product={product}/>
    {/*    props 전달 */}
    </div>
  );
}

export default App;
