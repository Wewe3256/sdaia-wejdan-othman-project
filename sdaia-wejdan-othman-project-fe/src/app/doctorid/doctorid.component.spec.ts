import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DoctoridComponent } from './doctorid.component';

describe('DoctoridComponent', () => {
  let component: DoctoridComponent;
  let fixture: ComponentFixture<DoctoridComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DoctoridComponent]
    });
    fixture = TestBed.createComponent(DoctoridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
