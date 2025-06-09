import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByDateComponentComponent } from './search-by-date-component';

describe('SearchByDateComponentComponent', () => {
  let component: SearchByDateComponentComponent;
  let fixture: ComponentFixture<SearchByDateComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByDateComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByDateComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
