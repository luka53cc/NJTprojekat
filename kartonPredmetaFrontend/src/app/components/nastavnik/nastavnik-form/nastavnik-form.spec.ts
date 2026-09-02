import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NastavnikForm } from './nastavnik-form';

describe('NastavnikForm', () => {
  let component: NastavnikForm;
  let fixture: ComponentFixture<NastavnikForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NastavnikForm],
    }).compileComponents();

    fixture = TestBed.createComponent(NastavnikForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
