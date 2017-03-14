import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { Test } from './test';
import { TestService } from './test.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'slide-admin',
  templateUrl: './slide-admin.component.html',
  styleUrls: ['./slide-admin.component.css']
})


export class SlideAdminComponent implements OnInit, OnDestroy {
  public editForm: FormGroup;
  public submitted: boolean;
  public events: any[] = [];
  tests: Test[] = [];
  errorMessage: string;
  test: Test = new Test();
  imageFile: any;
  subscriptions: Object;
  id: number;
  private sub: any;

  constructor(private testService: TestService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getTests();
    this.setEditForm();

    this.sub = this.route.params.subscribe(params => {
       this.id = +params['id']; 

       console.log("This is the id: "+this.id);
       // In a real app: dispatch action to load the details here.
    });
  }

  public setEditForm(test?: Test) {
    if (!test) {
      test = new Test();
    }
    this.editForm = new FormGroup({
      id: new FormControl(test.id),
      testName: new FormControl(test.testName, [<any>Validators.required, <any>Validators.minLength(5)]),
      description: new FormControl(test.description),
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
      tests => this.tests = tests,
      error => this.errorMessage = <any>error);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}