import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByAuthorNameComponent } from './search-by-author-name-component';

describe('SearchByAuthorNameComponentComponent', () => {
  let component: SearchByAuthorNameComponent;
  let fixture: ComponentFixture<SearchByAuthorNameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByAuthorNameComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByAuthorNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
