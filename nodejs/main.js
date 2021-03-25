var http = require('http');
var fs = require('fs');
var url = require('url');

var app = http.createServer(function(request,response){
    var _url = request.url;
    var baseURL = 'http://'+request.headers.host+'/';
    var myURL = new URL(_url,baseURL);
    var queryData = myURL.query;
    var path = myURL.pathname;
    var title;
 
    if(path==='/'){
      if(queryData === undefined){
        title = `welcome`;
      }
      else {
        title = queryData.id;
      }
      fs.readFile(`data/${title}`,'utf8',(err,data)=>{
        var template = `
      <!doctype html>
      <html>
      <head>
        <title>WEB1 - ${title}</title>
        <meta charset="utf-8">
      </head>
      <body>
        <h1><a href="/">WEB</a></h1>
        <ul>
          <li><a href="/?id=HTML">HTML</a></li>
          <li><a href="/?id=CSS">CSS</a></li>
          <li><a href="/?id=JavaScript">JavaScript</a></li>
        </ul>
        <h2>${title}</h2>
        ${data}
      </body>
      </html>
      `;
      response.writeHead(200);
      response.end(template);
      }); 
    }
    else{
      response.writeHead(404);
      response.end('Not found');
    }
});
app.listen(3000);