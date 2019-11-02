function initialise(io, users) {
  const mainRoom = io
    .on('connection', socket => {
      console.log('Socket connected', socket.id) 
      
      socket.on('userChange', (_) => {
        io.emit('userChange', Object.values(users).map(user => user.toJSON()))
      })

      socket.on('invite', ({ to: userId }) => {
        io.to(userId).emit('invite', ({ from: socket.id }))
      })

      // update name
      socket.on('updateName', ({ name }) => {
        users[socket.id].name = name
      })

      socket.on('sendMsg', ({ to: userId, message}) => {
        console.log(`From ${socket.id} to ${userId} received message ${message}`)

        io.to(userId).emit('response', {
          from: users[socket.id].name,
          message: message
        })
      })

      socket.on('disconnect', () => {
        console.log('Socket disconnected')
        delete users[socket.id]
      })
    })
}

module.exports = {
  initialise 
}