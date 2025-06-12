import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VideogiocoListComponent } from './videogioco-list.component';

describe('VideogiocoListComponent', () => {
  let component: VideogiocoListComponent;
  let fixture: ComponentFixture<VideogiocoListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [VideogiocoListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VideogiocoListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
