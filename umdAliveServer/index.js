
var app = express()

var bodyParser = require('body-parser');

app.set("port",5000);

app.use(bodyParser.urlencoded({
	extended: true;
}));

app.use(bodyParser.json());

var clubs = new Array();//new empty array for clubs to reside
