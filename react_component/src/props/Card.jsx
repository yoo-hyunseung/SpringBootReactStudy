import React from 'react';

const Card = (props) => {
    // props 부모 컴포넌트에서 값을 가져오기 위한 것

    return (
        <div className={'card'}>
            <h3>{props.title}</h3>
            <h3>{props.content}</h3>
        </div>
    );
};

export default Card;