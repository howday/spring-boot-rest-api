package com.rest.api.repository;


import com.rest.api.domain.Gene;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by suresh on 4/11/17.
 */


public interface GeneRepository extends CrudRepository<Gene, Long> {

}
