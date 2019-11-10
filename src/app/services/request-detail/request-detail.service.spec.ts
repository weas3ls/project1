import { TestBed } from '@angular/core/testing';

import { RequestDetailService } from './request-detail.service';

describe('RequestDetailService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RequestDetailService = TestBed.get(RequestDetailService);
    expect(service).toBeTruthy();
  });
});
