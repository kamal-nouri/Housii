package com.axsos.housii.housii.services;

import com.axsos.housii.housii.models.Role;
import com.axsos.housii.housii.models.User;
import com.axsos.housii.housii.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    // 1
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), getAuthorities(user));
    }
    // 2
    private List<GrantedAuthority> getAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role : user.getRoles()) {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
            authorities.add(grantedAuthority);
        }
        return authorities;
    }
}