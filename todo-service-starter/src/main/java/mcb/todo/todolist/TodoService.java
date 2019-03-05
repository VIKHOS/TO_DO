package mcb.todo.todolist;

import mcb.todo.todolist.restmodel.CreateTodoListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

import java.net.URI;

@Controller
@RequestMapping("/todos")
public class TodoService {
    private TodoListRepository repository;

    @Autowired
    public TodoService(TodoListRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Mono<ResponseEntity> createTodoList(@RequestBody Mono<CreateTodoListRequest> request) {
        return request.map(r -> this.repository.save(new TodoList(r.getName())))
                .map(TodoList::getId)
                .map(id -> ResponseEntity.created(URI.create("/todo/" + id)).build());
    }

    @GetMapping
    public ResponseEntity fetchTodoList() {
        return ResponseEntity.ok(repository.findAll());
    }
}