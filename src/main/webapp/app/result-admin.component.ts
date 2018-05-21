import { Component, OnInit, ViewChild } from '@angular/core';
import { ResultService } from './result.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
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



  constructor(private resultService: ResultService,  private router: Router) { }

  ngOnInit(): void {

  }


}