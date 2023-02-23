async function get() {
  const result = await axios.get(`/dust`)

  console.log(result)
  return result
}