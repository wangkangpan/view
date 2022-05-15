const script = "http://localhost:8082"
const user = "http://localhost:8081"
const start = "http://localhost:8080"

toastr.options = { // toastr配置
    "closeButton": true, //是否显示关闭按钮
    "debug": false, //是否使用debug模式
    "progressBar": true, //是否显示进度条，当为false时候不显示；当为true时候，显示进度条，当进度条缩短到0时候，消息通知弹窗消失
    "positionClass": "toast-top-full-width",
    "showDuration": "400", //显示的动画时间
    "hideDuration": "1000", //消失的动画时间
    "timeOut": "5000", //展现时间
    "extendedTimeOut": "2000", //加长展示时间
    "showEasing": "swing", //显示时的动画缓冲方式
    "hideEasing": "linear", //消失时的动画缓冲方式
    "showMethod": "fadeIn", //显示时的动画方式
    "hideMethod": "fadeOut" //消失时的动画方式
};
//邮件验证码

$("#emailVerify").click(function () {
    //ES6 新增let和const
    let email = $('#email').val();
    $.post(user + "/emailVerify",
        {email: email}
    ).done(
        function (res) {
            let top = 300;
            let verifyButton = $('#emailVerify');
            if(res.code !== "200"){
                toastr.info(res.value);
                return;
            }

            toastr.info(res.value);
            verifyButton.attr("disabled","disabled");
            let refreshButton = setInterval(function(){
                verifyButton.html(top + 's');
                top--;
                if(top === 0){
                    verifyButton.removeAttr("disabled");
                    verifyButton.html('发送验证');
                    clearInterval(refreshButton);
                }


            },1000);


    }).fail(function () {
        toastr.error("系统错误");
    })

});

//注册
$('#register-button').click(function () {

    $.post(user + "/register",{
        userName: $('input[name = "userName"]').val(),
        password: $('input[name = "password"]').val(),
        telephone: $('input[name = "telephone"]').val(),
        email: $('input[name = "email"]').val(),
        certificate: $('input[name = "certificate"]').val()
    }).done(function (res) {
        toastr.info(res.value);
    }).fail(function () {
        toastr.error("注册失败");
    })


});

//登录
$('#login-form').submit(function (e) {

    $.post(user + '/login', {
            userName: $('input[name = "userName"]:eq(1)').val(),
            password: $('input[name = "password"]:eq(1)').val(),
        }).done(function (res) {

            if(res.code !== "200"){
                toastr.info(res.value);

            }else{
                $.cookie("token", res.object,{expires:1});
                window.location.href = start + '/home';

            }

    }).fail(function (res) {
       toastr.error("服务器内部错误-certificate:" + res.value);
    });
    e.preventDefault();
});
