const express = require('express')
const app = express()
const server = require('http').Server(app)
const io = require('socket.io')(server)

const API_PORT = 3000
const WS_PORT = 8080

const users = {}

const mainRoom = io
  .on('connection', socket => {
    console.log('Socket connected', socket.id) 
    
    users[socket.id] = {}

    // update name
    socket.on('updateName', ({ name }) => {
      users[socket.id].name = name
    })

    socket.on('sendMsg', ({ to, message}) => {
      console.log(`From ${socket.id} received message ${message}`)

      for (let [userId, _] of Object.entries(users)) {
        if (userId === socket.id)
          continue

        console.log(`Sending to ${userId}`)
        io.to(userId).emit('response', {
          from: 'TODO name',
          message: message
        })
      }
    })

    socket.on('disconnect', () => {
      console.log('Socket disconnected')
      delete users[socket.id]
    })
  })

app.get('/users', (req, res) => {
  return res.json({ users })
})

server.listen(WS_PORT, () => console.log(`WebSockets listening on port ${WS_PORT}`))
app.listen(API_PORT, () => console.log(`APIs listening on port ${API_PORT}`))