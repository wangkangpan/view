function getItem(announcementTitle, announcementContent, announcementTime){

    return "<div class='item'>" +
        "<div class='header'>" +
        "<div><a>" + announcementTitle + "</a></div>" +
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
    for(let index in items){
        element.append(getItem(items[index].a,
            items[index].b,
            items[index].c));
    }
}

//数据库需要order by data desc
$(document).ready(function (){
    let testData =  [{"a":"xiao","b":12,"c":"2022-4-31"},
        {"a":"la","b":15,"c":"2022-4-14"},{"a":"la","b":15,"c":"2022-4-14"},
        {"a":"la","b":15,"c":"2022-4-14"},{"a":"la","b":15,"c":"2022-4-14"},
        {"a":"la","b":15,"c":"2022-4-14"},{"a":"la","b":15,"c":"2022-4-14"},
        {"a":"la","b":15,"c":"2022-4-14"},{"a":"la","b":15,"c":"2022-4-14"},
        {"a":"la","b":15,"c":"2022-4-14"}];
    let element = $('#announcement-father');
    $('#pageLimit').bootstrapPaginator({
        currentPage: 1,
        totalPages: 10,
        size:"normal",
        bootstrapMajorVersion: 3,
        alignment:"right",
        numberOfPages:5,
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first": return "首页";
                case "prev": return "上一页";
                case "next": return "下一页";
                case "last": return "末页";
                case "page": return page;
            }
        }
    });
    setAnnouncementItemsByJson(element, testData);
})

