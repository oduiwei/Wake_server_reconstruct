package education.cs.scu.controller;

import education.cs.scu.entity.AppUserInfo;
import education.cs.scu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by maicius on 2017/3/31.
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping(value="/userLogin")
    public String UserLogin(HttpServletRequest request,
                             @RequestParam(value="username") String userName,
                             @RequestParam(value="password") String password) throws Exception{
        AppUserInfo user = new AppUserInfo();
        user.setUserName(userName);
        user.setPassword(password);

        System.out.println(userName);

        AppUserInfo loginUser = loginService.doUserLogin(user);
        HttpSession session = request.getSession();
        String nickName;

        if(loginUser != null) {
            session.setAttribute("user", loginUser);
            nickName = loginUser.getNickName();
            System.out.println(nickName);
            return nickName;

        }else{
            AppUserInfo wrongUser = new AppUserInfo();
            wrongUser.setNickName("该用户不存在");
            session.setAttribute("user", wrongUser);
            return "failed";
        }

    }

}
