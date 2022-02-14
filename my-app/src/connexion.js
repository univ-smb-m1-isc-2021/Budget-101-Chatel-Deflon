import './connexion.css';

function Connexion() {
    return (
        <div className="Connexion">
            <header className="Connexion-header">
                <h1 className="title-connect">Gunter - my budget 101</h1>
                {/*<img src={logo} alt="Logo"/>*/}
            </header>
            <div className="body-connect">
                <form>
                    <table className="table-connect">
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
                        </tbody>
                    </table>
                    <input className="submit-connect" name="submit" type="submit" value="Connexion"/>
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

export default Connexion;
