package com.choimory.memberapi.jwt;

import com.choimory.memberapi.member.entity.Member;
import com.choimory.memberapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String identity) throws UsernameNotFoundException, NumberFormatException, NoSuchElementException {
        //회원 조회
        Member member = memberRepository.findMemberByIdentityEquals(identity)
                .orElseThrow(() -> new UsernameNotFoundException("찾을수 없는 계정입니다"));

        //시큐리티의 Authority 객체에 해당 회원의 권한과 함께 저장
        List<? extends GrantedAuthority> authorities = Stream.of(member.getMemberAuthority())
                .map(auth -> new SimpleGrantedAuthority(auth.getLevel().name()))
                .collect(Collectors.toUnmodifiableList());

        return User.builder()
                .username(member.getIdentity())
                .password(member.getPassword())
                .authorities(authorities)
                .build();
    }

    /*
    UserDetailsService 구현 안할시 사용
    public Authentication toAuthentication(String identity) throws UsernameNotFoundException, NumberFormatException, NoSuchElementException {
        //회원 조회
        Member member = memberRepository.findMemberByIdentityEquals(identity)
                .orElseThrow(() -> new UsernameNotFoundException("찾을수 없는 계정입니다"));

        //시큐리티의 Authority 객체에 해당 회원의 권한과 함께 저장
        List<? extends GrantedAuthority> authorities = Stream.of(member.getMemberAuthority())
                .map(auth -> new SimpleGrantedAuthority(auth.getLevel().name()))
                .collect(Collectors.toUnmodifiableList());

        return new UsernamePasswordAuthenticationToken(member.getIdentity(), null, authorities);
    }
    */
}
