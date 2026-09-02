import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StudijskiProgramForm } from './studijski-program-form';

describe('StudijskiProgramForm', () => {
  let component: StudijskiProgramForm;
  let fixture: ComponentFixture<StudijskiProgramForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudijskiProgramForm],
    }).compileComponents();

    fixture = TestBed.createComponent(StudijskiProgramForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
