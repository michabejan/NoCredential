package de.noCredentials.controller;

import de.noCredentials.model.Account;
import de.noCredentials.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.ExpiringSession;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Collection;

/**
 * Created by Micha-PC on 25.06.2017.
 */
@Controller
public class LoginController
{

    @Autowired
    private LoginService loginService;

    @Autowired
    FindByIndexNameSessionRepository<? extends ExpiringSession> sessions;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(HttpSession session) {
        String pseudonym = session.getAttribute("pseudonym").toString();

        Collection<? extends ExpiringSession> usersSessions = sessions
                .findByIndexNameAndIndexValue(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, pseudonym)
                .values();
        if(usersSessions.size() >= 2){
            return "redirect:/maximumNumberSessionsExceeded";
        }
        session.setAttribute(FindByIndexNameSessionRepository.PRINCIPAL_NAME_INDEX_NAME, pseudonym);
        session.setMaxInactiveInterval(360);

        Account account = loginService.getAccountByPseudonym(pseudonym);
        session.setAttribute("identifier", account.getIdentifier());

        return "redirect:/base";
    }


}
