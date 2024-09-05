import { TestBed } from '@angular/core/testing';

import { PromoKodService } from './promo-kod.service';

describe('PromoKodService', () => {
  let service: PromoKodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PromoKodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
