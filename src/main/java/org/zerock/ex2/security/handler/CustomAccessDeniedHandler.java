package org.zerock.ex2.security.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {


            Gson gson = new Gson();
            String msg = gson.toJson(Map.of("error", "ERROR_ACCESSDENIED"));

            response.setContentType("application/json");
            response.setStatus(HttpStatus.FORBIDDEN.value()); // 403
            PrintWriter printWriter = response.getWriter();
            printWriter.println(msg);
            printWriter.close();
    }

}
