package com.loginradius.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.loginradius.weather.model.RequestLog;

/**
 * The interface RequestLog repository.
 *
 * @author Sri
 */
@Repository
public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {}
