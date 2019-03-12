import { TestBed, inject } from '@angular/core/testing';

import { TodoService } from './todo.service';
import {FormsModule} from '@angular/forms';

describe('TodoService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule],
      providers: [TodoService]
    });
  });

  it('should be created', inject([TodoService], (service: TodoService) => {
    expect(service).toBeTruthy();
  }));
});
