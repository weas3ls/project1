import { TestBed } from '@angular/core/testing';

import { AllRequestsService } from './all-requests.service';

describe('AllRequestsService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));

    it('should be created', () => {
        const service: AllRequestsService = TestBed.get(AllRequestsService);
        expect(service).toBeTruthy();
    });
});
