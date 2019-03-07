import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TodoSelectListComponent } from './todo-select-list.component';

describe('TodoSelectListComponent', () => {
  let component: TodoSelectListComponent;
  let fixture: ComponentFixture<TodoSelectListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TodoSelectListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TodoSelectListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
