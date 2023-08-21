async function get() {
  const result = await axios.get(`/dust/list`)

  console.log(result)
  return result
}