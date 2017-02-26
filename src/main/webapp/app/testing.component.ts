import { Component, OnInit, ViewChild } from '@angular/core';
import { Hero } from './hero';
import { TestService } from './test.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class TestingComponent implements OnInit {
  heroes: Hero[] = [];
  errorMessage: string;

  @ViewChild('childModal') 
  public childModal:ModalDirective;

  constructor(private heroService: TestService) { }

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
    this.heroService.getTests()
      .subscribe(
      heroes => this.heroes = heroes,
      error => this.errorMessage = <any>error);
  }

}