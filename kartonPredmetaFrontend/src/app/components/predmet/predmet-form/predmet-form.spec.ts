import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PredmetForm } from './predmet-form';

describe('PredmetForm', () => {
  let component: PredmetForm;
  let fixture: ComponentFixture<PredmetForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredmetForm],
    }).compileComponents();

    fixture = TestBed.createComponent(PredmetForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
