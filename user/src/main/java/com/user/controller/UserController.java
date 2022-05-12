package com.user.controller;

import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("user")
public class UserController {

    @Value("${spring.mail.username}")
    private String developern;
    private final PatternUtil patternUtil;
    private final SendMailService sendMailService;
    private final UserService userService;

    @PostMapping("/feedback")
    public Result<?> feedback(Feedback feedback,String token){
        //虽然配置是开发者写的，但还是判断一下
        if(!patternUtil.isEmail(developern))
            return new Result(Result.VerifyEmailMessageDefault, "系统邮箱错误",null);


        String userName = JwtUtils.verify(token);
        String id = JwtUtils.getClaim(token,"id");
        String relationship = userService.getRelationshipById(id);

        sendMailService.emailFromToTo(
                developern,
                developern,
                userName + "," + relationship + "," + feedback.getSubject(),
                feedback.getContent()
        );


        return new Result<>(Result.Success,"反馈开发者成功",null);
    }

}
