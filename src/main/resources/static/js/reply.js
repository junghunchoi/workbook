async function get1(bno) {

    const result = await axios.get(`/replies/list/${bno}`)



    return result.data;
}

//goLast: 마지막 페이지 호출 여부 .. 강제적으로 마지막 댓글 페이지를 호출하도록 함
async function getList({bno, page, size, goLast}){

    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})

    return result.data
}


// async function getList({bno, page, size, goLast}){
//
//     const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})
//
//     if(goLast){
//         const total = result.data.total
//         const lastPage = parseInt(Math.ceil(total/size))
//
//         return getList({bno:bno, page:lastPage, size:size})
//
//     }
//
//     return result.data
// }


async function addReply(replyObj) {
    const response = await axios.post(`/replies/`,replyObj)
    return response.data
}

async function getReply(rno) {
    const response = await axios.get(`/replies/${rno}`)
    return response.data
}

async function modifyReply(replyObj) {

    const response = await axios.put(`/replies/${replyObj.rno}`, replyObj)
    return response.data
}

async function removeReply(rno) {
    const response = await axios.delete(`/replies/${rno}`)
    return response.data
}
