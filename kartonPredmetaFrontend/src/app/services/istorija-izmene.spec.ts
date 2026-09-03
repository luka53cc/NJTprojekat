import { TestBed } from '@angular/core/testing';
import { IstorijaIzmene } from './istorija-izmene';

describe('IstorijaIzmene', () => {
  let service: IstorijaIzmene;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(IstorijaIzmene);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
