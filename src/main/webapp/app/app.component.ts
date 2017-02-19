import { Component, OnInit, ViewChild } from '@angular/core';
import { Hero } from './hero';
import { HeroService } from './hero.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  heroes: Hero[] = [];
  errorMessage: string;

  @ViewChild('addRoleModal')
  addRoleModal: ModalDirective;

  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
    this.getHeroes();
  }


  getHeroes() {
    this.heroService.getHeroes()
      .subscribe(
      heroes => this.heroes = heroes,
      error => this.errorMessage = <any>error);
  }

  public showRoleModal() {
    this.addRoleModal.show();
  }
  
  public hideRoleModal() {
    this.addRoleModal.hide();
  }
}


