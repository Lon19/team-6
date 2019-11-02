import React, { Component } from 'react';
import { Badge, Card, CardContent, CardActions, Button, Typography } from '@material-ui/core';

class MainView extends Component {

  state = {
    active: []
  }

  componentDidMount() {
    const { getSocket, getSocketId } = this.props
    getSocket().emit('userChange')
  }

  render() {
    const { getSocket, getSocketId } = this.props
    const socket = getSocket()

    socket.on('userChange', users => {
      this.setState({ active: users })
    })

    socket.on('invite', ({ from }) => {
      this.setState(prevState => ({
        active: prevState.active.map(user => (
          user.socketId === from ? { request: true, ...user } : user
        ))
      }))
    })

    return (
      <div style={{ marginTop: '50px' }}>
        <Typography variant='h3'>
          Active Users
        </Typography>
        {this.state.active
          .filter(user => user.socketId !== this.props.getSocketId())
          .map((user, key) => (
            <Card key={key}>
              <CardContent>
                <Typography variant='h5' component='h2'>
                  {user.name}
                </Typography>
                <Typography variant="body2" component="p">
                  Aged {user.age}
                  
                </Typography>
              </CardContent>
              <CardActions>
                {user.request && 
                <Badge color="primary" badgeContent={1}>
                  <Button size="small" onClick={this.props.chatWith(user.socketId, user.name)}>Chat</Button>
                </Badge>
                }
                {!user.request &&
                <Button size="small" onClick={this.props.chatWith(user.socketId, user.name)}>Chat</Button>
                }
              </CardActions>
            </Card>
          ))}
      </div>
    );
  }
}

export default MainView;