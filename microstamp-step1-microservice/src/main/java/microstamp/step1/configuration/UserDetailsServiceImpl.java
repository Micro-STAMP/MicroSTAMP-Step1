package microstamp.step1.configuration;

import microstamp.step1.data.User;
import microstamp.step1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = UserRepository.getUserByUsername(s);

        if(user == null){
            throw new UsernameNotFoundException("Could not find user!");
        }

        return new MyUserDetails(user);
    }
}
