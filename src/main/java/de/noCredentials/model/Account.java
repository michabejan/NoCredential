package de.noCredentials.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Micha-PC on 25.06.2017.
 */
@Entity
@Table(name="account")
public class Account {
    @Id
    @Column(name = "pseudonym")
    private String pseudonym;

    @Column(name = "identifier")
    private String identifier;


    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
