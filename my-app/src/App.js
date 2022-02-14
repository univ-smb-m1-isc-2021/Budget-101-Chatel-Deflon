import './App.css';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>Gunter - my budget 101</h1>
                {/*<img src={logo} alt="Logo"/>*/}
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
                <span id="test"></span>
            </div>
        </div>
    );
}

export default App;
