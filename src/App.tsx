
import  React ,{useState} from "react";
import './App.css';


interface ChatFormat {
    sender: string;
    text: string;
}

const Chatbot = () => {
  const [userInput, setUserInput] =
      useState('');
 // const [ botResponse , setBotResponse] = useState('');
   const [chatHistory, setChatHistory] =
       useState<ChatFormat[]>([]);

const handleUserInput = (e: { target: { value: React.SetStateAction<string>; }; }) =>{
    setUserInput(e.target.value);
}
  const handleSubmit = async (e: { preventDefault: () => void; }) => {
    e.preventDefault();
  try {
      const response = await fetch('http://localhost:8080/processUserInput', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify({userInput}),
      });
      if (response.ok) {
          const data = await response.json();
          const updatedHistory: ChatFormat[] = [
              ...chatHistory,
              {  sender: 'User', text: userInput },
              {  sender: 'Bot', text: data.response },
          ];
          setChatHistory(updatedHistory);
      } else {
          console.log("Failed to fetch");
      }
  }catch(error)
      {
          console.log('Error:', error);
      }
setUserInput('');
  };


  // @ts-ignore
    return (
        <div className = "chatbot-container">
            <div>
                {chatHistory.map((message,index) => (
                    <p key={index}
                       style={{
                           textAlign: message.sender === 'User' ? 'right' : 'left',
                           margin: '5px',
                       }}>
                        <strong>{message.sender}</strong>: {message.text}
                    </p>
                ))}
            </div>
            <input type="text" value={userInput} onChange={handleUserInput}/>
            <button onClick={handleSubmit}>Send</button>
        </div>
    );
};
export default Chatbot;

