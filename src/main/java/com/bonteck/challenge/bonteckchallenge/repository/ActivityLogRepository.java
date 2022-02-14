package com.bonteck.challenge.bonteckchallenge.repository;

import com.bonteck.challenge.bonteckchallenge.model.ActivityLogEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Ali Tofigh 2/14/2022 10:34 PM
 */

@Repository
public interface ActivityLogRepository extends CrudRepository<ActivityLogEntity, Long> {
}
