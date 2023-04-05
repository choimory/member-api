package com.choimory.memberapi.member.entity;

import com.choimory.memberapi.common.entity.CommonDateTimeAt;
import com.choimory.memberapi.member.entity.id.MemberAgreementId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class MemberAgreement extends CommonDateTimeAt {
    @EmbeddedId
    private MemberAgreementId memberAgreementId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    //필수여부
    private Boolean isRequired;

    //동의여부
    private Boolean isAgree;

    @Builder(toBuilder = true)
    public MemberAgreement(LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime deletedAt, MemberAgreementId memberAgreementId, Member member, Boolean isRequired, Boolean isAgree) {
        super(createdAt, modifiedAt, deletedAt);
        this.memberAgreementId = memberAgreementId;
        this.member = member;
        this.isRequired = isRequired;
        this.isAgree = isAgree;
    }
}
