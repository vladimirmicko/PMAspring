import { Component, OnInit, ViewChild } from '@angular/core';
import { TestService } from './test.service';
import { ModalDirective } from 'ng2-bootstrap';


@Component({
  moduleId: module.id,
  selector: 'about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {
  private subscriptions: Object;
  private exception: string;

  constructor(private testService: TestService) { }

  public generateException() {
    console.log('Generate exception');
    this.subscriptions = this.testService.generateException()
      .subscribe(
      (res: any) => {
        this.exception = res.message;
      },
      (err: any) => {
        this.exception = err.message;
      })
  }

}