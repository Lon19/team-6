import React, { Component } from 'react';
import { BottomNavigation, TextField, Grid } from '@material-ui/core';

export default class ChatView extends Component {

  constructor(props) {
    super(props)
    console.log(props.otherSocketId)
  }

  state = {
    history: [],
    message: ''
  }

  componentDidMount() {
    this.props.getSocket().on('response', ({ from, message }) => {
      console.log('Message received', message)
      const { history } = this.state
      const newHistory = [...history,  { type: 'incoming', payload: message }]
      this.setState({ history: newHistory })
    })
  }
 
  changeMessage = event => this.setState({ message: event.target.value })
  onEnter = event => {
    if (event.keyCode === 13) {
      event.preventDefault()
      this.sendMessage()
    }
  }

  sendMessage() {
    const { message } = this.state
    const { otherSocketId } = this.props
    
    console.log(message, otherSocketId)

    this.props.getSocket().emit('sendMsg', {
      to: otherSocketId,
      message: message
    })
    
    this.setState(prevState => ({
      history: [...prevState.history, { type: 'outgoing', payload: message }],
      message: ''
    }))
  }

  render() {
    console.log(this.state.history)
    return (
      <div>
        <Grid container spacing={0} style={{ marginTop: '20px', marginBottom: '20px'}} alignItems='flex-start'>
        {
          this.state.history.map(({ type, payload }, key) => (
            <Grid
              key={key}
              container
              justify={type === 'outgoing' ? 'flex-end' : 'flex-start'}
            >
              <Grid item xs={6}>
                <strong>{type === 'outgoing' ? 'You' : this.props.otherName}</strong>: {payload}
              </Grid>
            </Grid>
          ))
        }

        </Grid>

        <TextField
            onChange={this.changeMessage.bind(this)}
            onKeyUp={this.onEnter.bind(this)}
            value={this.state.message}
            placeholder='Message'
            fullWidth
          />
      </div>
    );
  }
}
