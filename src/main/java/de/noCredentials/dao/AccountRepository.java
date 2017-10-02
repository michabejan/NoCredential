package de.noCredentials.dao;

import de.noCredentials.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Micha-PC on 25.06.2017.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByPseudonym(String pseudonym);
    Account findByIdentifier(String identifier);
}
