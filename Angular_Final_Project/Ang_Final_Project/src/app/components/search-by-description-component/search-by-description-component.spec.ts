import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByDescriptionComponentComponent } from './search-by-description-component';

describe('SearchByDescriptionComponentComponent', () => {
  let component: SearchByDescriptionComponentComponent;
  let fixture: ComponentFixture<SearchByDescriptionComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByDescriptionComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByDescriptionComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
