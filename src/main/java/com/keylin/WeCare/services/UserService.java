package com.keylin.WeCare.services;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.keylin.WeCare.entities.Family;
import com.keylin.WeCare.entities.Nanny;
import com.keylin.WeCare.entities.Users;

public interface UserService {
    // get nanny details
    public Nanny getNannyDetails(String username) throws Exception;

    // get family details
    public Family getFamilyDetails(String username) throws Exception;

    // register nanny
    public void registerUserN(Users user, Nanny nannyDetails) throws Exception;

    // register family
    public void registerUserF(Users user, Family familyDetails) throws Exception;

    // Edit user family
    public void editUserF(Long id, Family family) throws Exception;

    // Edit user nanny

    public void editUserN(Long id, Nanny nanny) throws Exception;

    // password encoder
    public PasswordEncoder passwordEncoder();
}
