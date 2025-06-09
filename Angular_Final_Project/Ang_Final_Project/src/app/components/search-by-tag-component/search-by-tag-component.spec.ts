import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByTagComponent } from './search-by-tag-component';

describe('SearchByTagComponentComponent', () => {
  let component: SearchByTagComponent;
  let fixture: ComponentFixture<SearchByTagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByTagComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByTagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
