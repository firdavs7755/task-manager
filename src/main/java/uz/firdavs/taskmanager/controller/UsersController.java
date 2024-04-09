package uz.firdavs.taskmanager.controller;

import io.jsonwebtoken.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.payload.ReqAuth;
import uz.firdavs.taskmanager.payload.ReqUser;
import uz.firdavs.taskmanager.service.UsersService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
//@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsersController {


    private final UsersService service;
    private static String secretKey="fir4gb56w4rbn6bhr46b41t6g4w3e5423t4ghtdavsokam354ax57545844w2d4qsutaliyev";


    @GetMapping(value = "/one")
    public String getAllUserssss(){
        System.out.println("FFFFFFF");
        return "ONEEE";
    }
    @GetMapping(value = "/kirish")
    public String kirish(){
        System.out.println("kirish");
        return "signin.html";
    }


    @GetMapping(value = "/two")
    public String getss(){
        System.out.println("FFFFFFF");
        return "TWOOO";
    }

    @GetMapping(value = "/three")
    public String threer(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("THREEEE");
        System.out.println(authentication.getPrincipal());
        return "THREEEE";
    }

    @PostMapping(value = "/check")
    public ResponseDto<?> validateToken(@RequestBody String token){
        ResponseDto<?> response = new ResponseDto<>();
        try{
            Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);
//            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            response.setMessage("access token!");
            response.setSuccess(true);
            return response;
        }catch (SignatureException s){
            System.err.println("Invalid JWT signature:"+s.getMessage());
            response.setMessage("Invalid JWT signature:"+s.getMessage());
            response.setSuccess(false);
        }catch (MalformedJwtException m){
            System.err.println("Invalid JWT token:"+m.getMessage());
            response.setMessage("Invalid JWT token:"+m.getMessage());
            response.setSuccess(false);
        }catch (ExpiredJwtException e){
            System.err.println("Expired JWT token:"+e.getMessage());
            response.setMessage("Expired JWT token:"+e.getMessage());
            response.setSuccess(false);
        }catch (UnsupportedJwtException e){
            System.err.println("Unsupported JWT token:"+e.getMessage());
            response.setMessage("Unsupported JWT token:"+e.getMessage());
            response.setSuccess(false);
        }catch (IllegalArgumentException e){
            System.err.println("JWT claims string is empty token:"+e.getMessage());
            response.setMessage("JWT claims string is empty token:"+e.getMessage());
            response.setSuccess(false);
        }
        return response;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<ResponseDto<?>> getAllUsers(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("AUTH:"+authentication.toString());
        return ResponseEntity.ok(service.getAllUsers());
    }

    @PostMapping(value = "/login")
    @Operation(summary = "Login qilib token olish")
    public ResponseEntity<ResponseDto<?>> login(HttpServletRequest servletRequest,@RequestBody ReqAuth reqAuth){
        return ResponseEntity.ok(service.login(servletRequest,reqAuth));
    }
    @PostMapping(value = "/logout")
    @Operation(summary = "logout")
    public ResponseEntity<ResponseDto<?>> logout(HttpServletRequest request){
        return ResponseEntity.ok(service.logout(request));
    }

    @PostMapping(value = "/add")
    @Operation(summary = "Qo`shish")
    public ResponseEntity<ResponseDto<?>> insertUser(@RequestBody ReqUser reqUser){
        return ResponseEntity.ok(service.insertUser(reqUser));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Tahrirlash")
    public ResponseEntity<ResponseDto<?>> updateUser(@RequestBody ReqUser reqUser, @PathVariable Integer id){
        return ResponseEntity.ok(service.updateUserById(reqUser,id));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "O`chirish")
    public ResponseEntity<ResponseDto<?>> deleteUser(@PathVariable Integer id){
        return ResponseEntity.ok(service.deleteUserByID(id));
    }



}
