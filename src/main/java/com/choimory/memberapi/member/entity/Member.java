package com.choimory.memberapi.member.entity;

import com.choimory.memberapi.common.entity.CommonDateTimeAt;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Member extends CommonDateTimeAt {
    //회원 번호
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //회원 아이디
    private String identity;

    //비밀번호
    private String password;

    //이메일
    private String email;

    //소개글
    private String profile;

    //회원 사진 (프사, 배경 등)
    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<MemberImage> memberImages = new ArrayList<>();

    //회원 권한
    @OneToOne(mappedBy = "member", orphanRemoval = true)
    private MemberAuthority memberAuthority;

    //회원 정지 내역
    @OneToMany(mappedBy = "member", orphanRemoval = true)
    private List<MemberSuspension> memberSuspensions = new ArrayList<>();

    //회원약관 동의
    /*@OneToMany(mappedBy = "member", orphanRemoval = true)
    private Set<MemberAgreement> memberAgreements = new HashSet<>();*/

    @Builder(toBuilder = true)
    public Member(LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime deletedAt, Long id, String identity, String password, String email, String profile, List<MemberImage> memberImages, MemberAuthority memberAuthority, List<MemberSuspension> memberSuspensions) {
        super(createdAt, modifiedAt, deletedAt);
        this.id = id;
        this.identity = identity;
        this.password = password;
        this.email = email;
        this.profile = profile;
        this.memberImages = memberImages;
        this.memberAuthority = memberAuthority;
        this.memberSuspensions = memberSuspensions;
    }
}
