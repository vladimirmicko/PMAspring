import { Component, OnInit } from '@angular/core';
import { Hero }        from './hero';
import { HeroService } from './hero.service';
import {DataTableModule,SharedModule} from 'primeng/primeng';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.css' ]
})
export class AppComponent implements OnInit {
  heroes: Hero[] = [];
  errorMessage: string;

  constructor(private heroService: HeroService) { }

  ngOnInit(): void {
    this.getHeroes(); 
  }


    getHeroes() {
    this.heroService.getHeroes()
                     .subscribe(
                       heroes => this.heroes = heroes,
                       error =>  this.errorMessage = <any>error);
  }

}


