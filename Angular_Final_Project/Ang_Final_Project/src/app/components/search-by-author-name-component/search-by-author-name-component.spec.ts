import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByAuthorNameComponentComponent } from './search-by-author-name-component';

describe('SearchByAuthorNameComponentComponent', () => {
  let component: SearchByAuthorNameComponentComponent;
  let fixture: ComponentFixture<SearchByAuthorNameComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchByAuthorNameComponentComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchByAuthorNameComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
