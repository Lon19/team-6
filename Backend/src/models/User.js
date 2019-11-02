class User {

  constructor(name, age, cancerBefore, interests, relateToCancer, socketId) {
    this.name = name
    this.age = age
    this.cancerBefore = cancerBefore
    this.interests = interests
    this.relateToCancer = relateToCancer
    this.socketId = socketId
  }

  toJSON() {
    return {
      name: this.name,
      age: this.age,
      cancerBefore: this.cancerBefore,
      interests: this.interests,
      relateToCancer: this.relateToCancer,
      socketId: this.socketId,
    }
  }

}

function fromJSON(user) {
  const { name, age, cancerBefore, interests, relateToCancer, socketId } = user

  // // TODO validation
  // if (!name || !age || !cancerBefore || !interests || !relateToCancer || !socketId)
  //   return false;

  return new User(name, age, cancerBefore, interests, relateToCancer, socketId)
}

module.exports = {
  User, fromJSON
}