package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 5:41 PM
 */

@Repository
public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {

    ServiceEntity findServiceEntityById(Long id);

    //List<ServiceEntity> findAllByActive(boolean status);

   // @Query("UPDATE SERVICE E SET isActive = :status where id = :serviceId")
    //int changeServiceStatus(@Param("serviceId") Long serviceId, @Param("status") boolean status);
}
