const domain = 'http://localhost:8081/comments';
const pageSize = 10;
//页面的数量
let PageNum = 0;
//当前页
let currentNum = 1;
let AnnouncementId = 1;
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
function getItem(announcementId, announcementTitle, announcementContent, announcementTime){

    return "<div class='item'>" +
        "<div class='header'>" +
        "<div><a id = " + announcementId + ">" + announcementTitle + "</a></div>" +
        "<div></div>" +
        "</div>" +
        "<div class='description'>" +
        "<p>"  + announcementContent + "</p>" +
        "</div>" +
        "<div class='time'>" +
        "<p data-toggle='tooltip' title='Example'>发布于" + announcementTime +"</p>" +
        "</div>" +
        "<div class='ge'></div>" +
        "</div>";
}
function setAnnouncementItemsByJson(element, items){
    if (items == undefined)
        return;
    for(let index in items){

        element.append(getItem(items[index].id, items[index].title,
            items[index].content,
            items[index].createTime));
    }
}
function requestAnnouncements(element,pageNo,pageSize){
    $.post(
        domain + "/getAnnouncements",
        {
            pageNo: pageNo,
            pageSize: pageSize
        }
    ).done(function (lists) {
        setAnnouncementItemsByJson(element, lists);
    }).fail(function () {
        toastr.error('查询失败');
    });
}
function requestAnnouncementsCount(){
    //必须先设置同步，否则变量不会更新
    $.ajaxSettings.async = false;
    $.post(
        domain + "/getCountsAnnouncements"
    ).done(function (count) {
        PageNum = Math.ceil(count / pageSize);
    }).fail(function () {
        toastr.error("获取公告数量失败");
    });
    $.ajaxSettings.async = true;

}
function getAnnouncementById(id){
    let announcement = undefined;
    $.ajaxSettings.async = false;
    $.post(
        domain + "/getAnnouncementById",
        {
            id: id
        }
    ).done(function (item) {
        announcement = item;
    }).fail(function () {
        toastr.error("获取公告详细信息失败");
    });
    $.ajaxSettings.async = true;
    return announcement;
}

function setModel(title,content,footer){

    $('#model-info').on('show.bs.modal', function () {

        $('#model-title').html(title);
        $('#model-content').html(content);
        $('#model-footer').html(footer);

    });
    $('#model-info').on('hidden.bs.modal', function () {
        $('#model-title').empty();
        $('#model-content').empty();
        $('#model-footer').empty();
    })
}

//动态生成的元素必须以这种方式来操作
$("#announcement-father").on("click", ".item a", function () {
    let announcement = getAnnouncementById($(this).attr("id"));
    setModel(announcement.title,announcement.content,announcement.createTime)
    $('#model-info').modal('show');
});



//数据库需要order by data desc
//分页
$(document).ready(function (){
    requestAnnouncementsCount();
    let element = $('#announcement-father');
    if(PageNum == 0){
        toastr.error("分页异常");
        return;
    }
    requestAnnouncements(element, 1,pageSize);
    $('#pageLimit').bootstrapPaginator({
        currentPage: 1,
        totalPages: PageNum,
        size:"normal",
        bootstrapMajorVersion: 3,
        alignment:"right",
        // 一页显示几个按钮
        numberOfPages: 5 < PageNum ? 5 : PageNum,
        itemTexts: function (type, page) {
            switch (type) {
                case "first": return "首页";
                case "prev": return "上一页";
                case "next": return "下一页";
                case "last": return "末页";
                case "page": return page;
            }
        },
        onPageClicked: function (event,originalEvent,type,page) {
            currentNum = page;
            $(".item").remove();
            requestAnnouncements(element, page,pageSize);
        }

    });

});


