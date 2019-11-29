import { TestBed } from '@angular/core/testing';

import { TlService } from './tl.service';

describe('TlService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TlService = TestBed.get(TlService);
    expect(service).toBeTruthy();
  });
});
