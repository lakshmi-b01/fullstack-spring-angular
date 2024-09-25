package net.javaspaceproject.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.javaspaceproject.springboot.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
