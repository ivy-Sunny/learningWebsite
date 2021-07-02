package com.ivy.service.front.impl;

import com.ivy.dao.front.MemberDao;
import com.ivy.domain.front.Member;
import com.ivy.service.front.MemberService;
import com.ivy.utils.MD5Util;
import com.ivy.utils.MapperFactory;
import com.ivy.utils.TransactionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.UUID;

/**
 * MemberServiceImpl
 *
 * @Author: ivy
 * @CreateTime: 2021-07-01
 */
public class MemberServiceImpl implements MemberService {
    public Member login(String email, String password) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            Member member = memberDao.findByEmailAndPwd(email, MD5Util.md5(password));
            return member;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public Member findById(String id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = MapperFactory.getSqlSession();
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            Member member = memberDao.findById(id);
            return member;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public boolean register(Member member) {
        SqlSession sqlSession = null;
        try {
            boolean existMember = this.isExistMember(member.getNickName(), member.getEmail());
            if (existMember) return false;
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            //2.获取Dao
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            //id使用UUID的生成策略来获取
            String id = UUID.randomUUID().toString();
            member.setId(id);
            member.setRegisterDate(new Date());
            member.setState("1");
            member.setPassword(MD5Util.md5(member.getPassword()));
            //3.调用Dao层操作
            int row = memberDao.save(member);
            //4.提交事务
            TransactionUtil.commit(sqlSession);
            return row > 0;
        } catch (Exception e) {
            TransactionUtil.rollback(sqlSession);
            throw new RuntimeException(e);
            //记录日志
        } finally {
            try {
                TransactionUtil.close(sqlSession);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Member findByName(String nickname) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            return memberDao.findByName(nickname);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Member findByEmail(String email) {
        SqlSession sqlSession = null;
        try {
            //1.获取SqlSession
            sqlSession = MapperFactory.getSqlSession();
            MemberDao memberDao = MapperFactory.getMapper(sqlSession, MemberDao.class);
            return memberDao.findByEmail(email);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                sqlSession.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isExistMember(String nickname, String email) {
        Member byName = this.findByName(nickname);
        Member byEmail = this.findByEmail(email);
        if (byEmail != null || byName != null) {
            return true;
        }
        return false;
    }

}
