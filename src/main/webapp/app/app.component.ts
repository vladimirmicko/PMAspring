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
export class AppComponent {

}


