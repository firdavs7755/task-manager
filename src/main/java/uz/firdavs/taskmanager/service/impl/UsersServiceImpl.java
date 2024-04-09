package uz.firdavs.taskmanager.service.impl;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.config.AuthenticationCheck;
import uz.firdavs.taskmanager.config.JwtService;
import uz.firdavs.taskmanager.config.UserPrincipal;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.Token;
import uz.firdavs.taskmanager.entity.Users;
import uz.firdavs.taskmanager.enums.RoleNames;
import uz.firdavs.taskmanager.payload.ReqAuth;
import uz.firdavs.taskmanager.payload.ReqUser;
import uz.firdavs.taskmanager.repository.RoleRepository;
import uz.firdavs.taskmanager.repository.TokenRepository;
import uz.firdavs.taskmanager.repository.UsersRepository;
import uz.firdavs.taskmanager.service.UsersService;
import uz.firdavs.taskmanager.utis.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

     private final UsersRepository repository;

     private final RoleRepository roleRepository;

     private final TokenRepository tokenRepository;


     private final JwtService jwtService;


     private final PasswordEncoder passwordEncoder;

//    @Autowired
//    AuthenticationManager authenticationManager;
    @Override
    public ResponseDto<?> getAllUsers() {
        List<Users> all = repository.findAll();
        if (!all.isEmpty()) {
            return new ResponseDto<>(true, "access", all);
        }
        return new ResponseDto<>(false, "Nothing not found !", new ArrayList<>());
    }
    @Autowired
    AuthenticationCheck authenticationCheck;
    @Override
    public ResponseDto<?> login(HttpServletRequest request,ReqAuth reqAuth) {
        Optional<Users> byUsername = repository.findByUsername(reqAuth.getUsername());
        if (byUsername.isPresent()) {
            if (passwordEncoder.matches(reqAuth.getPassword(),byUsername.get().getPassword())) {

                String jwt = JwtService.generateToken(reqAuth);
                Token token = new Token();
                token.set_logged_out(false);
                token.setToken(jwt);
                token.setUser(byUsername.get());
                tokenRepository.save(token);
                return new ResponseDto<>(true, "ok",jwt);
            }
            return new ResponseDto<>(false, "!!!Ops password or username wrong!");
        }
        return new ResponseDto<>(false, "Not found !");
    }

    @Override
    public ResponseDto<?> logout(HttpServletRequest request) {
        Optional<Token> token = tokenRepository.findTokenByToken(jwtService.getJwtFromRequest(request).substring(7));
        if (!token.get().getToken().isEmpty()){
            int i = tokenRepository.deleteByToken(token.get().getToken());
            System.out.printf("I:"+i);
            SecurityContextHolder.clearContext();
            System.out.println("logged out !");
            return new ResponseDto<>(true,"logged out !");
        }else {
            return new ResponseDto<>(false,"Ichki xatolik ");
        }

    }

    @Override
    public ResponseDto<?> insertUser(ReqUser reqUser) {
        Optional<Users> byUsername = repository.findByUsername(reqUser.getUsername());
        if (byUsername.isPresent()) {
            return new ResponseDto<>(false, "this user already registered");
        }
        Users users = new Users();
        users.setUsername(reqUser.getUsername());
        users.setPassword(passwordEncoder.encode(reqUser.getPassword()));
        users.setFio(reqUser.getFio());
        users.setPhone(reqUser.getPhone());
        users.setRoles(roleRepository.findAllByRole(RoleNames.ROLE_STAFF));
        try {
            Users save = repository.save(users);
            Token token = new Token();
            token.setToken(jwtService.generateTokenn(new ReqAuth(save.getUsername(), null)));
            token.setUser(save);
            token.set_logged_out(false);
            tokenRepository.save(token);
            return new ResponseDto<>(true, "Successfully", save);
        } catch (Exception e) {
            log.error("Exp on manupulating {}" + e.getMessage());
            return new ResponseDto<>(false, "Exp on manupulating");
        }
    }

    @Override
    public ResponseDto<?> updateUserById(ReqUser reqUser, Integer id) {
        Optional<Users> byId = repository.findById(id);
        if (byId.isPresent()) {
            Users users = new Users();
            users.setId(id);
            users.setUsername(reqUser.getUsername());
            users.setPassword(reqUser.getPassword());
            users.setPhone(reqUser.getPhone());
            users.setFio(reqUser.getFio());

            try {
                repository.save(users);
                return new ResponseDto<>(true, "Successfully updated id:"+ id);
            } catch (Exception e) {
                log.error("Exp on manupulating {}" + e.getMessage());
                return new ResponseDto<>(false, "Exp on manupulating");
            }
        }
        return new ResponseDto<>(false, "Object not found id:" + id);

    }

    @Override
    public ResponseDto<?> deleteUserByID(Integer id) {
        Optional<Users> byId = repository.findById(id);
        if (byId.isPresent()) {
            try {
                repository.deleteById(id);
                return new ResponseDto<>(true, "Deleted id:" + id);
            } catch (Exception e) {
                log.error("Exp on manupulating {}" + e.getMessage());
                return new ResponseDto<>(false, "Exp on manupulating");
            }
        }
        return new ResponseDto<>(false, "Object not found id" + id);

    }
}
