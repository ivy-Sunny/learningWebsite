package com.ivy.dao.front;

import com.ivy.domain.front.Member;
import org.apache.ibatis.annotations.Param;

public interface MemberDao {
    int save(Member member);

    Member findByName(String nickname);

    Member findByEmail(String email);

    Member findByEmailAndPwd(@Param("email") String email, @Param("password") String password);
}
