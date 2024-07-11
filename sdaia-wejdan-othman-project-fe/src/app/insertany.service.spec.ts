import { TestBed } from '@angular/core/testing';

import { InsertanyService } from './insertany.service';

describe('InsertanyService', () => {
  let service: InsertanyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InsertanyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
