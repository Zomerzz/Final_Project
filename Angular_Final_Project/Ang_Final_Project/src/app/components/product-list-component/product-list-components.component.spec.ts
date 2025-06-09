import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductListComponentsComponent } from './product-list-components.component';

describe('ProductListComponentsComponent', () => {
  let component: ProductListComponentsComponent;
  let fixture: ComponentFixture<ProductListComponentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductListComponentsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductListComponentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
