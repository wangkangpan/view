//域名
const domain = 'http://localhost:8081';


//邮件验证码
$("#emailVerify").click(function () {
    //ES6 新增let和const
    let email = $('#email').val();
    $.post(domain + "/emailVerify",
        {email: email}
    ).done(
        function (res) {
            let top = 300;
            let verifyButton = $('#emailVerify');
            if(res.code != "200")
                alert(res.value);

            verifyButton.attr("disabled","disabled");

            let refreshButton = setInterval(function(){
                verifyButton.html(top + 's');
                top--;
                if(top == 0){
                    verifyButton.removeAttr("disabled");
                    verifyButton.html('发送验证');
                    clearInterval(refreshButton);
                }


            },1000);


    }).fail(function () {
        alert("发送失败");
    })

});

//注册
$('#register-button').click(function () {

    $.post(domain + "/register",{
        userName: $('input[name = "userName"]').val(),
        password: $('input[name = "password"]').val(),
        telephone: $('input[name = "telephone"]').val(),
        email: $('input[name = "email"]').val(),
        certificate: $('input[name = "certificate"]').val()
    }).done(function (res) {
        alert(res.value);
    }).fail(function () {
        alert("注册失败");
    })


});

//登录
$('#login-form').submit(function (e) {

    $.post(domain + '/login', {
            userName: $('input[name = "userName"]:eq(1)').val(),
            password: $('input[name = "password"]:eq(1)').val(),
        }).done(function (res) {

            if(res.code != "200"){
                alert(res.value);

            }else{
                window.location.href = domain + '/home'

            }

    }).fail(function () {
        alert("登陆失败");
    });
    e.preventDefault();
});
