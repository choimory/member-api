package com.choimory.memberapi.member.code;

public interface MemberValid {
    int CODE_SUSPENDED_MEMBER = 4000;
    String MESSAGE_SUSPENDED_MEMBER = "정지된 회원입니다";

    int CODE_DUPLICATED_ID_OR_EMAIL = 4001;
    String MESSAGE_DUPLICATED_ID_OR_EMAIL = "이미 가입된 아이디 혹은 이메일입니다";
}
