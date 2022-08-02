package com.phoenixtype.userregistrationservice.repository;

import com.phoenixtype.userregistrationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
