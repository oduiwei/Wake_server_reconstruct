package education.cs.scu.controller;

import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by maicius on 2017/3/31.
 * Controller
 * 后端入口，负责与前端通信
 * UserController
 * 负责处理涉及到用户信息的请求，比如登陆、注册、设置个人信息
 */
@RestController
public class UserController {

    @Autowired
    userService userService;

    @RequestMapping(value="/LogLet", produces="text/html;charset=UTF-8")
    public String UserLogin(HttpServletRequest request,
                             @RequestParam(value="username") String userName,
                             @RequestParam(value="password") String password) throws Exception{
        AppUserInfo user = new AppUserInfo();
        user.setUserName(userName);
        user.setPassword(password);
        System.out.println(request.getCharacterEncoding());
        System.out.println(Charset.defaultCharset());
        System.out.println(userName+"昵称");

        List<AppUserInfo> loginUsers = userService.doUserLogin(user);

        if(!loginUsers.isEmpty()) {
            System.out.println(loginUsers.get(0).getNickName());
            return loginUsers.get(0).getNickName();

        }else{
            return "failed";
        }

    }

    //当返回信息含有中文时需要加上 produces = "text/html;charset=UTF-8"
    @RequestMapping(value="/RegLet", produces = "text/html;charset=UTF-8")
    public String UserRegist(@RequestParam(value="username") String userName,
                                        @RequestParam(value="password") String password,
                                        @RequestParam(value="nickname") String nickName) throws Exception{
        nickName = URLDecoder.decode(nickName, "UTF-8");
        System.out.println(nickName);
        AppUserInfo user = new AppUserInfo();
        //默认用户自我介绍为空
        String brief_intro="";
        user.setUserName(userName);
        user.setPassword(password);
        user.setNickName(nickName);
        user.setBrief_intro(brief_intro);
        int register = userService.doUserRegist(user);
        if(register ==1){
            return "success";
        }
        else{
            return "failed";
        }
    }

    @RequestMapping(value="/GetUserInfo", produces = "text/html;charset=UTF-8")
    public String GetUserInfo(@RequestParam(value="username") String userName) throws Exception{
        AppUserInfo userInfo = new AppUserInfo();
        userInfo.setUserName(userName);
        userInfo = userService.getUserInfo(userInfo);

        return userInfo.getUserInfo();
    }

    @RequestMapping(value="/SetUserInfo")
    public String SetUserInfo(@RequestParam(value="username") String userName,
                                          @RequestParam(value="nickname") String nickName,
                                          @RequestParam(value="brief_intrp") String brief_intro) throws Exception{
        nickName = URLDecoder.decode(nickName, "UTF-8");
        brief_intro = URLDecoder.decode(brief_intro, "UTF-8");
        AppUserInfo appUserInfo = new AppUserInfo();
        appUserInfo.setUserName(userName);
        appUserInfo.setNickName(nickName);
        appUserInfo.setBrief_intro(brief_intro);

        int setUserInfo = userService.setUserInfo(appUserInfo);
        if(setUserInfo > 0)
            return "success";
        else
            return "failed";
    }


}
