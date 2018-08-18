import { Component, OnInit, ViewChild } from '@angular/core';
import { Test } from './test';
import { TestService } from './test.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

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
  classifier: string;
  errorMessage: string;
  test: Test = new Test();
  imageFile: any;
  subscriptions: Object;
  @ViewChild('classifierModal') classifierModal: any;


  constructor(private testService: TestService,  private router: Router) { 
    this.classifier = "";
  }

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
      description: new FormControl(test.description),
      creationDate: new FormControl(test.creationDate),
      resultYesDescription: new FormControl(test.resultYesDescription),
      resultNoDescription: new FormControl(test.resultNoDescription)
    });
  }

  public editTestModal(test: Test, modal: ModalDirective): void {
    this.setEditForm(test);
    console.log(test.testName);
    this.test = test;
    modal.show();
  }

  public addNewTestModal(modal: ModalDirective): void {
    let test = new Test();
    this.test = test;
    this.setEditForm(test);
    modal.show();
  }

  public deleteTestModal(test: Test, modal: ModalDirective): void {
    console.log(test.testName);
    this.test = test;
    modal.show();
  }

  public trainClassifier(test: Test): void {
    console.log(test.testName);
    this.test = test;
    this.subscriptions = this.testService.trainClassifier(test)
      .subscribe(
      (res: any) => {
        this.classifier = res.message;
        this.classifierModal.show();
        // this.getTests();
      },
      (err: any) => {
        this.router.navigateByUrl('login/');
      })
  }


public addEditTest(test: Test, isValid: boolean, modal: ModalDirective) {
    this.submitted = true;
    console.log(test, isValid);
    modal.hide();

    let formData = new FormData();
    formData.append('imageFile', this.imageFile);
    formData.append('test', new Blob([JSON.stringify(test)], {type: "application/json"}));
    this.subscriptions = this.testService.uploadTest(formData)
      .subscribe(
      (res: any) => {
        this.getTests();
      },
      (err: any) => {
        this.router.navigateByUrl('login/');
      })
  }


  public deleteTest(test: Test, modal: ModalDirective) {
    console.log('Test deleted:' + test.testName);
    modal.hide();
    this.subscriptions = this.testService.deleteTest(test)
      .subscribe(
      (res: any) => {
        this.getTests();
      },
      (err: any) => {
        this.router.navigateByUrl('login/');
      })
  }

  public onChangeJavaFile(event: any): void {
    if (event.target.files[0]) {
      this.imageFile = event.target.files[0];
    }
  }


  public getTests() {
    this.testService.getTests()
      .subscribe(
      tests => {
        this.tests = tests;
      },
      error => {
        this.errorMessage = <any>error;
        this.router.navigateByUrl('login/');
      });
  }


  public redirectToTest(test: Test, modal: ModalDirective){
    this.router.navigate(['tests/'+test.id]);
  }
}