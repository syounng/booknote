package com.sy.jwt;

import com.sy.domain.User;
import com.sy.domain.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        /* 헤더에서 토큰 추출 */
        String authHeader = request.getHeader("Authorization");

        String token = null;
        String email = null;

        /* 토큰 형식 검증 */
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7); //"Bearer " 이후의 토큰값만 가져옴
            email = jwtUtil.getEmailFromToken(token); //토큰에서 이메일 추출
        }

        /* 이메일 검증 */
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = userRepository.findByEmail(email).orElse(null);

            /* 인증 정보가 없고 토큰이 유효하면 SecurityContext에 인증 정보 설정 */
            if (user != null && jwtUtil.validateToken(token)) {
                /* 인증 정보 생성 */
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(user, null, List.of());
                /* 인증 상세 정보 설정 */
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        /* 다음 필터 실행 */
        filterChain.doFilter(request, response);
    }
}