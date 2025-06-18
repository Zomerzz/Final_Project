import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarminxComponent } from './carminx.component';

describe('CarminxComponent', () => {
  let component: CarminxComponent;
  let fixture: ComponentFixture<CarminxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CarminxComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarminxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
