package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ali Tofigh 2/10/2022 5:41 PM
 */

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    ServiceEntity findServiceEntityById(Long id);

    ServiceEntity findByName(String name);

    List<ServiceEntity> findAllByStatus(boolean status);

    @Transactional
    @Modifying
    @Query("UPDATE SERVICE SET status = :status where id = :serviceId")
    int changeServiceStatus(@Param("serviceId") Long serviceId, @Param("status") boolean status);
}
