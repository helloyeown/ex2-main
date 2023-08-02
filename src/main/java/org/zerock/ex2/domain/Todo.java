package org.zerock.ex2.domain;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_todo")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tno;
  
  private String title;

  private String writer;

  private boolean complete;

  private LocalDate dueDate;

  public void changeTitle(String title){
    this.title = title;
  }

  public void changeComplete(boolean complete){
    this.complete = complete;
  }

  public void changeDueDate(LocalDate dueDate){
    this.dueDate = dueDate;
  }


}
