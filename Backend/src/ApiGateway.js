function initialise(app, users) {

  // Health check
  app.get('/_health', (req, res) => res.send('OK'))

  // Get all users
  app.get('/users', (req, res) => {
    return res.json({ users })
  })
  
}

module.exports = {
  initialise
}