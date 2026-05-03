package com.loriek.crmloriek.model.redirect;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedirectRepository extends JpaRepository<Redirect, String> {
}
