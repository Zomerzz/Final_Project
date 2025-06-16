import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserRecensioniListComponent } from './user-recensioni-list.component';

describe('UserRecensioniListComponent', () => {
  let component: UserRecensioniListComponent;
  let fixture: ComponentFixture<UserRecensioniListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserRecensioniListComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserRecensioniListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
