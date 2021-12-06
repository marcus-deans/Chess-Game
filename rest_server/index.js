
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
    profileColor: {type: String, required: false},
    wins: {type: Number, required: false},
    losses: {type: Number, required: false}
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

async function findUser(Users, query){
    let resp = await Users.findOne(query);

    return resp;
}

app.get('/createUser', (req, res) => {
    let id = req.query.id
    let password = req.query.password

    // id = "test1"
    // password = "test"
    console.log(id)
    console.log(password)

    let query = {
        _id: id
    }

    let collectionName = "userInfo"

    res.header("Access-Control-Allow-Origin", "*")
    res.header("Access-Control-Allow-Headers", "*")

    const Users = mongoose.model(collectionName, userSchema, collectionName)

    findUser(Users, query).then(r => {
        console.log(r)

        if(!r) {
            Users.create({
                _id: id,
                password: password,
                profileColor:"",
                wins:0,
                losses:0
            })
            res.send("createduser")
        }
        else if(r.password !== password){
            res.send("wrongpassword")
        }else{
            res.send("loggedin")
        }
    })
});

app.get('/setProfileColor', (req, res) => {
    let id = req.query.id
    let color = req.query.color

    console.log(id)

    let query = {
        _id: id
    }

    let collectionName = "userInfo"

    res.header("Access-Control-Allow-Origin", "*")
    res.header("Access-Control-Allow-Headers", "*")

    const Users = mongoose.model(collectionName, userSchema, collectionName)

    Users.findOneAndUpdate(
        query,
        {profileColor: color},
        { upsert: true, new: true, setDefaultsOnInsert: true },
        function(error, result) {
            if (error){
                res.send("error")
                return;
            }
            res.send("updated")
        })
});

app.get('/getUserScore', (req, res) => {
    let id = req.query.id

    console.log(id)

    let query = {
        _id: id
    }

    let collectionName = "userInfo"

    res.header("Access-Control-Allow-Origin", "*")
    res.header("Access-Control-Allow-Headers", "*")

    const Users = mongoose.model(collectionName, userSchema, collectionName)

    Users.findById(id).then( r=> {
        res.send(r.wins + ", " + r.losses);
    })
});


app.get('/addScore', (req, res) => {
    let id = req.query.id

    console.log(id)

    let query = {
        _id: id
    }

    let collectionName = "userInfo"

    res.header("Access-Control-Allow-Origin", "*")
    res.header("Access-Control-Allow-Headers", "*")

    const Users = mongoose.model(collectionName, userSchema, collectionName)

    Users.findOneAndUpdate(
        query,
        {$inc : {wins : 1}},
        { upsert: true, new: true, setDefaultsOnInsert: true },
        function(error, result) {
            if (error){
                res.send("error")
                return;
            }
            res.send("updated")
        })
});

app.get('/subtractScore', (req, res) => {
    let id = req.query.id

    console.log(id)

    let query = {
        _id: id
    }

    let collectionName = "userInfo"

    res.header("Access-Control-Allow-Origin", "*")
    res.header("Access-Control-Allow-Headers", "*")

    const Users = mongoose.model(collectionName, userSchema, collectionName)

    Users.findOneAndUpdate(
        query,
        {$inc : {losses : 1}},
        { upsert: true, new: true, setDefaultsOnInsert: true },
        function(error, result) {
            if (error){
                res.send("error")
                return;
            }
            res.send("updated")
        })
});

// starting the server
app.listen(3001, () => {
    console.log('listening on port 3001');
});