import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IssueBookComponent } from './issue-book.component';

describe('IssueBookComponent', () => {
  let component: IssueBookComponent;
  let fixture: ComponentFixture<IssueBookComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IssueBookComponent]
    });
    fixture = TestBed.createComponent(IssueBookComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
