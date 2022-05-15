//要操作的元素
let login=document.getElementById('login');
let register=document.getElementById('register');
let form_box=document.getElementsByClassName('form-box')[0];
let register_box=document.getElementsByClassName('register-box')[0];
let login_box=document.getElementsByClassName('login-box')[0];
//去注册按钮点击事件
register.addEventListener('click',()=>{
     form_box.style.transform='translateX(140%)';
     login_box.classList.add('hidden');
     register_box.classList.remove('hidden');
});
//去登陆按钮点击事件
login.addEventListener('click',()=>{
     form_box.style.transform='translateX(0%)';
     register_box.classList.add('hidden');
     login_box.classList.remove('hidden');
})
let plane = document.getElementById('plane');
let deg=0,ex=0,ey=0,vx=0,vy=0,count=0;
window.addEventListener('mousemove',(e)=>{
     ex = e.x - plane.offsetLeft - plane.clientWidth / 2;
     ey = e.y - plane.offsetTop - plane.clientHeight / 2;
     deg=360*Math.atan(ey / ex)/(2 * Math.PI) + 45;
     if(ex < 0){
          deg += 180;
     }
     count = 0;

});
function draw(){
     plane.style.transform = 'rotate('+deg+'deg)';
     if(count < 100){
          vx += ex / 100;
          vy += ey / 100;
     }
     plane.style.left = vx +'px';
     plane.style.top = vy +'px';
     count++;
}
setInterval(draw, 5);