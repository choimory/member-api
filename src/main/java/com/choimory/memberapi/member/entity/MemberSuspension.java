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
public class MemberSuspension extends CommonDateTimeAt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    //사유
    private String reason;

    //정지일자
    private LocalDateTime suspendedAt;

    //정지만료일자
    private LocalDateTime suspendedTo;

    @Builder(toBuilder = true)
    public MemberSuspension(LocalDateTime createdAt, LocalDateTime modifiedAt, LocalDateTime deletedAt, Long id, Member member, String reason, LocalDateTime suspendedAt, LocalDateTime suspendedTo) {
        super(createdAt, modifiedAt, deletedAt);
        this.id = id;
        this.member = member;
        this.reason = reason;
        this.suspendedAt = suspendedAt;
        this.suspendedTo = suspendedTo;
    }
}
