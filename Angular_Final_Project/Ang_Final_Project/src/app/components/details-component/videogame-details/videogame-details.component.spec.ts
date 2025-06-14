import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideogameDetailsComponent } from './videogame-details.component';

describe('VideogameDetailsComponent', () => {
  let component: VideogameDetailsComponent;
  let fixture: ComponentFixture<VideogameDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VideogameDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VideogameDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
