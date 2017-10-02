package de.noCredentials.service;

import de.noCredentials.dao.AccountRepository;
import de.noCredentials.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Micha-PC on 25.06.2017.
 */
@Service("registrationService")
public class RegistrationServiceImp implements RegistrationService{

    @Autowired
    AccountRepository accountRepository;
    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }

    @Override
    public boolean accountIsSetinDataBase(String pseudonym) {
        if(accountRepository.findAccountByPseudonym(pseudonym) != null) return true;
        return false;
    }
}
