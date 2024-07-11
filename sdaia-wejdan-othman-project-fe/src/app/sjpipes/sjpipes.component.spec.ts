import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SjpipesComponent } from './sjpipes.component';

describe('SjpipesComponent', () => {
  let component: SjpipesComponent;
  let fixture: ComponentFixture<SjpipesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SjpipesComponent]
    });
    fixture = TestBed.createComponent(SjpipesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
