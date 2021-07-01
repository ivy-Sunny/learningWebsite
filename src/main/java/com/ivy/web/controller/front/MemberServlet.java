package com.ivy.web.controller.front;

import com.alibaba.fastjson.JSON;
import com.ivy.web.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * MemberServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-07-01
 */
@WebServlet("/member/register")
public class MemberServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、收集数据（request数据）
        Object json = JSON.parseObject(req.getInputStream(), String.class);
        //2、组织称一个实体类（Member）
        //3、调用逻辑层API
        //4、返回结果（JSON数据解析）
        resp.setContentType("application/json;charset=utf-8");
        JSON.writeJSONString(resp.getOutputStream(),"{data:'abc'}");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
