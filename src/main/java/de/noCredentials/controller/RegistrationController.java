package de.noCredentials.controller;

import de.noCredentials.model.Account;
import de.noCredentials.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;


/**
 * Created by Micha-PC on 25.06.2017.
 */
@Controller
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerSubmit(@ModelAttribute Account account, @RequestParam String identifier, @RequestParam String fingerprint, HttpSession session) {
            if(registrationService.accountIsSetinDataBase(fingerprint)){
                session.setAttribute("pseudonym", fingerprint);
                return "redirect:/login";
            }
            account.setIdentifier(identifier);
            account.setPseudonym(fingerprint);

            registrationService.createAccount(account);

            return "redirect:/";


    }




}
