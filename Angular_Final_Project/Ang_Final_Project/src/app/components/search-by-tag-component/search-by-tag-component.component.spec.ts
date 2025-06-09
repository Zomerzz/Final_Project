import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByTagComponentComponent } from './search-by-tag-component.component';

describe('SearchByTagComponentComponent', () => {
  let component: SearchByTagComponentComponent;
  let fixture: ComponentFixture<SearchByTagComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByTagComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByTagComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
