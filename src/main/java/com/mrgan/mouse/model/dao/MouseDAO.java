package com.mrgan.mouse.model.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mrgan.mouse.model.Mouse;

public interface MouseDAO extends CrudRepository<Mouse, Long> {
	long countByGroupIdAndGroupNum(String groupId, int groupNum);
	
	@Query("select MAX(mo.groupNum) from Mouse mo where mo.groupId = ?")
	int findGroupNumMaxByGroupId(String groupId);
	
	List<Mouse> findByGroupId(String groupId, Sort sort);
	
	List<Mouse> findByGroupIdAndGroupNum(String groupId, int groupNum, Sort sort);

	List<Mouse> findByNoAndGroupId(String no, String groupId);

	long countByGroupId(String groupId);
}
