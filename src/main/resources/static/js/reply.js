async function get1(bno) {
  const result = await axios.get(`/replies/list/${bno}`)

  return result
}

async function getList({bno, page, size, goLast}){

    const result = await axios.get(`/replies/list/${bno}`, {params: {page, size}})

  // 최근 댓글이 가장 뒷 페이지에 존재한다면 마지막 페이지를 호출 할 수있도록함.
  if (goLast) {
    const total = result.data.total;
    const lastPage = parseInt(Math.ceil(total/size))
    return getList({bno:bno, page:lastPage,size:size})
  }
    return result.data;
}

async function addReply(replyObj) {
  const response = await axios.post(`/replies/`, replyObj);
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