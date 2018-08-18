import { Component, OnInit, ViewChild } from '@angular/core';
import { Result } from './result';
import { Answer } from './answer';
import { ResultService } from './result.service';
import { DataTableModule, SharedModule, CheckboxModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'result-admin',
  templateUrl: './result-admin.component.html',
  styleUrls: ['./result-admin.component.css']
})


export class ResultAdminComponent implements OnInit {
  results: Result[] = [];
  answers: Answer[] = [];
  errorMessage: string;
  result: Result = new Result();
  subscriptions: Object;


  constructor(private resultService: ResultService,  private router: Router) { }

  ngOnInit(): void {
    this.getResults();
  }



  public toggleSupervised(result: Result){
    this.resultService.toggleSupervised(result.id).subscribe(
        result => this.result = result,
        error => this.errorMessage = <any>error);
  }



  public deleteResultModal(result: Result, modal: ModalDirective): void {
    this.result=result;
    modal.show();
  }

  public deleteResult(result: Result, modal: ModalDirective) {
    console.log('Result deleted: ' + result.testName);
    this.subscriptions = this.resultService.deleteResult(result)
      .subscribe(
      (res: any) => {
        this.getResults();
      },
      (err: any) => {
        this.router.navigateByUrl('login/');
      })
      modal.hide();
  }


  public viewModal(result: Result, modal: ModalDirective): void {
    this.answers=result.answerList;
    modal.show();
  }


  public getResults() {
    this.resultService.getResults()
      .subscribe(
      results => this.results = results,
      error => {
        this.errorMessage = <any>error;
        this.router.navigateByUrl('login/');
      });
  }


  public redirectToTest(result: Result, modal: ModalDirective){
    this.router.navigate(['results/'+result.id]);
  }


}