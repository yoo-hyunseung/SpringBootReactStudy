import {useState} from "react";

const Product=()=>{
    const [quantity, setQuantity] = useState(1);
    const price = 1000;
    const increaseQuantity = () => {
        setQuantity(quantity + 1);
    };
    const decreaseQuantity = () => {
        if (quantity > 1) {
            setQuantity(quantity - 1);
        }
    };

    const totalPrice = price * quantity;

    return (
        <div>
            <h2>상품 정보</h2>
            <ul>
                <li>가격 : {price}</li>
                <li>수량 : {quantity}</li>
                <li>총 가격 : {totalPrice}</li>
            </ul>
            <button onClick={increaseQuantity}>+</button>
            <button onClick={decreaseQuantity}>-</button>
        </div>
    );
}
export default Product;