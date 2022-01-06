package vak.it.listservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vak.it.listservice.model.TodoList;
import vak.it.listservice.repository.ListRepository;

import javax.annotation.PostConstruct;

@RestController
public class ListController {

    @Autowired
    private ListRepository listRepository;

    @GetMapping("/lists/naam/{naam}")
    public TodoList getListByNaam(@PathVariable String naam){
        return listRepository.findTodoListByNaam(naam);
    }

    @GetMapping("/lists/category/{category}")
    public java.util.List<TodoList> getListsByCategory(@PathVariable String category){
        return listRepository.findListsByCategoryContaining(category);
    }

    @PostMapping("/lists")
    public TodoList addList(@RequestBody TodoList list){
        listRepository.save(list);
        return list;
    }

    @PutMapping("/lists")
    public TodoList updateList(@RequestBody TodoList updatedList){
        TodoList retrievedList = listRepository.findTodoListByNaam(updatedList.getNaam());

        retrievedList.setNaam(updatedList.getNaam());
        retrievedList.setCategory(updatedList.getCategory());

        listRepository.save(retrievedList);

        return retrievedList;
    }

    @DeleteMapping("/lists/naam/{naam}")
    public ResponseEntity deleteList(@PathVariable String naam){
        TodoList list = listRepository.findTodoListByNaam(naam);
        if(list!=null){
            listRepository.delete(list);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostConstruct
    public void fillDB(){
        if(listRepository.count()==0){
            listRepository.save(new TodoList("School","#3285a8"));
            listRepository.save(new TodoList("Klusjes","#a83632"));
        }

        System.out.println(listRepository.findTodoListByNaam("School").getNaam());
    }
}
