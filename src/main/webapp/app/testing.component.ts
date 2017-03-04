import { Component, OnInit, ViewChild } from '@angular/core';
import { Test } from './test';
import { TestService } from './test.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';

@Component({
  moduleId: module.id,
  selector: 'testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.css']
})
export class TestingComponent implements OnInit {
  test: Test = new Test();
  errorMessage: string;
  imageFile: any;
  subscriptions: Object;

  @ViewChild('childModal')
  public childModal: ModalDirective;

  constructor(private testService: TestService) { }

  ngOnInit(): void {
    this.getTests();
  }

  public showChildModal(): void {
    this.childModal.show();
  }

  public hideChildModal(): void {
    this.childModal.hide();
  }


  getTests() {
    this.testService.getTest()
      .subscribe(
      test => this.test = test,
      error => this.errorMessage = <any>error);
  }



  onChangeJavaFile(event: any): void {
    if (event.target.files[0]) {
      this.imageFile = event.target.files[0];
    }
  }


  upload() {
    let formData = new FormData();
    formData.append('imageFile', this.imageFile);

    this.subscriptions['upload'] = this.testService.uploadRest(formData)
      .subscribe(
      (res: any) => {
      },
      (err: any) => {
        
      })
  }

}