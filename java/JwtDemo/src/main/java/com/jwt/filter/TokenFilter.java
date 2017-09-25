package com.jwt.filter;

import com.jwt.define.Constants;
import com.jwt.define.ResultBean;
import com.jwt.util.TokenUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lzp on 2017/9/16.
 */
@WebFilter(filterName = "TokenFilter",urlPatterns = "/*")
public class TokenFilter implements Filter {
    private static String[] exclude_urls={"/userLogin",".html"};
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response= (HttpServletResponse) resp;
        String url=request.getRequestURI();
        for (int i=0;i<exclude_urls.length;i++){
            if (url.endsWith(exclude_urls[i])){
                chain.doFilter(request,response);
                return;
            }
        }
        String token=request.getHeader("token");

        ResultBean result=TokenUtil.validateToken(token);
        if (result.getState().equals(Constants.VALID)){
            System.out.println("token校验合法");
            chain.doFilter(request,response);
        }
        PrintWriter writer=response.getWriter();
        writer.write(result.toString());
        writer.close();
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("初始化token过滤器");
    }

}
