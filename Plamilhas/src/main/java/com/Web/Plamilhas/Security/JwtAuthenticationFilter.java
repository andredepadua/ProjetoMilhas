package com.Web.Plamilhas.Security;

import jakarta.servlet.ServletException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final UsuarioDetailsService usuarioDetailsService;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider, UsuarioDetailsService usuarioDetailsService) {
        this.tokenProvider = tokenProvider;
        this.usuarioDetailsService = usuarioDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String header = request.getHeader("Authorization");
        System.out.println("HEADER RECEBIDO = " + header);

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (tokenProvider.validarToken(token)) {
                String email = tokenProvider.obterEmailDoToken(token);
                System.out.println("TOKEN VÁLIDO. EMAIL EXTRAÍDO = " + email);
               
                try {
                   UserDetails userDetails = usuarioDetailsService.loadUserByUsername(email);
                    System.out.println("USERDETAILS CARREGADO = " + userDetails);

                      UsernamePasswordAuthenticationToken auth =
                       new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                     );

                          auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                         SecurityContextHolder.getContext().setAuthentication(auth);

                    } catch (UsernameNotFoundException e) {
                         System.out.println("ERRO: usuário não encontrado no banco: " + email);
                }


                /*UserDetails userDetails = usuarioDetailsService.loadUserByUsername(email);
                System.out.println("USERDETAILS CARREGADO = " + userDetails);

                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);*/
            }
        }
        filterChain.doFilter(req, res);
    }

   /* public UsuarioDetailsService getUsuarioDetailsService() {
        return usuarioDetailsService;
    }*/
}
