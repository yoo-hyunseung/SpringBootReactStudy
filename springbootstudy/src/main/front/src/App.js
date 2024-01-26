import './App.css';
import {useEffect, useState} from "react";
import axios from "axios";

function App() {
  const [data, setData] = useState('');
  useEffect(() => {
      axios.get("/api/test").then(res => setData(res.data))
          .catch(res => console.log("error"));
  }, []);
  return (
      <div>
        data = {data}
      </div>
  );
}

export default App;
