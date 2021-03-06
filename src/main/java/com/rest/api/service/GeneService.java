package com.rest.api.service;

import com.rest.api.domain.Gene;
import com.rest.api.repository.GeneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by suresh on 4/11/17.
 */
@Service
public class GeneService {

    @Autowired
    GeneRepository geneRepository;

    public Gene get() {
//        geneRepository.findAll().forEach(a -> a.getVariants().forEach(b -> System.out.println(b.getConsequenceTerm())));
        return geneRepository.findOne(1L);
    }

}
