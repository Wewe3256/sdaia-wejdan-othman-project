import { TestBed } from '@angular/core/testing';

import { DoctoridService } from './doctorid.service';

describe('DoctoridService', () => {
  let service: DoctoridService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DoctoridService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
