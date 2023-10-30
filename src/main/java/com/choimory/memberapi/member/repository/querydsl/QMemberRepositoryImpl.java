package com.choimory.memberapi.member.repository.querydsl;

import com.choimory.memberapi.common.exception.CommonException;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import static com.choimory.memberapi.member.entity.QMember.member;

@Repository
public class QMemberRepositoryImpl implements QMemberRepository{
    private final JPAQueryFactory query;

    public QMemberRepositoryImpl(JPAQueryFactory query) {
        this.query = query;
    }

    @Override
    public boolean existsByIdentityAndPasswordEquals(String identity, String password) {
        Integer result = query.selectOne()
                .from(member)
                .where(member.identity.eq(identity)
                        .and(member.password.eq(password)))
                .fetchFirst();

        return result != null;
    }

    @Override
    public boolean existsByIdentityOrEmail(String identity, String email) {
        if(!StringUtils.hasText(identity) || !StringUtils.hasText(email)){
            throw new CommonException(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase());
        }

        Integer result = query.selectOne()
                .from(member)
                .where(member.identity.eq(identity)
                        .or(member.email.eq(email)))
                .fetchFirst();

        return result != null;
    }
}
