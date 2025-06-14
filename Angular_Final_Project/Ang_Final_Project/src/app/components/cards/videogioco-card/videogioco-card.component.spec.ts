import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideogiocoCardComponent } from './videogioco-card.component';

describe('VideogiocoCardComponent', () => {
  let component: VideogiocoCardComponent;
  let fixture: ComponentFixture<VideogiocoCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VideogiocoCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VideogiocoCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
