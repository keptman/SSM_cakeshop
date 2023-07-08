package SSM.interceptor;

import SSM.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class OrderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User loginuser = (User) session.getAttribute("loginuser");
        if (loginuser==null){
            request.setAttribute("failMsg", "请登录后，再提交订单！");
            request.getRequestDispatcher("/user_login.jsp").forward(request, response);
            return false;
        }
        return true;
    }
}
