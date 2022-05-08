const domain = 'http://localhost:8081/comments';
const pageSize = 10;
//页面的数量
let PageNum = 0;
//当前页
let currentNum = 1;
let AnnouncementNum = 1;

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
function getAnnouncementItem(announcementId, announcementTitle, announcementContent, announcementTime){

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
function getCommentItem(CommentId, CommentTitle, CommentContent, CommentTime){
    if(CommentId % 2 == 0){
        return "<div id = '"+CommentId +
            "' class = 'item shadow-sharp card col-lg-4 col-lg-offset-2'>" +
            "<div class = 'header'>" +
            "<h2>" + CommentTitle + "</h2>" +
            "</div>" +
            "<div class = 'description'>" +
            "<p>" + CommentContent + "</p>" +
            "<a data-toggle='modal' href='Item' data-target='#modal' rel='noopener noreferrer' onclick='sotreCommendId(this)'>THE DETAIL</a>" +
            "</div>" +
            "</div>";
    }else{
        return "<div id = '"+ CommentId +
            "'class = 'item shadow-sharp card col-lg-4'>" +
            "<div class = 'header'>" +
            "<h2>" + CommentTitle + "</h2>" +
            "</div>" +
            "<div class = 'description'>" +
            "<p>" + CommentContent + "</p>" +
            "<a data-toggle='modal' href='Item' data-target='#modal' rel='noopener noreferrer' onclick='sotreCommendId(this)'>THE DETAIL</a>" +
            "</div>" +
            "</div>";
    }

}

function sotreCommendId(element) {
    $.cookie("commentId",$(element).parents('.item').attr('id'),1);
}

function setAnnouncementItemsByJson(element, items){
    if (items == undefined)
        return;
    for(let index in items){

        element.append(getAnnouncementItem(items[index].id, items[index].title,
            items[index].content,
            items[index].createTime));
    }
}
function setCommentItemsByJson(element, items){
    if (items == undefined)
        return;
    for(let index in items){

        element.append(getCommentItem(items[index].id, items[index].title,
            items[index].comments,
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

function requestComments(element,pageNo,pageSize){
    $.post(
        domain + "/getComments",
        {
            "pageNo": pageNo,
            "pageSize": pageSize
        }
    ).done(function (lists) {
        setCommentItemsByJson(element, lists);
    }).fail(function () {
        toastr.error('查询失败');
    });
}
//获得公告数量
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

//获得comments数量
function requestCommentsCount(){
    //必须先设置同步，否则变量不会更新
    $.ajaxSettings.async = false;
    $.post(
        domain + "/getCountsComments"
    ).done(function (count) {
        PageNum = Math.ceil(count / pageSize);
    }).fail(function () {
        toastr.error("获取公告数量失败");
    });
    $.ajaxSettings.async = true;

}
//获取公告详细信息
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


//设置一个固定格式的模态框
function setInfoModal(title,content,footer){

    $('#modal-info').on('show.bs.modal', function () {

        $('#model-title').html(title);
        $('#model-content').html(content);
        $('#model-footer').html(footer);

    });
    $('#modal-info').on('hidden.bs.modal', function () {
        $('#model-title').empty();
        $('#model-content').empty();
        $('#model-footer').empty();
    })
}

//动态新增一条公告
//动态生成的元素必须以这种方式来操作
$("#announcement-father").on("click", ".item a", function () {
    let announcement = getAnnouncementById($(this).attr("id"));
    setInfoModal(announcement.title,announcement.content,announcement.createTime)
    $('#modal-info').modal('show');
});


//数据库需要order by data desc
//分页
$(document).ready(function (){
    let element = $('#announcement-father');

    loadDIV(element,1);

});




function loadDIV(element,option){
    if(option == 1){
        requestAnnouncementsCount();
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
    }else if(option == 2){
        requestCommentsCount();
        if(PageNum == 0){
            toastr.error("分页异常");
            return;
        }
        requestComments(element,1,pageSize)
        $('#pageLimit-comments').bootstrapPaginator({
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
                AnnouncementNum = page;
                $(".item").remove();
                requestComments(element, page,pageSize);
            }
        });
    }else{
        toastr.error("option加载失败");
        return;
    }



}
$('#menu li').click(function () {
    $(".item").remove();
    let TargetHref = $(this).children("a").attr("href");
    if(TargetHref === "#announcement"){
        let element = $('#announcement-father');
        loadDIV(element,1)
    }else if(TargetHref === "#community"){
        let element = $('#community-father');
        loadDIV(element,2);
    }else{
        return ;
    }
})


$('#addOneItem').click(function () {
    let ActivityLink = $(".active").children("a").attr("href");
    if(ActivityLink === "#announcement"){
        //新增一条公告
        $("#modal-input").modal();
    }else if(ActivityLink === "#community"){
        //新增一个comments
        $("#modal-input-c").modal();
    }else{
        return ;
    }
})
addAnnouncement = () =>{
    $.post(
        domain + "/addAnnouncement",
        {
            title: $('#AnnouncementTitle').val(),
            content: $('#AnnouncementContent').val()
        }
    ).done(function (data) {
        $('#modal-input').modal('hide')
        toastr.info(data.value)
    }).fail(function () {
        toastr.error("请求错误")
    })
}
addComment = () =>{
    let userId = '1645422015492'
    $.post(
        domain + "/addReply",
        {
            userId: userId,
            title: $('#CommentTitle').val(),
            comments: $('#CommentContent').val(),
            parentId: 0
        }
    ).done(function (data) {
        $('#modal-input-c').modal('hide')
        toastr.info(data.value)
    }).fail(function () {
        toastr.error("请求错误")
    })
}


$("#modal").on("hidden.bs.modal", function() {
    $(this).removeData("bs.modal");
});

