package com.rest.api.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by suresh on 4/11/17.
 */

@Entity
public class Gene {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String hugoSymbol;
    private String geneAliases;
    private Long entrezGeneId;
    private Boolean oncogene;
    private Boolean tsg;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Variant> variants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHugoSymbol() {
        return hugoSymbol;
    }

    public void setHugoSymbol(String hugoSymbol) {
        this.hugoSymbol = hugoSymbol;
    }

    public String getGeneAliases() {
        return geneAliases;
    }

    public void setGeneAliases(String geneAliases) {
        this.geneAliases = geneAliases;
    }

    public Long getEntrezGeneId() {
        return entrezGeneId;
    }

    public void setEntrezGeneId(Long entrezGeneId) {
        this.entrezGeneId = entrezGeneId;
    }

    public Boolean getOncogene() {
        return oncogene;
    }

    public void setOncogene(Boolean oncogene) {
        this.oncogene = oncogene;
    }

    public Boolean getTsg() {
        return tsg;
    }

    public void setTsg(Boolean tsg) {
        this.tsg = tsg;
    }


    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

}
