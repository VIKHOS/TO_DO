import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoListComponent } from './todo-list.component';
import {TodoService} from '../todo.service';
import {By} from '@angular/platform-browser';
import {FormsModule} from '@angular/forms';
import {TodoInputBoxComponent} from '../todo-input-box/todo-input-box.component';

describe('TodoListComponent', () => {
  let component: TodoListComponent;
  let fixture: ComponentFixture<TodoListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule],
      declarations: [ TodoListComponent ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should display the todo list', () => {
    const items = fixture.debugElement.queryAll(By.css('li'));
    expect(items.length).toBe(3);
  });
});
