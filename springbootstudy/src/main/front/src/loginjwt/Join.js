function Join(){
    const onJoin=()=>{

    }
    return (
        <>
            <div className={"container"}>
                로그인
                <form className={"login-form"} onClick={(e) => {
                    onJoin()
                }}>
                    <div>
                        <label htmlFor={"username"}>username</label>
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
                    <div>
                        <label htmlFor={"name"}>name</label>
                        <input type={"text"}
                               id={"name"}
                               placeholder={"name"}
                               name={"name"}
                               autoComplete={"name"}
                               required
                        />
                    </div>
                    <div>
                        <label htmlFor={"email"}>email</label>
                        <input type={"email"}
                               id={"email"}
                               placeholder={"email"}
                               name={"email"}
                               autoComplete={"email"}
                               required
                        />
                    </div>
                    <button className={"btn btn-sm btn-primary"} value={"login"} type={"submit"}>join</button>
                </form>
            </div>
        </>
    )
}
export default Join;