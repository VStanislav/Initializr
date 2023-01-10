package com.voronkov.Initializr.service;

import com.voronkov.Initializr.Dao.ProductDao;
import com.voronkov.Initializr.entity.Authority;
import com.voronkov.Initializr.entity.User;
import com.voronkov.Initializr.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServise implements UserDetailsService {
    private final UserRepository userRepository;

    public Optional<User> findByUsername(String userName){
        return userRepository.findByUsername(userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s not found",username)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),mapRolesToAuthorities(user.getAuthority()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> authority){
        return authority.stream().map(authorities -> new SimpleGrantedAuthority(authorities.getName())).collect(Collectors.toList());
    }

    public void deleteBy (String name){
        userRepository.deleteById(findByUsername(name).get().getId());
    }

    public List<User> findAll(){
        return (List<User>) userRepository.findAll();
    }
}
