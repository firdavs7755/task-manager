package uz.firdavs.taskmanager.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;
import uz.firdavs.taskmanager.entity.Token;
import uz.firdavs.taskmanager.repository.TokenRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Slf4j
@Service
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    JwtService JwtService;
    @Autowired
    AuthenticationCheck authenticationCheck;
    @Autowired
    TokenRepository tokenRepository;

    @Override
    public void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwt = request.getHeader("Authorization");
        String mm = request.getMethod();
        System.out.println("JWT:"+jwt);
        System.out.println("method:"+mm);
        try {
            if (request.getServletPath().startsWith("/swagger-ui") || request.getServletPath().startsWith("/v3/api-docs") || request.getServletPath().equals("/favicon.ico")) {
                filterChain.doFilter(request, response);
                return;
            }

                if (!jwt.isEmpty()){
                Optional<Token> byToken = tokenRepository.findByToken(jwt.substring(7));
                if (byToken.isPresent() && JwtService.validateToken(jwt)) {
                    String username = JwtService.getUserNameFromJwt(jwt);
                    UserPrincipal userDetails = (UserPrincipal) authenticationCheck.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }else {
                    SecurityContextHolder.clearContext();
                }
            }

        } catch (Exception e) {
            log.error("Authorization token err : {}" + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }


}




























/*
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            if (request.getServletPath().startsWith("/swagger-ui") || request.getServletPath().startsWith("/v3/api-docs")||request.getServletPath().equals("/favicon.ico")) {
                filterChain.doFilter(request, response);
                return;
            }
            String jwt = request.getHeader("Authorization");
    //            SWAGGER GA MUROJAAT QILINGANDA TOKEN TEKSHIRIB O'TIRMASDAN ENDPOINT LAR RO'YHATINI QAYTARISH UN
                if (StringUtils.hasText(jwt) && JwtService.validateToken(jwt)) {
//                    todo shu yerda principalni tekshirish kk
                    String username = JwtService.getUserNameFromJwt(jwt);
                    UserPrincipal userDetails = (UserPrincipal) authenticationCheck.loadUserByUsername(username);

                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
        } catch (Exception e){
            log.error("Authorization token err : {}"+e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
}
*/
