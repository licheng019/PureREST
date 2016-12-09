package com.givit.model.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.givit.dao.UserDao;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
//public interface CountryDao extends CrudRepository<Country, Long> {
public interface User extends PagingAndSortingRepository<UserDao, Long> {
//public interface CountryDao extends JpaRepository<Country, Long> {

    public UserDao findByUsername(String code);

    public UserDao findByUsernameAndPassword(String username, String password);

}
