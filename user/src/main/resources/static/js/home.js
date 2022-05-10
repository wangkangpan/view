//域名
const domain = 'http://localhost:8082';
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
token = $.cookie("token");

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
        case '0': return false;
        case '1':
            window.location.href = domain + "/script/run" + "?" +
                "url=" + url + "&" + "tag=" + tag + "&" +
                "&" + "key=" + key + "&" + "value=" + value;
    }
});

