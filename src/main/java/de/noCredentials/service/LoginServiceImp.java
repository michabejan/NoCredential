package de.noCredentials.service;

import de.noCredentials.dao.AccountRepository;
import de.noCredentials.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Micha-PC on 25.06.2017.
 */
@Service("loginService")
public class LoginServiceImp implements LoginService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account getAccountByPseudonym(String pseudonym) {
       return accountRepository.findAccountByPseudonym(pseudonym);
    }
}
