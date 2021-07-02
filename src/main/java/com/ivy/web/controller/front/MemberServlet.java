package com.ivy.web.controller.front;

import com.alibaba.fastjson.JSON;
import com.ivy.domain.front.Member;
import com.ivy.web.BaseServlet;
import com.ivy.web.controller.ResultMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * MemberServlet
 *
 * @Author: ivy
 * @CreateTime: 2021-07-01
 */
@WebServlet("/member/*")
public class MemberServlet extends BaseServlet {
    public ResultMap register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = getData(req, Member.class);
        //2、调用逻辑层API
        boolean flag = memberService.register(member);
        ResultMap resultMap;
        if (flag) {
            resultMap = setResultMap("注册成功", null);
        } else {
            resultMap = setResultMap("用户昵称或邮箱已被占用");
        }
        //3、返回结果（JSON数据解析）
        return resultMap;
    }

    public ResultMap login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member = getData(req, Member.class);
        member = memberService.login(member.getEmail(), member.getPassword());
        ResultMap resultMap;
        //登录成功
        if (member != null) {
            resultMap = setResultMap("登录成功", member);
        } else {
            resultMap = setResultMap("用户名或密码错误，请重试！");
        }
        return resultMap;
    }

    public ResultMap checkLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Member member = getData(req, Member.class);
        return setResultMap("ok", memberService.findById(member.getId()));
    }
}
