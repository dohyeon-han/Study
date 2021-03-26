var http = require('http');
var fs = require('fs');


function listHTML(files){
  var list = '<ul>';
  files.forEach(file=>{
    if(file===`Welcome`) return;
    list+=`<li><a href="/?id=${file}">${file}</a></li>`
  })
  list+='</ul>';
  return list;
}

function templateHTML(title,list,data){
  return  `
  <!doctype html>
  <html>
  <head>
    <title>WEB1 - ${title}</title>
    <meta charset="utf-8">
  </head>
  <body>
    <h1><a href="/">WEB</a></h1>
    <ul>${list}</ul>
    <h2>${title}</h2>
    ${data}
  </body>
  </html>
  `;
}

var app = http.createServer(function(request,response){
    var _url = request.url;
    var baseURL = 'http://'+request.headers.host+'/';
    var myURL = new URL(_url,baseURL);
    var id = myURL.searchParams.get('id');
    var path = myURL.pathname;
    var title, data;
 
    if(path==='/'){
      title = id===null?`Welcome`:id;

      fs.readdir('data',(err,files)=>{//폴더 읽기
        var list = listHTML(files);//list변수

        fs.readFile(`data/${title}`, `utf8`, (err,data)=>{//파일 내용 읽기
          var template = templateHTML(title,list,data);
          response.writeHead(200);
          response.end(template); 
        })
      })
    }
    else{//잘못된 경로
      response.writeHead(404);
      response.end('Not found');
    }
});
app.listen(3000);