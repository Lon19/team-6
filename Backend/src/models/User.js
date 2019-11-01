class User {

  constructor(name, age, cancerBefore, interests, relateToCancer) {
    this.name = name
    this.age = age
    this.cancerBefore = cancerBefore
    this.interests = interests
    this.relateToCancer = relateToCancer
  }

  setSocketId(socketId) {
    this.socketId = socketId
  }

}

module.exports = User