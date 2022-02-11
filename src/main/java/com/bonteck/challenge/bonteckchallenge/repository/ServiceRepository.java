package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ali Tofigh 2/10/2022 5:41 PM
 */

@Repository
public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {

    ServiceEntity findAllByCost(Integer cost);
}
