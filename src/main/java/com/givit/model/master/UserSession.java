package com.givit.model.master;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.givit.dao.UserDao;
import com.givit.dao.UserSessionDao;


public interface UserSession extends PagingAndSortingRepository<UserSessionDao, Long> {
	
	public UserSessionDao findByToken(String token);
	public void delete(Long userId);
}
