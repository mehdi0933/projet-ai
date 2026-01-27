package com.formationspring.demo.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

       // String path = request.getRequestURI();

        // Ignorer toutes les routes publiques
        //if (path.startsWith("/ai/") || path.equals("/user/login") || path.equals("/user/post") || path.startsWith("/user/test")) {
          //  filterChain.doFilter(request, response);
            //return;
        //}
        // Laisser passer toutes les requêtes
        filterChain.doFilter(request, response);
        //String header = request.getHeader("Authorization");
        //String token = null;
        //String username = null;

       // if (header != null && header.startsWith("Bearer ")) {
         //   token = header.substring(7);
           // try {
             //   username = jwtUtil.extractUsername(token);
            //} catch (io.jsonwebtoken.ExpiredJwtException e) {
              //  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
               // response.getWriter().write("JWT expired");
                //return;
            //} catch (Exception e) {
              //  response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                //response.getWriter().write("Invalid JWT");
                //return;
            //}
        //}

        //if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //@SuppressWarnings("unchecked")
            //List<String> roles = (List<String>) jwtUtil.extractAllClaims(token).get("roles");

            //List<SimpleGrantedAuthority> authorities = roles.stream()
                //    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
              //      .collect(Collectors.toList());

            //UsernamePasswordAuthenticationToken authToken =
             //       new UsernamePasswordAuthenticationToken(username, null, authorities);
           // authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

         //   SecurityContextHolder.getContext().setAuthentication(authToken);
       // }

        //filterChain.doFilter(request, response);
    }
}
