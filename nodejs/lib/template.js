module.exports={
  HTML:function(title,list,data,update,del){
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
  },
  list:function(files){
    var list = '<ul>';
    files.forEach(file=>{
      if(file===`Welcome`) return;
      list+=`<li><a href="/?id=${file}">${file}</a></li>`
    })
    
    list+='</ul>';
    return list;
  }
}
