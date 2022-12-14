package kh.deli.global.interceptor;

import kh.deli.global.entity.UserType;
import kh.deli.global.util.alarm.AlarmService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@AllArgsConstructor
public class DomainInterceptor implements HandlerInterceptor {

    private final HttpSession session;

    private final AlarmService alarmService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        Integer accSeq = (Integer) session.getAttribute("acc_seq");
        UserType userType = alarmService.getUserType(accSeq);

        switch (userType) {
            case NONE:
                if (
                        (uri.startsWith("/admin/")
                                || uri.startsWith("/owner/")
                                || uri.startsWith("/member/")
                                || uri.startsWith("/order/")
                                || uri.startsWith("/myPage/")
                                || uri.startsWith("/store/")
                                || uri.equals("/admin")
                                || uri.equals("/owner")
                                || uri.equals("/order")
                                || uri.equals("/myPage")
                                || uri.equals("/store")
                        ) && !(
                                // 예외
                                uri.equals("/owner/order/updateStatus")
                        )
                ) {
                    response.sendRedirect("/");
                    return false;
                }
                break;
            case ADMIN:
                if (uri.startsWith("/owner/")
                        || uri.startsWith("/member/")
                        || uri.startsWith("/order/")
                        || uri.startsWith("/myPage/")
                        || uri.startsWith("/store/")
                        || uri.equals("/owner")
                        || uri.equals("/order")
                        || uri.equals("/myPage")
                        || uri.equals("/store")
                ) {
                    response.sendRedirect("/");
                    return false;
                }
                break;
            case MEMBER:
                if (uri.startsWith("/admin/")
                        || uri.startsWith("/owner/")
                        || uri.equals("/owner")
                        || uri.equals("/admin")
                ) {
                    response.sendRedirect("/");
                    return false;
                }
                break;
            case OWNER:
                if (uri.startsWith("/admin/")
                        || uri.startsWith("/member/")
                        || uri.startsWith("/order/")
                        || uri.startsWith("/myPage/")
                        || uri.startsWith("/store/")
                        || uri.equals("/admin")
                        || uri.equals("/order")
                        || uri.equals("/myPage")
                        || uri.equals("/store")
                ) {
                    response.sendRedirect("/");
                    return false;
                }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
