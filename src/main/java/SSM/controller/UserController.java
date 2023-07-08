package SSM.controller;
import SSM.domain.Order;
import SSM.domain.User;
import SSM.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;


@Controller
@SessionAttributes("loginuser")
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userService")
    private UserService userService;

    //    查询登录用户
    @RequestMapping("/login")
    public String  findLoginUser(String username, String password, Model model) {
        User loginuser = userService.findLonginUser(username,password);
        if (loginuser == null) {
            // 处理登录失败逻辑
            model.addAttribute("failMsg", "用户名或密码错误，请重新登录");
            return "forward:/user_login.jsp";
        } else {
            // 处理登录成功逻辑
            model.addAttribute("loginuser", loginuser);
            return "redirect:/goods/index";
        }
    }
    @RequestMapping("/register")
    public ModelAndView addUser(User user){
        ModelAndView modelAndView = new ModelAndView();
        if (userService.register(user)){
            user.setPassword(null);
            modelAndView.addObject("msg","注册成功，请登录");
            modelAndView.setViewName("user_login");
        }else {
            modelAndView.addObject("msg","用户已存在");
            modelAndView.setViewName("user_register");
        }
        return modelAndView;
    }
    @RequestMapping("/user_changeaddress")
    public String user_changeaddress(@ModelAttribute("loginuser") User loginuser,Model model){
         userService.updateUserAddress(loginuser);
         model.addAttribute("msg","修改成功");
         return "forward:/user_center.jsp";
    }

    @RequestMapping("/user_changepwd")
    public String user_changepwd(@ModelAttribute("loginuser") User loginuser,Model model,@RequestParam(name = "password1")String password1,
                                 @RequestParam(name = "newPassword")String newPassword){
        if (password1.equals(loginuser.getPassword())){
            loginuser.setPassword(newPassword);
            userService.updatePwd(loginuser);
            model.addAttribute("msg","密码修改成功");
        }else {
            model.addAttribute("failMsg","原密码不正确");
        }
        return "forward:/user_center.jsp";
    }
    @RequestMapping("/user_logout")
    public String user_logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/goods/index";
    }

    @RequestMapping("/order_submit")
    public String order_submit(){
        return "redirect:/order_submit.jsp";
    }
}
