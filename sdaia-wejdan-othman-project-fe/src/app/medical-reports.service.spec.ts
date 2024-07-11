import { TestBed } from '@angular/core/testing';

import { MedicalReportsService } from './medical-reports.service';

describe('MedicalReportsService', () => {
  let service: MedicalReportsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MedicalReportsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
