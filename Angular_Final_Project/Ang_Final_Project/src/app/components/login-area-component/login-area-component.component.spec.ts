import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginAreaComponentComponent } from './login-area-component.component';

describe('LoginAreaComponentComponent', () => {
  let component: LoginAreaComponentComponent;
  let fixture: ComponentFixture<LoginAreaComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LoginAreaComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginAreaComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
