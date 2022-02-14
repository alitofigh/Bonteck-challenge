package com.bonteck.challenge.bonteckchallenge.service;

import com.bonteck.challenge.bonteckchallenge.model.ActivityLogEntity;
import com.bonteck.challenge.bonteckchallenge.repository.ActivityLogRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Ali Tofigh 2/14/2022 10:36 PM
 */

@Service
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public ActivityLogService(ActivityLogRepository activityLogRepository) {
        this.activityLogRepository = activityLogRepository;
    }

    public void save(String username, String serviceName) {
        ActivityLogEntity activityLogEntity = new ActivityLogEntity();
        activityLogEntity.setUsername(username);
        activityLogEntity.setServiceName(serviceName);
        activityLogEntity.setDateTime(new Date());
        activityLogRepository.save(activityLogEntity);
    }
}
