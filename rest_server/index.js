let mongoose = require('mongoose');

mongoose
	.connect("mongodb+srv://cs307:robertduvall@cluster0.6eljb.mongodb.net/cs307Chess?retryWrites=true&w=majority")

const express = require('express');
const bodyParser = require('body-parser');
const jsonParser = bodyParser.json()
const cors = require('cors');
const helmet = require('helmet');
const morgan = require('morgan');


let userSchema = new mongoose.Schema({
    _id: { type: String, required: true },
    password: { type: String, required: false },
  })

// defining the Express app
const app = express();

// defining an array to work as the database (temporary solution)
const ads = [
  {title: 'Hello, world (again)!'}
];

// adding Helmet to enhance your API's security
app.use(helmet());

// using bodyParser to parse JSON bodies into JS objects
app.use(bodyParser.json());

// enabling CORS for all requests
app.use(cors());

// adding morgan to log HTTP requests
app.use(morgan('combined'));

// defining an endpoint to return all ads
app.get('/', (req, res) => {
  res.send(ads);
});

app.get('/createUser', (req, res) => {
    let id = req.query.id
    let password = req.query.password

    // id = "test1"
    // password = "test"

    console.log(id)
    console.log(password)

    let collectionName = "userInfo"
    
    res.header("Access-Control-Allow-Origin", "*")
    res.header("Access-Control-Allow-Headers", "*")   

    const Users = mongoose.model(collectionName, userSchema, collectionName)

    Users.create(
        {
            _id: id,
            password:password
        }
    )
        res.send("success????")
  });

// starting the server
app.listen(3001, () => {
  console.log('listening on port 3001');
});