import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByDateComponent } from './search-by-date-component';

describe('SearchByDateComponentComponent', () => {
  let component: SearchByDateComponent;
  let fixture: ComponentFixture<SearchByDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByDateComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
