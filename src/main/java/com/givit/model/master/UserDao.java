package com.givit.model.master;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
//public interface CountryDao extends CrudRepository<Country, Long> {
public interface UserDao extends PagingAndSortingRepository<User, Long> {
//public interface CountryDao extends JpaRepository<Country, Long> {


    public User findByUsername(String code);

    public User findByUsernameAndPassword(String username, String password);

}
