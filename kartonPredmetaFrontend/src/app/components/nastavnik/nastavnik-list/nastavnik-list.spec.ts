import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NastavnikList } from './nastavnik-list';

describe('NastavnikList', () => {
  let component: NastavnikList;
  let fixture: ComponentFixture<NastavnikList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NastavnikList],
    }).compileComponents();

    fixture = TestBed.createComponent(NastavnikList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
