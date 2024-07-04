package com.keylin.WeCare.security.config;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    Logger log = LoggerFactory.getLogger(LoginSuccessHandler.class);

    @Autowired
    UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        log.info("onAuthenticationSuccess");

        HttpSession session = request.getSession();
        Nanny nannyDetails = null;
        Family familyDetails = null;
        try {
            log.info("Name:" + authentication.getName());

            String username = authentication.getName();
            log.info("SubString:" + username.substring(0, 1));

            if ("n".equals(username.substring(0, 1))) {
                log.info("nanny username: " + username);
                nannyDetails = userService.getNannyDetails(authentication.getName());
                log.info("Nanny details" + nannyDetails.toString());
                session.setAttribute("user", nannyDetails);
                response.sendRedirect("/loggedinN");
            } else if ("f".equals(username.substring(0, 1))) {
                familyDetails = userService.getFamilyDetails(authentication.getName());
                log.info("Family details" + familyDetails.toString());
                session.setAttribute("user", familyDetails);
                response.sendRedirect("/loggedinF");
            } else {
                response.sendRedirect("/logout");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
