package ma.youcode.aftas.security;

import ma.youcode.aftas.models.Entities.Jury;
import ma.youcode.aftas.models.Entities.Manager;
import ma.youcode.aftas.models.Entities.Member;
import ma.youcode.aftas.repositories.JuryRepository;
import ma.youcode.aftas.repositories.ManagerRepository;
import ma.youcode.aftas.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private JuryRepository juryRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username);
        if (member != null) {

            return member;
        }

        Manager manager = managerRepository.findByEmail(username);
        if (manager != null){

            return manager;
        }

        Jury jury = juryRepository.findByEmail(username);
        if (jury != null){

            return jury;
        }

        throw new UsernameNotFoundException("User not found with the email " + username);
    }
}
