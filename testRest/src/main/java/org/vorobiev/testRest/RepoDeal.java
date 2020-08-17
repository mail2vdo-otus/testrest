package org.vorobiev.testRest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoDeal  extends JpaRepository<Deal,Integer> {

  //  Page<Deal> findByClientid(Long clientid, Pageable pageable);
  //  Optional<Deal> findByIdAndClientid(Long id, Long clientid);

}
