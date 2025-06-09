import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserPageComponentComponent } from './user-page-component';

describe('UserPageComponentComponent', () => {
  let component: UserPageComponentComponent;
  let fixture: ComponentFixture<UserPageComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserPageComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserPageComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
