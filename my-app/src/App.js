import './App.css';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>Gunter - my budget 101</h1>
            </header>
            <div>
                <form>
                    <input type="text" name="username" required/>
                    <br/>
                    <input name="submit" type="submit" value="Submit"/>
                </form>
            </div>
            <hr/>
            <div>
                <span id="test">hello</span>
            </div>
        </div>
    );
}

export default App;
