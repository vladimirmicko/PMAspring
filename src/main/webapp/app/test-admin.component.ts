import { Component, OnInit, ViewChild } from '@angular/core';
import { Test } from './test';
import { TestService } from './test.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

@Component({
  moduleId: module.id,
  selector: 'test-admin',
  templateUrl: './test-admin.component.html',
  styleUrls: ['./test-admin.component.css']
})


export class TestAdminComponent implements OnInit {
  public editForm: FormGroup; 
  public submitted: boolean; 
  public events: any[] = [];
  tests: Test[] = [];
  errorMessage: string;
  test: Test;


  constructor(private testService: TestService) { }

  ngOnInit(): void {
    this.getTests();

    this.editForm = new FormGroup({
        name: new FormControl('', [<any>Validators.required, <any>Validators.minLength(5)]),
        description: new FormControl('', [<any>Validators.required, <any>Validators.minLength(5)]),
    });
  }

  public editTestModal(test: Test, modal: ModalDirective): void {
    console.log(test.testName);
    this.test=test;
    modal.show();
  }

  public deleteTestModal(test: Test, modal: ModalDirective): void {
    console.log(test.testName);
    this.test=test;
    modal.show();
  }

  editTest(test: Test, isValid: boolean, modal: ModalDirective) {
        this.submitted = true;
        console.log(test, isValid);
        modal.hide();
    }

  deleteTest(test: Test, modal: ModalDirective) {
    console.log('Test deleted:' + test.testName);
    modal.hide();
  }

  getTests() {
    this.testService.getTests()
      .subscribe(
      tests => this.tests = tests,
      error => this.errorMessage = <any>error);
  }
}