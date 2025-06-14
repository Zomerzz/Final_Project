import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecensioniListComponent } from './recensioni-list.component';

describe('RecensioniListComponent', () => {
  let component: RecensioniListComponent;
  let fixture: ComponentFixture<RecensioniListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecensioniListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecensioniListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
