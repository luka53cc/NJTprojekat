import { ComponentFixture, TestBed } from '@angular/core/testing';
import { StudijskiProgramList } from './studijski-program-list';

describe('StudijskiProgramList', () => {
  let component: StudijskiProgramList;
  let fixture: ComponentFixture<StudijskiProgramList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StudijskiProgramList],
    }).compileComponents();

    fixture = TestBed.createComponent(StudijskiProgramList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
