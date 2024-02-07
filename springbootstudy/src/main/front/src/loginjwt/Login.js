function Login(){
    const onLogin=()=>{

    }
    return (
        <>
            <div className={"container"}>
                로그인
                <form className={"login-form"} onClick={(e) => {
                    onLogin()
                }}>
                    <div>
                        <label htmlFor={"name"}>username</label>
                        <input type={"text"}
                               id={"username"}
                               placeholder={"username"}
                               name={"username"}
                               autoComplete={"username"}
                               required
                        />
                    </div>
                    <div>
                        <label htmlFor={"password"}>password</label>
                        <input type={"password"}
                               id={"password"}
                               name={"password"}
                               autoComplete={"password"}
                               required
                        />
                    </div>
                    <button className={"btn btn-sm btn-primary"} value={"login"}>login</button>
                </form>
            </div>
        </>
    );
}
export default Login;