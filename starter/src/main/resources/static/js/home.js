const script = "http://localhost:8082"
const user = "http://localhost:8081"
const start = "http://localhost:8080"

//域名
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
const token = $.cookie("token");

function addTag(){
    $('#selector').val('0');
    $('#Attr').modal(
        {
            keyboard: true

        });
}
function addKey(){
    $('#selector').val('1');
    $('#Attr').modal(
        {
            keyboard: true

        });
}
function addAttr(){
    $('#selector').val('2');
    $('#Attr').modal(
        {
            keyboard: true

        });
}
$('#submit-attr-tag').click(function () {
    if($('#selector').val() == '0')
        $('#attr-value').val($('#attr-tag').val());
    else if($('#selector').val() == '1')
        $('#key-value').val($('#attr-tag').val());
    else
        $('#tag-value').val($('#attr-tag').val());
    $('#Attr').modal('hide');
    toastr.success('修改成功');
});
$('#Attr').on('hide.bs.modal',
    function() {
        $('#attr-tag').val('');
    })

$('#doSearch').click(function () {

    let option = $('#option').val();
    let url = $('#info').val();
    let tag = $('#tag-value').val();
    let key = $('#key-value').val();
    let value = $('#attr-value').val();
    switch (option) {

        case '0':
            url = escape(url);
            window.location.href = script + "/script/multi" + "?" +
            "url=" + url + "&" + "tag=" + tag + "&" +
            "&" + "key=" + key + "&" + "value=" + value;
            break;
        case '1':
            // 处理url中的 ? / &
            url = escape(url);
            // https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=hello%20world&fenlei=256&rsv_pq=bc030db80006a5bc&rsv_t=b111bEnSM%2Bra6u29%2BsNTpyHFq5njFBeq6ibjHgWPICpdZLOn4hJNEAAqFGA&rqlang=cn&rsv_enter=1&rsv_dl=tb&rsv_sug3=15&rsv_sug1=14&rsv_sug7=101&rsv_sug2=0&rsv_btype=i&inputT=3665&rsv_sug4=784588
            window.location.href = script + "/script/run" + "?" +
                "url=" + url + "&" + "tag=" + tag + "&" +
                "&" + "key=" + key + "&" + "value=" + value;
            break;
    }
});

function feedback(){
    let subject = $("#f-title").val();
    let content = $("#f-content").val();
    $.post(
        user + "/user/feedback",
        {
            token: token,
            subject: subject,
            content: content
        }
    ).done(function (data) {
        toastr.info(data.value);
    }).fail(function () {
        toastr.error("反馈请求失败！")
    })
}
function modifyModalContent(index){
    if(index === 1){
        $('#attr-tag').val($('#tag-value').val())
        $('#attr-tag').attr("placeholder","请填入标签名")
        $('#model_title').html("请填入标签名")
    }else if(index === 2){
        $('#attr-tag').val($('#key-value').val())
        $('#attr-tag').attr("placeholder","请填入属性名")
        $('#model_title').html("请填入属性名")
    }else if (index === 3){
        $('#attr-tag').val($('#attr-value').val())
        $('#attr-tag').attr("placeholder","请填入属性值")
        $('#model_title').html("请填入属性值")
    }
}

function test(selector){
       if(selector === 1){
           $('#info').val("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=helloword")
           $('#tag-value').val("span")
           $('#key-value').val("class")
           $('#attr-value').val("content-right_8Zs40")
           $('#option').val('1');$('.right').eq(0).removeClass('icon-right-1');$('.right').eq(1).addClass('icon-right-1')
       }else if(selector === 2){
           $('#info').val("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=helloword;https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu&wd=1")
           $('#tag-value').val("div")
           $('#key-value').val("class")
           $('#attr-value').val("c-container")
           $('#option').val('0');$('.right').eq(1).removeClass('icon-right-1');$('.right').eq(0).addClass('icon-right-1')
       }else{
            toastr.info("非法数据")
       }
}

download = () => {
    window.location.href = user + '/download/script'
}
