package org.vorobiev.testRest;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoClient  extends JpaRepository<Client,Integer> {
}
