import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginAreaComponent } from './login-area-component';

describe('LoginAreaComponentComponent', () => {
  let component: LoginAreaComponent;
  let fixture: ComponentFixture<LoginAreaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginAreaComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginAreaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
