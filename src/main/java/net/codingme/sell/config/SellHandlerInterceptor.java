package net.codingme.sell.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 拦截器
 *
 * @Author niujinpeng
 * @Date 2019/8/13 12:54
 */
@Slf4j
public class SellHandlerInterceptor implements HandlerInterceptor {

    /**
     * 请求方法执行之前 返回true则通过
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        StringBuffer requestURL = request.getRequestURL();
        log.info("【拦截器】请求的url{}", requestURL.toString());
        Object openId = request.getAttribute("openId");
        if (openId == null) {
            request.setAttribute("openid", "qwertyuiop123");
            log.info("【拦截器】设置必要参数 openId:qwertyuiop123");
        }
        return true;
    }

    /**
     * 返回modelAndView之前执行
     * 
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) {
        // log.info("postHandle返回modelAndView之前");
    }

    /**
     * 执行Handler完成执行此方法
     * 
     * @param request
     * @param response
     * @param handler
     * @param ex
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
        Exception ex) {
        // log.info("afterCompletion执行完请求方法完全返回之后");
    }
}
