import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PredmetList } from './predmet-list';

describe('PredmetList', () => {
  let component: PredmetList;
  let fixture: ComponentFixture<PredmetList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PredmetList],
    }).compileComponents();

    fixture = TestBed.createComponent(PredmetList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
