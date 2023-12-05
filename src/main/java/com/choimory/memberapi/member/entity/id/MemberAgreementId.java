package com.choimory.memberapi.member.entity.id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class MemberAgreementId implements Serializable {
    @Column
    private Long memberId;

    //회원가입 약관 코드
    @Enumerated(EnumType.STRING)
    private AgreementCode agreementCode;

    @AllArgsConstructor
    @Getter
    public enum AgreementCode {
        JOIN_001("JOIN_001", "회원가입 약관1", "약관 설명 확인 경로");

        private final String code;
        private final String title;
        private final String explain;
    }
}
