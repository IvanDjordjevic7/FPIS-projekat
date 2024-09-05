import { TestBed } from '@angular/core/testing';

import { IzlozbaService } from './izlozba-service';

describe('IzlozbaServiceService', () => {
  let service: IzlozbaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IzlozbaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
