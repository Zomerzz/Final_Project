import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreazioneDatiComponent } from './creazione-dati.component';

describe('CreazioneDatiComponent', () => {
  let component: CreazioneDatiComponent;
  let fixture: ComponentFixture<CreazioneDatiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreazioneDatiComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreazioneDatiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
