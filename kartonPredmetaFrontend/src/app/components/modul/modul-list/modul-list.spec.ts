import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ModulList } from './modul-list';

describe('ModulList', () => {
  let component: ModulList;
  let fixture: ComponentFixture<ModulList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModulList],
    }).compileComponents();

    fixture = TestBed.createComponent(ModulList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
