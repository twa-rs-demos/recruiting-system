
//'use strict';

var express = require('express');
var app = express();
var bodyParser = require('body-parser');
var route = require('./routes/route');
var yamlConfig = require('node-yaml-config');
var config = yamlConfig.load('./config/config.yml');

app.use(bodyParser.urlencoded({
  extended: false
}));

app.use(bodyParser.json());

route.setRoutes(app);

app.listen(config.port, () => {
  console.log('App listening at http://localhost:' + config.port);
});

module.exports = app;
