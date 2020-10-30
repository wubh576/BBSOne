package com.bluemsun.BBS.web.backstage.superAdmin.adminInterceptor;

import com.bluemsun.BBS.common.ServerResponse;
import com.bluemsun.BBS.entity.User;
import com.bluemsun.BBS.util.JsonUtil;
import com.bluemsun.BBS.util.RedisPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class AdminInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String loginToken = request.getHeader("token");
        String jsonStr = RedisPoolUtil.get(loginToken);
        response.setCharacterEncoding("UTF-8");// 这里要设置编码，否则会乱码
        response.setContentType("application/json;charset=UTF-8");// 这里要设置返回值的类型，因为全部是json接口。
        PrintWriter out = response.getWriter();
        response.reset();// 这里要添加reset，否则报异常 getWriter() has already been called for this response.
        if (StringUtils.isEmpty(jsonStr)) {
            out.print(JsonUtil.obj2String(ServerResponse.createByErrorNotLogin()));
            out.flush();
            out.close();
            return false;
        }
        User user = JsonUtil.string2Obj(jsonStr, User.class);
        if (user.getUserType() != 3) {
            out.print(JsonUtil.obj2String(ServerResponse.createByErrorNoAuthority()));
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}
