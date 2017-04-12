package com.rest.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by suresh on 4/11/17.
 */
@Entity
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlteration() {
        return alteration;
    }

    public void setAlteration(String alteration) {
        this.alteration = alteration;
    }

    public String getConsequenceTerm() {
        return consequenceTerm;
    }

    public void setConsequenceTerm(String consequenceTerm) {
        this.consequenceTerm = consequenceTerm;
    }

    public Boolean getGenerallyTruncating() {
        return isGenerallyTruncating;
    }

    public void setGenerallyTruncating(Boolean generallyTruncating) {
        isGenerallyTruncating = generallyTruncating;
    }

    private String alteration;
    private String consequenceTerm;
    private Boolean isGenerallyTruncating;
}
