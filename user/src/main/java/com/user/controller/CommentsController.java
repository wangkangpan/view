package com.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.user.entity.po.Announcement;
import com.user.entity.po.Comments;
import com.user.entity.vo.Page;
import com.user.service.CommentsService;
import com.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RequiredArgsConstructor
@RestController
@RequestMapping("comments")
public class CommentsController {

    private final CommentsService commentsService;



    @PostMapping("/getAnnouncements")
    public List<Announcement> getAnnouncements(Page page){
        return commentsService.getAllAnnouncements(page);
    }

    @PostMapping("/getCountsAnnouncements")
    public Integer getCountsAnnouncements(){
        return commentsService.getCountAnnouncements();
    }

    @PostMapping("/getAnnouncementById")
    public Announcement getAnnouncementById(@RequestParam(required = false, defaultValue = "1") String id){
        return commentsService.getAnnouncementById(id);
    }
    @PostMapping("addAnnouncement")
    public Result<Integer> addAnnouncement(Announcement announcement){
        Integer num = commentsService.addAnnouncement(announcement);
        return new Result<Integer>(Result.Success,"success",num);
    }

//    ==============================================================================
    @PostMapping("/getComments")
    public List<Comments> getComments(Page page){
        return commentsService.getAllComments(page);
    }

    @PostMapping("/getCountsComments")
    public Integer getCountsComments(){
        return commentsService.getCountComments();
    }

    @PostMapping("/replies")
    public Result<?> getReplies(){
        List<Comments> res = new ArrayList<>();
        Comments a = new Comments("1", "恰饭的好处", "恰饭好恰饭好恰饭好恰饭好恰饭好恰饭好恰饭好恰饭好", "佩克利姆1",
                new Date(), "ww010101" , "kk0101010","臭鼬1"
        );
        Comments b = new Comments("2", "恰饭的好处", "视频提供了功能强大的方法帮助您证明您的观点。当您单击联机视频时，可以在想要添加的视频的嵌入代码中进行粘贴。您也可以键入一个关键字以联机搜索最适合您的文档的视频。为使您的文档具有专业外观，Word 提供了页眉、页脚、封面和文本框设计，这些设计可互为补充。例如，您可以添加匹配的封面、页眉和提要栏。单击“插入”，然后从不同库中选择所需元素。", "佩克利姆2",
                new Date(), "ww010102" , "kk0101011","臭鼬2"
        );
        Comments c = new Comments("3", "恰饭的好处", "主题和样式也有助于文档保持协调。当您单击设计并选择新的主题时，图片、图表或 SmartArt 图形将会更改以匹配新的主题。当应用样式时，您的标题会进行更改以匹配新的主题。使用在需要位置出现的新按钮在 Word 中保存时间。若要更改图片适应文档的方式，请单击该图片，图片旁边将会显示布局选项按钮。当处理表格时，单击要添加行或列的位置，然后单击加号。", "佩克利姆3",
                new Date(), "ww010103" , "kk0101012","臭鼬3"
        );
        Comments d = new Comments("4", "恰饭的好处", "在新的阅读视图中阅读更加容易。可以折叠文档某些部分并关注所需文本。如果在达到结尾处之前需要停止读取，Word 会记住您的停止位置 - 即使在另一个设备上。视频提供了功能强大的方法帮助您证明您的观点。当您单击联机视频时，可以在想要添加的视频的嵌入代码中进行粘贴。您也可以键入一个关键字以联机搜索最适合您的文档的视频。", "佩克利姆4",
                new Date(), "ww010104" , "kk0101013","臭鼬4"
        );
        res.add(a);
        res.add(b);
        res.add(c);
        res.add(d);
        return new Result<>(Result.Success,"查询回复信息成功",res);
    }

    @PostMapping
    @RequestMapping("/comment")
    public Result<?> getComment(String id){
        Comments comments = new Comments(id,"兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草兔子不吃窝边草，不吃窝边草",new Date(),"兔子哥");
        return new Result<>(Result.Success, "查询评论信息成功",comments);
    }

}
