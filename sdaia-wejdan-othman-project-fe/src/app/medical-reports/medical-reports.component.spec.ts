import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicalReportsComponent } from './medical-reports.component';

describe('MedicalReportsComponent', () => {
  let component: MedicalReportsComponent;
  let fixture: ComponentFixture<MedicalReportsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MedicalReportsComponent]
    });
    fixture = TestBed.createComponent(MedicalReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
