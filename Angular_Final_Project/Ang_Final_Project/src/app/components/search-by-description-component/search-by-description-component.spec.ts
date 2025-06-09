import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByDescriptionComponent } from './search-by-description-component';

describe('SearchByDescriptionComponentComponent', () => {
  let component: SearchByDescriptionComponent;
  let fixture: ComponentFixture<SearchByDescriptionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByDescriptionComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByDescriptionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
