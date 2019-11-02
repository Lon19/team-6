import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import openSocket from 'socket.io-client'
import RegisterView from './components/RegisterView';
import { Container } from '@material-ui/core';
import MainView from './components/MainView';
import ChatView from './components/ChatView';

const LOCAL_ENDPOINT = 'localhost'
const AWS_ENDPOINT = '18.200.196.115'

class App extends Component {

  state = {
    registered: false,
    chatSocketId: undefined,
    chatName: undefined
  }

  componentDidMount() {
    const socket = openSocket(`http://${AWS_ENDPOINT}:8080`)

    socket.on('connect', () => this.setState({
      socket: socket,
      socketId: socket.id
    }))
  }

  getSocket() {
    return this.state.socket
  }

  getSocketId() {
    return this.state.socketId
  }

  registerUser = userState => async event => {
    console.log(userState)
    try {
      await fetch('http://localhost:3000/user', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(userState),
      })
    } catch (error) {
      console.error(error)
      return
    }

    this.setState({ registered: true })
  }

  chatWith = (otherSocketId, otherName) => event => {
    this.getSocket().emit('invite', { to: otherSocketId })
    this.setState({ chatSocketId: otherSocketId, chatName: otherName})
  }

  render() {
    return (
      <Router>
      <div className="App">
        <Container>
        {
          !this.state.registered &&
          <RegisterView registerUser={this.registerUser.bind(this)} getSocketId={this.getSocketId.bind(this)} />
        }
        {
          this.state.registered && !this.state.chatSocketId &&
          <MainView
            getSocket={this.getSocket.bind(this)}
            getSocketId={this.getSocketId.bind(this)}
            chatWith={this.chatWith.bind(this)}
          />
        }
        {
          this.state.registered && this.state.chatSocketId && <ChatView
            getSocket={this.getSocket.bind(this)}
            getSocketId={this.getSocketId.bind(this)}
            otherSocketId={this.state.chatSocketId}
            otherName={this.state.chatName}
          />
        }
        </Container>
      </div>
      </Router>
    );
  }
}

export default App;