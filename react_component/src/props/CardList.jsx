import React from 'react';
import Card from "./Card";

const CardList = () => {
    // Card 컴포넌트에 전달할 데이터
    const cardData = [
        {id: 1, title: 'card 1', content: 'Content Card 1'},
        {id: 2, title: 'card 2', content: 'Content Card 2'},
        {id: 3, title: 'card 3', content: 'Content Card 3'},
        {id: 4, title: 'card 4', content: 'Content Card 4'},
        {id: 5, title: 'card 5', content: 'Content Card 5'}
    ];
    return (
        <>
            <div>
                <h1>Card List</h1>
                <Card key={0} title={cardData[0].title} content={cardData[0].content}></Card>
                <Card key={1} title={cardData[1].title} content={cardData[1].content}></Card>
                <Card key={2} title={cardData[2].title} content={cardData[2].content}></Card>
                <Card key={3} title={cardData[3].title} content={cardData[3].content}></Card>
                <Card key={4} title={cardData[4].title} content={cardData[4].content}></Card>
            </div>
            <hr/>
            <h1>map 반복문으로 작성</h1>
            <div>
                {
                    cardData.map((value,index)=>{
                        return <Card key={value.id} title={value.title} content={value.content}></Card>
                        // key 값을 인덱스로 하는것은 권장되지 않는다.
                        // return -> 출력할 html코드
                    })
                }
            </div>
        </>
    );
};

export default CardList;