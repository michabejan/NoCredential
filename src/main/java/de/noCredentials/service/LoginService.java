package de.noCredentials.service;

import de.noCredentials.model.Account;

/**
 * Created by Micha-PC on 25.06.2017.
 */
public interface LoginService {
    Account getAccountByPseudonym(String pseudonym);
}
