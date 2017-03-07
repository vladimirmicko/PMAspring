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



  @ViewChild('childModal') 
  public childModal:ModalDirective;

  constructor(private testService: TestService) { }

 ngOnInit(): void {
    this.getTests();
  }

  public showChildModal():void {
    this.childModal.show();
  }
 
  public hideChildModal():void {
    this.childModal.hide();
  }

  getTests() {
    this.testService.getTests()
      .subscribe(
      tests => this.tests = tests,
      error => this.errorMessage = <any>error);
  }
}