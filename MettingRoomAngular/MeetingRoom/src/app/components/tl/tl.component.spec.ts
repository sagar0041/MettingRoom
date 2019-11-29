import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TlComponent } from './tl.component';

describe('TlComponent', () => {
  let component: TlComponent;
  let fixture: ComponentFixture<TlComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TlComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TlComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
