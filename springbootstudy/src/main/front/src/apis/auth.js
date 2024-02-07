import api from "./api";


// login
export const login = (username,password) =>{
    api.post("/login",{
        params:{
            username:username,
            password:password
        }
    })
}

// info
export const info =(userId)=>{
    api.get("/users/info",userId)
}
// join
export const join = (data)=>{
    api.post("/users/join",{
        params:{
            data:data
        }
    })
}
// update
export const update =(data)=>{
    api.put("/users/update",{
        params: {
            data:data
        }})
}
// remove
export const remove = (userId) =>{
    api.delete("/users/",{
        params:{
            userId:userId
        }
    })
}