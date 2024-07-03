package com.keylin.WeCare.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.keylin.WeCare.entities.Authorities;
import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.entities.Users;
import com.keylin.WeCare.repositories.AuthoritiesRepository;
import com.keylin.WeCare.repositories.FamilyRepository;
import com.keylin.WeCare.repositories.NannyRepository;
import com.keylin.WeCare.repositories.UserRepository;
import com.keylin.WeCare.services.FamilyService;
import com.keylin.WeCare.services.NannyService;
import com.keylin.WeCare.services.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    // The loggers to give detailed info when running in console
    Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    // 'Injections' needed

    @Autowired
    private FamilyService familyService;

    @Autowired
    private NannyService nannyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NannyRepository nannyRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private AuthoritiesRepository authoritiesRepository;

    // Business logic, services
    @Override
    public Nanny getNannyDetails(String username) throws Exception {
        log.info("Starting to get nanny details");
        log.debug("username:" + username);
        Nanny nanny = nannyRepository.findByUsername(username);
        return nanny;
    }

    @Override
    public Family getFamilyDetails(String username) throws Exception {
        log.info("Starting to get family details");
        log.debug("username:" + username);
        Family family = familyRepository.findByUsername(username);
        return family;
    }
    // Register

    @Override
    @Transactional
    public void registerUserN(Users user, Nanny nannyDetails) throws Exception {
        log.info("Register user");
        log.debug("user", user);
        log.debug("Nanny details:", nannyDetails);
        try {
            user.setUsername("n" + user.getUsername());
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);

            // Asign role to the user, in this case, nanny role
            Authorities authorities = new Authorities();
            authorities.setUsername(user.getUsername());
            authorities.setAuthority("ROLE_N");

            // Time to asign the role(authority) in the data base
            authoritiesRepository.save(authorities);
            log.debug("authorities:", authorities);
            nannyDetails.setUsername("n" + nannyDetails.getUsername());
            nannyRepository.save(nannyDetails);

            log.info("User {} registered successfully with 'ROLE_N'", user.getUsername());
        } catch (Exception e) {
            log.error("Error when trying to register the user.", e);
        }
    }

    @Override
    @Transactional
    public void registerUserF(Users user, Family familyDetails) throws Exception {
        log.info("Register user");
        log.debug("user", user);
        log.debug("Family details:", familyDetails);
        try {
            user.setUsername("f" + user.getUsername());
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userRepository.save(user);

            // Asign role to the user, in this case, family role
            Authorities authorities = new Authorities();
            authorities.setUsername(user.getUsername());
            authorities.setAuthority("ROLE_F");

            // Time to asign the role(authority) in the data base
            authoritiesRepository.save(authorities);
            log.debug("authorities:", authorities);
            familyDetails.setUsername("f" + familyDetails.getUsername());
            familyRepository.save(familyDetails);

            log.info("User {} registered successfully with 'ROLE_F'", user.getUsername());
        } catch (Exception e) {
            log.error("Error when trying to register the user", e);
        }
    }

    // Edit

    @Override
    public void editUserF(Long id, Family family) throws Exception {
        log.info("Edit user");
        log.debug("Family details:", family);
        try {
            familyService.updateFamily(id, family);
        } catch (Exception e) {
            log.error("Error when trying to register the user", e);
        }
    }

    // Edit

    @Override
    public void editUserN(Long id, Nanny nanny) throws Exception {
        log.info("Edit user");
        log.debug("nanny details:", nanny);
        try {
            nannyService.updateNanny(id, nanny);
        } catch (Exception e) {
            log.error("Error when trying to register the user", e);
        }
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
