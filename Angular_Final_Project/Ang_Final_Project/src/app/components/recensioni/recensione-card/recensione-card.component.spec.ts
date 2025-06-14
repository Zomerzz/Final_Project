import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecensioneCardComponent } from './recensione-card.component';

describe('RecensioneCardComponent', () => {
  let component: RecensioneCardComponent;
  let fixture: ComponentFixture<RecensioneCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecensioneCardComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecensioneCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
