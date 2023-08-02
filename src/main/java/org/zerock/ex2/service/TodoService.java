package org.zerock.ex2.service;

import org.zerock.ex2.dto.PageRequestDTO;
import org.zerock.ex2.dto.PageResponseDTO;
import org.zerock.ex2.dto.TodoDTO;

public interface TodoService {
  
  Long register(TodoDTO todoDTO);

  TodoDTO get(Long tno);

  void modify(TodoDTO todoDTO);

  void remove(Long tno);

  PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
