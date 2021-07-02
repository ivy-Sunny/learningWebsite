package com.ivy.web;

import com.alibaba.fastjson.JSON;
import com.ivy.domain.front.Member;
import com.ivy.service.front.MemberService;
import com.ivy.service.front.impl.MemberServiceImpl;
import com.ivy.web.controller.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * BaseServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-07-01
 */
public class BaseServlet extends HttpServlet {
    protected MemberService memberService;

    @Override
    public void init() throws ServletException {
        this.memberService = new MemberServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String router = uri.substring(uri.lastIndexOf("/") + 1);
        Class clazz = this.getClass();
        try {
            Method method = clazz.getMethod(router, HttpServletRequest.class, HttpServletResponse.class);
            ResultMap ret = (ResultMap) method.invoke(this, req, resp);
            sendData(resp, ret);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
    /**
     * 接受web端数据
     *
     * @param request
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    protected final <T> T getData(HttpServletRequest request, Class<T> clazz) throws IOException {
        return JSON.parseObject(request.getInputStream(), clazz);
    }

    protected final void sendData(HttpServletResponse response, ResultMap resultMap) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(response.getOutputStream(), resultMap);
    }

    public ResultMap setResultMap() {
        return new ResultMap();
    }

    public ResultMap setResultMap(String failMsg) {
        return new ResultMap(failMsg);
    }

    public ResultMap setResultMap(String message, Object data) {
        return new ResultMap(message, data);
    }

    public ResultMap setResultMap(String message, Boolean flag, Object data, Integer code) {
        return new ResultMap(message, flag, data, code);
    }
}
