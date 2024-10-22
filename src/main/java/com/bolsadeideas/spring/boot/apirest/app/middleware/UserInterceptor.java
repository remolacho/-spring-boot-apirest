package com.bolsadeideas.spring.boot.apirest.app.middleware;

import com.bolsadeideas.spring.boot.apirest.app.controllers.ApplicationController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object controller = handlerMethod.getBean();

        if (controller instanceof ApplicationController) {
            ((ApplicationController) controller).initCurrentUser();
        }

        return true;
    }
}
