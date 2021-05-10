package top.lucas9.crowdfunding.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import top.lucas9.crowdfunding.constant.Constant;
import top.lucas9.crowdfunding.entity.Admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute(Constant.ATTR_NAME_LOGIN_ADMIN);
        if (null == admin) {
            request.setAttribute(Constant.ATTR_NAME_MESSAGE, Constant.MESSAGE_ACCESS_DENIED);
            request.getRequestDispatcher("/WEB-INF/admin-login.jsp").forward(request,response);
            return false;
        }
        return true;
    }
}
