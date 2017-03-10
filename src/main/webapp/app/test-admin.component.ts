import { Component, OnInit, ViewChild } from '@angular/core';
import { Test } from './test';
import { TestService } from './test.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';

@Component({
  moduleId: module.id,
  selector: 'test-admin',
  templateUrl: './test-admin.component.html',
  styleUrls: ['./test-admin.component.css']
})
export class TestAdminComponent implements OnInit {
  tests: Test[] = [];
  errorMessage: string;


  constructor(private testService: TestService) { }

  ngOnInit(): void {
    this.getTests();
  }

  public editTest(test: Test, modal: ModalDirective): void {
    console.log(test.testName);
    modal.show();
  }

  public hideEditModal(modal: ModalDirective){
    modal.hide();

  }

  public deleteTest(test: Test, modal: ModalDirective): void {
    console.log(test.testName);
    modal.show();
  }



  getTests() {
    this.testService.getTests()
      .subscribe(
      tests => this.tests = tests,
      error => this.errorMessage = <any>error);
  }
}