/*
// ES5
console.log(foo); // undefined
var foo = 123;
console.log(foo); // 123
{
    var foo = 456;
}
console.log(foo); // 456
*/ 
/*
// ES6
let foo = 123;
{
  let foo = 456;
  let bar = 456;
}
console.log(foo); 
console.log(bar);
*/
var foo = 123;
var foo = 456;  // OK

let bar = 123;
let bar = 456;  // Uncaught SyntaxError: Identifier 'bar' has already been declared