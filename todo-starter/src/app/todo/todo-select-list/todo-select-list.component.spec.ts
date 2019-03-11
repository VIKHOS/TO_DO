import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoSelectListComponent } from './todo-select-list.component';
import {TodoModel} from '../../todo-model';
import {TodoService} from '../todo.service';
import {of} from 'rxjs';

describe('TodoSelectListComponent', () => {
  let component: TodoSelectListComponent;
  let fixture: ComponentFixture<TodoSelectListComponent>;
  let todoService: TodoService;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TodoSelectListComponent ]
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
