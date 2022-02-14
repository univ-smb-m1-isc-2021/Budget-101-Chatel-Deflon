import './App.css';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <h1>Gunter - my budget 101</h1>
                {/*<img src={logo} alt="Logo"/>*/}
            </header>
            <div>
                <h2>Connexion :</h2>
                <form>
                    <table>
                        <tbody>
                            <tr>
                                <td>
                                    <label>Pseudo : </label>
                                </td>
                                <td>
                                    <input type="text" name="username" required/>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Mot de passe : </label></td>
                                <td><input type="password" name="password" required/></td>
                            </tr>
                            <tr>
                                <td><input name="submit" type="submit" value="Connexion"/></td>
                            </tr>
                        </tbody>
                    </table>
                </form>
                <div>
                    <a href={"#"}>Mot de passe oublié</a>
                </div>
                <div>
                    <a href={"#"}>Créer un compte</a>
                </div>
            </div>
        </div>
    );
}

export default App;
