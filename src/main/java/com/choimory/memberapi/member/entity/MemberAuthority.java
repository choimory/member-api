package com.choimory.memberapi.member.entity;

import com.choimory.memberapi.common.entity.CommonDateTimeAt;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class MemberAuthority extends CommonDateTimeAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Enumerated(EnumType.STRING)
    private Level level;

    public enum Level{
        MEMBER,
        ADMIN
    }

    @Builder(toBuilder = true)
    public MemberAuthority(LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime deletedAt, Long id, Member member, Level level) {
        super(createdAt, modifiedAt, deletedAt);
        this.id = id;
        this.member = member;
        this.level = level;
    }
}
