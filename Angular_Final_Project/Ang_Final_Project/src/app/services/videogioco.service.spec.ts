import { TestBed } from '@angular/core/testing';

import { VideogiocoService } from './videogioco.service';

describe('VideogiocoService', () => {
  let service: VideogiocoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VideogiocoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
