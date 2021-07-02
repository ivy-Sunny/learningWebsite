package com.ivy.service.front;

import com.ivy.domain.front.Member;

public interface MemberService {
    boolean register(Member member);

    Member findByName(String nickname);

    Member findByEmail(String email);

    Member login(String email, String password);

    Member findById(String id);
}
