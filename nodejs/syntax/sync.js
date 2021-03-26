const fs = require('fs');

fs.readFile('syntax/sample.txt','utf8', (err,result)=>{
    console.log(result);
});
console.log('A');