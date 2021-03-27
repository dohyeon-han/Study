var http = require('http');
var fs = require('fs');
var qs = require('querystring');

function listHTML(files){
  var list = '<ul>';
  files.forEach(file=>{
    if(file===`Welcome`) return;
    list+=`<li><a href="/?id=${file}">${file}</a></li>`
  })
  
  list+='</ul>';
  return list;
}

function templateHTML(title,list,data,update,del){
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
    <a href="/create">create</a>  ${update} ${del}
    <h2>${title}</h2>
    <p>${data}<p>
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
    var update = `<a href="/update?id=${id}">update</a>`
    var del = `
    <form action="delete_process" method="post" onsubmit>
      <input type='hidden' name='title' value=${id}>
      <input type='submit' value='delete'>
    </form>
    `;
    if(path==='/'){
      if(id===null){
        id = 'Welcome'
        update = '';
        del = '';
      }
      fs.readdir('data',(err,files)=>{//폴더 읽기
        var list = listHTML(files);//list변수

        fs.readFile(`data/${id}`, `utf8`, (err,data)=>{//파일 내용 읽기
          var template = templateHTML(id,list,data,update,del);
          response.writeHead(200);
          response.end(template); 
        })
      })
    }
    else if(path==='/create'){
      id = 'create';
      fs.readdir('data',(err,files)=>{//폴더 읽기
        var list = listHTML(files);//list변수

        var data = `
        <form action="/create_process" method="post">
          <p>
              <input type="text" name="title" placeholder="title">
          <p>
          <p>
              <textarea name="description" placeholder="description"></textarea>
          <p>
          <input type='submit'>
        </form>
        `
        var template = templateHTML(id,list,data,'','');
        response.writeHead(200);
        response.end(template); 
        
      })
    }
    else if(path===`/update`){//글 수정하기
      fs.readdir('data',(err,files)=>{//폴더 읽기
        var list = listHTML(files);//list변수

        fs.readFile(`data/${id}`, `utf8`, (err,data)=>{//파일 내용 읽기
          var template = templateHTML(id,list,`
          <form action="/update_process" method="post">
            <input type='hidden' name='id' value=${id}>
            <p>
              <input type="text" name="title" placeholder="title" value=${id}>
            <p>
            <p>
              <textarea name="description" placeholder="description">${data}</textarea>
            <p>
            <input type='submit'>
          </form>
          `,'',del);
          response.writeHead(200);
          response.end(template); 
        })      
      })
    }
    else if(path==='/create_process'||path==='/update_process'||path==='/delete_process'){
      var body = '';
      request.on('data',function(data){//submit한 정보 받기
        body += data;
      });
      request.on('end',function(){
        var post = qs.parse(body);
        var title = post.title;
        var description = post.description;

        
        if(path==='/update_process'){
          var id = post.id;
          fs.rename(`data/${id}`,`data/${title}`,(error)=>{
            fs.writeFile(`data/${title}`, description,'utf8', (err)=>{
              response.writeHead(302,{Location:`/?id=${title}`});
              response.end(); 
            });
          })
        }
        else if(path==='/create_process'){
          fs.writeFile(`data/${title}`, description,'utf8', (err)=>{
            response.writeHead(302,{Location:`/?id=${title}`});
            response.end(); 
          });
        }
        else{
          fs.unlink(`data/${title}`,(err)=>{
            response.writeHead(302,{Location:`/`});
            response.end(); 
          })
        }
        
      });
    }
    else{//잘못된 경로
      response.writeHead(404);
      response.end('Not found');
    }
});
app.listen(3000);