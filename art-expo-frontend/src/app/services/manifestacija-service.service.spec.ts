import { TestBed } from '@angular/core/testing';

import { ManifestacijaServiceService } from './manifestacija-service';

describe('ManifestacijaServiceService', () => {
  let service: ManifestacijaServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ManifestacijaServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
