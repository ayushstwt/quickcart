package com.tier3Hub.quickCart.security;

import com.tier3Hub.quickCart.entity.User;
import com.tier3Hub.quickCart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoConfigManager implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(emailId).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserInfoConfig(user);
    }
}
