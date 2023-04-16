package com.choimory.memberapi.member.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

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
}
