import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsConponentsComponent } from './details-conponents.component';

describe('DetailsConponentsComponent', () => {
  let component: DetailsConponentsComponent;
  let fixture: ComponentFixture<DetailsConponentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsConponentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsConponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
