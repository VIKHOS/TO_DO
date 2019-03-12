import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoSelectListComponent } from './todo-select-list.component';
import {TodoModel} from '../../todo-model';
import {TodoService} from '../todo.service';
import {of} from 'rxjs';
import {FormsModule} from '@angular/forms';
import {TodoListComponent} from '../todo-list/todo-list.component';
import {TodoInputBoxComponent} from '../todo-input-box/todo-input-box.component';
import {HttpClient, HttpHandler} from '@angular/common/http';

describe('TodoSelectListComponent', () => {
  let component: TodoSelectListComponent;
  let fixture: ComponentFixture<TodoSelectListComponent>;
  let todoService: TodoService;


  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule],
      declarations: [ TodoSelectListComponent, TodoListComponent, TodoInputBoxComponent ],
      providers: [HttpClient, HttpHandler]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoSelectListComponent);
    todoService = TestBed.get(TodoService);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('createTodoList', () => {
    it('should create a new todo list', () => {
      const todoList: TodoModel = <TodoModel> {};
      spyOn(todoService, 'createTodoList').and.returnValue(of(todoList));
      component.createTodoList('Work');
      expect(component.todoList.length).toBe(1);
    });
    it('should populate error message', () => {
      spyOn(todoService, 'createTodoList').and.returnValue(of(ErrorEvent));
      component.createTodoList('Work');
      expect(component.errorMsg).toBeDefined();
    });
  });

});
