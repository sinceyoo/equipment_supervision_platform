package com.syo.platform.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.syo.platform.entity.DataBaseMonitorConfig;

public interface DataBaseMonitorConfigRepository extends JpaRepository<DataBaseMonitorConfig, String> {
	
	@Query("from DataBaseMonitorConfig c where c.name like %:vague% or c.dbVendor like %:vague% or c.remarks like %:vague%")
	public List<DataBaseMonitorConfig> findByVague(@Param("vague")String vague);
}
