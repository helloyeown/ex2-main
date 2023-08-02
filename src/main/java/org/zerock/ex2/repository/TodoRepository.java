package org.zerock.ex2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerock.ex2.domain.Todo;


public interface TodoRepository extends JpaRepository<Todo, Long>{
  
}
