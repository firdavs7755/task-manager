package uz.firdavs.taskmanager.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.config.AuthenticationCheck;
import uz.firdavs.taskmanager.config.JwtService;
import uz.firdavs.taskmanager.dto.ResponseDto;
import uz.firdavs.taskmanager.entity.*;
import uz.firdavs.taskmanager.mapper.UsersMapper;
import uz.firdavs.taskmanager.payload.rq.ReqAuth;
import uz.firdavs.taskmanager.payload.rq.ReqUser;
import uz.firdavs.taskmanager.payload.rs.UserPayloadDto;
import uz.firdavs.taskmanager.repository.*;
import uz.firdavs.taskmanager.service.UsersService;
import uz.firdavs.taskmanager.specifications.UsersSpecification;
import uz.firdavs.taskmanager.utis.Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class ImplUsersService implements UsersService {

     private final UsersRepository repository;

     private final RoleRepository roleRepository;
     private final TechnologyRepository technologyRepository;
     private final EmployeeRepository employeeRepository;
     private final WishRepository wishRepository;
     private final PurposeRepository purposeRepository;
     private final EmployeePurposeRepository employeePurposeRepository;
     private final EmployeeTypeRepository employeeTypeRepository;

     private final TokenRepository tokenRepository;
     private final UsersMapper mapper;


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
            if (passwordEncoder.matches(reqAuth.getPassword(),byUsername.get().getPassword()) && byUsername.get().getUsername().equals(reqAuth.getUsername())) {
                tokenRepository.deleteByUserId(byUsername.get().getId());
                String jwt = JwtService.generateToken(reqAuth);
                Token token = new Token();
                token.set_logged_out(false);
                token.setToken(jwt);
                token.setUser(byUsername.get());
                tokenRepository.save(token);
                List<Map<String,Object>> userRoles = repository.selectUserRoles(byUsername.get().getId());

                Map<String,Object> user = new HashMap<>();
                Optional<Employee> employee = employeeRepository.findBySameUserId(byUsername.get().getId());
                Integer employee_id = null;
                if (employee.isPresent()){
                    user.put("employee_id",employee.get().getId());
                } else {
                    user.put("employee_id",employee_id);
                }

                user.put("username",byUsername.get().getUsername());
                user.put("fio",byUsername.get().getFio());
                user.put("id",byUsername.get().getId());
                UserPayloadDto userPayloadDto = new UserPayloadDto(userRoles,user);
                return new ResponseDto<>(true, "ok",jwt,userPayloadDto);
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
            tokenRepository.deleteByUserId(token.get().getUser().getId());
            System.out.println("I:"+i);
            SecurityContextHolder.clearContext();
            System.out.println("logged out !");
            return new ResponseDto<>(true,"logged out !");
        }else {
            return new ResponseDto<>(false,"Ichki xatolik ");
        }

    }

    /*super admin yaratish*/
    @Override
    public ResponseDto<?> insertUser(ReqUser reqUser) {
        Optional<Users> byUsername = repository.findByUsername(reqUser.getUsername());
        if (byUsername.isPresent()) {
            return new ResponseDto<>(false, "this username already registered");
        }
        Users users = new Users();
        users.setUsername(reqUser.getUsername());
        users.setPassword(passwordEncoder.encode(reqUser.getPassword()));
        users.setFio(reqUser.getFio());
        users.setPhone(reqUser.getPhone());
        users.setRoles(roleRepository.findAll());
        try {
            Users saved = repository.save(users);
            Employee employee = new Employee();
            employee.setName(reqUser.getFio());
            employee.setCreated_user(Utils.getUser());
            employee.setSame_user(saved);
            Optional<Wish> wish_3 = wishRepository.findById(3);
            employee.setWish(wish_3.get());
            System.out.println("ssss:"+employee.toString());
            employeeRepository.save(employee);
/*            Token token = new Token();
            token.setToken(jwtService.generateTokenn(new ReqAuth(save.getUsername(), null)));
            token.setUser(save);
            token.set_logged_out(false);
            tokenRepository.save(token);*/
            return new ResponseDto<>(true, "Successfully");
        } catch (Exception e) {
            log.error("Exp on manupulating {}" + e.getMessage());
            return new ResponseDto<>(false, "Exp on manupulating");
        }
    }

    @Override
    public ResponseDto<?> createRow(ReqUser reqUser) {
        Optional<Users> byUsername = repository.findByUsername(reqUser.getUsername());
        if (byUsername.isPresent()) {
            return new ResponseDto<>(false, "this username already registered");
        }
        Users users = new Users();
        users.setUsername(reqUser.getUsername());
        users.setPassword(passwordEncoder.encode(reqUser.getPassword()));
        users.setFio(reqUser.getFio());
        users.setPhone(reqUser.getPhone());
        List<Role> roles = roleRepository.findAllById(reqUser.getIdRoles());
        users.setRoles(roles);
        try {
            Users saved = repository.save(users);
            Employee employee = new Employee();
            employee.setName(reqUser.getFio());
            employee.setCreated_user(Utils.getUser());
            employee.setSame_user(saved);
            Optional<Wish> wish_3 = wishRepository.findById(3);
            employee.setWish(wish_3.get());
            Optional<EmployeeType> employeeType = employeeTypeRepository.findById(reqUser.getEmployee_type_id());
            if (!employeeType.isPresent()){
                return new ResponseDto<>(false,"Employe type topilmadi");
            }
            employee.setEmployeeType(employeeType.get());
            employeeRepository.save(employee);
            // Xodim kbinetiga kirganda ourposeni yangilashi un pustoy purpose yaratib quyaman
            Purpose purpose = new Purpose();
            Purpose savedPurpose = purposeRepository.save(purpose);

            EmployeePurpose employeePurpose = new EmployeePurpose();
            employeePurpose.setPurpose(savedPurpose);
            employeePurpose.setEmployee(employee);
            employeePurpose.setTechnology(technologyRepository.findById(21).get());
            employeePurposeRepository.save(employeePurpose);
            //
            return new ResponseDto<>(true, "Successfully");
        } catch (Exception e) {
            log.error("Exp on manupulating {}" + e.getMessage());
            return new ResponseDto<>(false, "Exp on manupulating");
        }
    }

    @Override
    public ResponseDto<?> updateUserById(ReqUser reqUser, Integer id) {
        Optional<Users> byUsername = repository.findById(id);
        if (!byUsername.isPresent()) {
            return new ResponseDto<>(false, "this username already registered");
        }
        Optional<Employee> bySameUserId = employeeRepository.findBySameUserId(byUsername.get().getId());
        bySameUserId.get().setName(reqUser.getFio());
        Optional<EmployeeType> employeeType = employeeTypeRepository.findById(reqUser.getEmployee_type_id());
        if (!employeeType.isPresent()){
            return new ResponseDto<>(false,"Employe type topilmadi");
        }
        bySameUserId.get().setEmployeeType(employeeType.get());
        employeeRepository.save(bySameUserId.get());

        Users users = new Users();
        users.setId(id);
        users.setUsername(reqUser.getUsername());
        users.setFio(reqUser.getFio());
        users.setPhone(reqUser.getPhone());
        users.setPassword(byUsername.get().getPassword());
        roleRepository.deleteByUserId(id);
        List<Role> roles = roleRepository.findAllById(reqUser.getIdRoles());
        users.setRoles(roles);
        try {
            repository.save(users);
            return new ResponseDto<>(true, "Successfully");
        } catch (Exception e) {
            log.error("Exp on manupulating {}" + e.getMessage());
            return new ResponseDto<>(false, "Exp on manupulating");
        }
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


    @Override
    public ResponseDto<?> findAll(Map<String, Object> map) {
        Specification<Users> specs = UsersSpecification.filterTable(map);
        Specification<Users> combinedSpecs = Specification.where(specs);
        return Utils.generatePageable(repository, combinedSpecs, mapper, map);
    }
    @Override
    public ResponseDto<?> getRowById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<?> deleteRowById(Integer id) {
        return null;
    }
}
