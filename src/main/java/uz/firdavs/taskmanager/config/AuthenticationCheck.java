package uz.firdavs.taskmanager.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.firdavs.taskmanager.entity.Users;
import uz.firdavs.taskmanager.repository.UsersRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationCheck implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> byUsername = usersRepository.findByUsername(username);
        if (byUsername.isPresent()){
            Users users = byUsername.get();
            List<SimpleGrantedAuthority> roles = users.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getRole().name())).collect(Collectors.toList());
            return
                    new UserPrincipal(
                            users.getId(),
                            users.getUsername(),
                            users.getPassword(),
                            users.getFio(),
                            users.getPhone(),
                            roles);
        }
        return new UserPrincipal();
    }
}
