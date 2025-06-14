import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddRecensioneComponent } from './add-recensione.component';

describe('AddRecensioneComponent', () => {
  let component: AddRecensioneComponent;
  let fixture: ComponentFixture<AddRecensioneComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddRecensioneComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddRecensioneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
