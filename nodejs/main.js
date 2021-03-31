var http = require('http');
var fs = require('fs');
var qs = require('querystring');
var p = require('path');
var template = require('./lib/template.js');

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
        var list = template.list(files);//list변수
        var filterd = p.parse(id).base;//상위 디렉토리를 입력해도 base주소만
        fs.readFile(`data/${filterd}`, `utf8`, (err,data)=>{//파일 내용 읽기
          var HTML = template.HTML(id,list,data,update,del);
          response.writeHead(200);
          response.end(HTML); 
        })
      })
    }
    else if(path==='/create'){
      id = 'create';
      fs.readdir('data',(err,files)=>{//폴더 읽기
        var list = template.list(files);//list변수
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
        var HTML = template.HTML(id,list,data,'','');
        response.writeHead(200);
        response.end(HTML); 
        
      })
    }
    else if(path===`/update`){//글 수정하기
      fs.readdir('data',(err,files)=>{//폴더 읽기
        var list = template.list(files);//list변수
        var filterd = p.parse(id).base;//상위 디렉토리를 입력해도 base주소만
        fs.readFile(`data/${filterd}`, `utf8`, (err,data)=>{//파일 내용 읽기
          var HTML = template.HTML(id,list,`
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
          response.end(HTML); 
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