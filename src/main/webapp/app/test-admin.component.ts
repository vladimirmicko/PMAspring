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
  test: Test = new Test();
  imageFile: any;
  subscriptions: Object;


  constructor(private testService: TestService) { }

  ngOnInit(): void {
    this.getTests();
    this.setEditForm();
  }

  public setEditForm(test?: Test) {
    if (!test) {
      test = new Test();
    }
    this.editForm = new FormGroup({
      id: new FormControl(test.id),
      testName: new FormControl(test.testName, [<any>Validators.required, <any>Validators.minLength(5)]),
      description: new FormControl(test.description, [<any>Validators.required, <any>Validators.minLength(5)]),
      creationDate: new FormControl(test.creationDate)
    });
  }

  public editTestModal(test: Test, modal: ModalDirective): void {
    this.setEditForm(test);
    console.log(test.testName);
    this.test = test;
    modal.show();
  }

  public addNewTestModal(modal: ModalDirective): void {
    modal.show();
  }

  public deleteTestModal(test: Test, modal: ModalDirective): void {
    console.log(test.testName);
    this.test = test;
    modal.show();
  }

  public editTest(test: Test, isValid: boolean, modal: ModalDirective) {
    this.submitted = true;
    console.log(test, isValid);
    modal.hide();
    this.subscriptions = this.testService.postTest(test)
      .subscribe(
      (res: any) => {
        this.getTests();
      },
      (err: any) => {

      })
  }

  public deleteTest(test: Test, modal: ModalDirective) {
    console.log('Test deleted:' + test.testName);
    modal.hide();
  }

  public onChangeJavaFile(event: any): void {
    if (event.target.files[0]) {
      this.imageFile = event.target.files[0];
    }
  }


  public upload() {
    let formData = new FormData();
    formData.append('imageFile', this.imageFile);
    this.subscriptions = this.testService.uploadRest(this.test.id, formData)
      .subscribe(
      (res: any) => {
        this.getTests();
      },
      (err: any) => {

      })
  }

  public getTests() {
    this.testService.getTests()
      .subscribe(
      tests => this.tests = tests,
      error => this.errorMessage = <any>error);
  }
}