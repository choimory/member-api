package com.choimory.memberapi.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtUserDetailService jwtUserDetailService;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /*토큰 획득*/
        String token = JwtUtil.toToken(request);

        /*토큰 유효성 검사*/
        // 토큰 형식 및 만료 확인
        boolean isTokenValid = jwtUtil.isTokenValid(token);
        String identity = jwtUtil.findIdentityFromToken(token);

        /*토큰이 유효할시 결과를 Authentification로 변환 후 시큐리티 컨테이너에 저장*/
        //TODO 유효기간 만료시 시큐릿 컨테이너의 내용은 계속 살아있는지? 직접 죽여줘야 하는지?
        //TODO 시큐릿 컨테이너의 사용량에 제한은 없는지?
        UserDetails userDetails = jwtUserDetailService.loadUserByUsername(identity);
        boolean isTokenMatched = jwtUtil.isTokenUserMatched(token, userDetails);
        if(identity != null
                && SecurityContextHolder.getContext().getAuthentication() == null
                && isTokenValid
                && isTokenMatched) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        /*필터 체이닝*/
        filterChain.doFilter(request, response);
    }

    /*
    UserDetailsService 미구현 사용시
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //토큰 획득
        String token = JwtUtil.toToken(request);

        //토큰 유효성 검사 (토큰 형식 및 만료 확인)
        boolean isTokenValid = jwtUtil.isTokenValid(token);

        // 토큰이 유효할시 결과를 Authentification로 변환 후 시큐리티 컨테이너에 저장
        //TODO 유효기간 만료시 시큐릿 컨테이너의 내용은 계속 살아있는지? 직접 죽여줘야 하는지?
        //TODO 시큐릿 컨테이너의 사용량에 제한은 없는지?
        if(isTokenValid) {
            Authentication authentication = jwtUserDetailService.toAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        //필터 체이닝
        filterChain.doFilter(request, response);
    }*/
}
