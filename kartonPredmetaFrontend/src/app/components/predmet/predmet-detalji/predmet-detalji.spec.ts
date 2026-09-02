import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PredmetDetalji } from './predmet-detalji';

describe('PredmetDetalji', () => {
  let component: PredmetDetalji;
  let fixture: ComponentFixture<PredmetDetalji>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredmetDetalji],
    }).compileComponents();

    fixture = TestBed.createComponent(PredmetDetalji);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
