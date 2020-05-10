package org.vorobiev.testRest;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/*@Repository
public interface RepoRequest extends PagingAndSortingRepository<Request,Long>, QuerydslPredicateExecutor<Request> {
*/

@Repository
public interface RepoRequest extends JpaRepository<Request,Integer> {
}
