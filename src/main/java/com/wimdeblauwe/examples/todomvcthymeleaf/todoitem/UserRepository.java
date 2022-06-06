package com.wimdeblauwe.examples.todomvcthymeleaf.todoitem;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
