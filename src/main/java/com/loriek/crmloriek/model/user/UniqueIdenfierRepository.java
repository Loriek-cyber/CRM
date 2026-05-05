package com.loriek.crmloriek.model.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniqueIdenfierRepository extends JpaRepository<UniqueIdentificator, String> {
}
