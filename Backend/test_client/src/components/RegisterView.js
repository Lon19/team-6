import React, { Component } from 'react';
import { TextField, Grid, Typography, Button } from '@material-ui/core'

class RegisterView extends Component {

  constructor(props) {
    super(props)
    this.onChange = this.onChange.bind(this)
  }

  state = {
    name: '',
    age: '',
    cancerBefore: '',
    interests: '',
    relateToCancer: ''
  }

  onChange = name => event => {
    this.setState({ [name]: event.target.value })
  }

  render() {
    return (
      <div style={{ marginTop: '50px' }}>
        <Typography variant='h3' gutterBottom>
          Register
        </Typography>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <TextField
              onChange={this.onChange('name')}
              placeholder='Name'
              value={this.state.name}
              fullWidth
            />
          </Grid> 

          <Grid item xs={12}>
            <TextField
              onChange={this.onChange('age')}
              placeholder='Age'
              value={this.state.age}
              fullWidth
            />  
          </Grid> 

          <Grid item xs={12}>
            <TextField
              onChange={this.onChange('cancerBefore')}
              placeholder='Cancer Before?'
              value={this.state.cancerBefore}
              fullWidth
            />  
          </Grid> 

          <Grid item xs={12}>
            <TextField
              onChange={this.onChange('interests')}
              placeholder='Interests'
              value={this.state.interests}
              fullWidth
            />
          </Grid>

          <Grid item xs={12}>
            <TextField
              onChange={this.onChange('relateToCancer')}
              placeholder='How are you related to cancer?'
              value={this.state.relateToCancer}
              fullWidth
            />
          </Grid> 

          <Grid item xs={12}>
            <Button
              variant='contained'
              color='primary'
              fullWidth
              onClick={this.props.registerUser({
                socketId: this.props.getSocketId(), 
                ...this.state
              })}
            >Register</Button>
          </Grid> 

        </Grid>
      </div>
    );
  }
}

export default RegisterView;