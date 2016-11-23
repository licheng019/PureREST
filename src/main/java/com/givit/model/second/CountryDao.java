package com.givit.model.second;

import org.springframework.data.repository.PagingAndSortingRepository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Julian on 9/28/2016.
 *
 * Table 2.3. Supported keywords inside method names

 Keyword	Sample	JPQL snippet
 And	findByLastnameAndFirstname	… where x.lastname = ?1 and x.firstname = ?2
 Or	findByLastnameOrFirstname	… where x.lastname = ?1 or x.firstname = ?2
 Between	findByStartDateBetween	… where x.startDate between 1? and ?2
 LessThan	findByAgeLessThan	… where x.age < ?1
 GreaterThan	findByAgeGreaterThan	… where x.age > ?1
 After	findByStartDateAfter	… where x.startDate > ?1
 Before	findByStartDateBefore	… where x.startDate < ?1
 IsNull	findByAgeIsNull	… where x.age is null
 IsNotNull,NotNull	findByAge(Is)NotNull	… where x.age not null
 Like	findByFirstnameLike	… where x.firstname like ?1
 NotLike	findByFirstnameNotLike	… where x.firstname not like ?1
 StartingWith	findByFirstnameStartingWith	… where x.firstname like ?1 (parameter bound with appended %)
 EndingWith	findByFirstnameEndingWith	… where x.firstname like ?1 (parameter bound with prepended %)
 Containing	findByFirstnameContaining	… where x.firstname like ?1 (parameter bound wrapped in %)
 OrderBy	findByAgeOrderByLastnameDesc	… where x.age = ?1 order by x.lastname desc
 Not	findByLastnameNot	… where x.lastname <> ?1
 In	findByAgeIn(Collection<Age> ages)	… where x.age in ?1
 NotIn	findByAgeNotIn(Collection<Age> age)	… where x.age not in ?1
 True	findByActiveTrue()	… where x.active = true
 False	findByActiveFalse()	… where x.active = false
 */
@Transactional
//public interface CountryDao extends CrudRepository<Country, Long> {
public interface CountryDao extends PagingAndSortingRepository<Country, Long> {
//public interface CountryDao extends JpaRepository<Country, Long> {

    public Country findByName(String name);

    public Country findByCode(String username);


}



