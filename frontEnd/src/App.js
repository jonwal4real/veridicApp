
import './App.css';
import StationComponent from "./Components/StationComponent";
import HeaderComponent from './Components/HeaderComponent';
function App() {
  return (
    <div className="App">
      <header className="App-header">
        <HeaderComponent />
       <StationComponent />
      </header>
    </div>
  );
}

export default App;
