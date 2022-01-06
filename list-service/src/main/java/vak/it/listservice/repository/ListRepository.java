package vak.it.listservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vak.it.listservice.model.TodoList;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<TodoList, Integer> {
    TodoList findTodoListByNaam(String Naam);
    List<TodoList> findListsByCategoryContaining(String category);
}
