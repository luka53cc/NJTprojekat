import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ModulForm } from './modul-form';

describe('ModulForm', () => {
  let component: ModulForm;
  let fixture: ComponentFixture<ModulForm>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModulForm],
    }).compileComponents();

    fixture = TestBed.createComponent(ModulForm);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
