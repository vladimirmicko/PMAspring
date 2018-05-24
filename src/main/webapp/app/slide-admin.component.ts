import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { Test } from './test';
import { Slide } from './slide';
import { TestService } from './test.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'slide-admin',
  templateUrl: './slide-admin.component.html',
  styleUrls: ['./slide-admin.component.css']
})


export class SlideAdminComponent implements OnInit, OnDestroy {
  private editForm: FormGroup;
  private submitted: boolean;
  private events: any[] = [];
  private errorMessage: string;
  private test: Test = new Test();
  private slide: Slide = new Slide();
  private slides: Slide[] = [];
  private subscriptions: Object;
  private id: number;
  private sub: any;
  private primingFile: string;
  private testFile: string;


  constructor(private testService: TestService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.id = + params['id']; 
      console.log("This is the id: " + this.id);
      this.getTest(this.id);
      this.setEditForm();
      this.primingFile = "";
      this.testFile = "";
    });
  }

  public setEditForm(slide?: Slide) {
    if (!slide) {
      slide = new Slide();
    }
    this.editForm = new FormGroup({
      id: new FormControl(slide.id),
      slideName: new FormControl(slide.slideName, [<any>Validators.required, <any>Validators.minLength(5)]),
      delay: new FormControl(slide.delay, [<any>Validators.required])
    });
  }

  public editSlideModal(slide: Slide, modal: ModalDirective): void {
    this.setEditForm(slide);
    console.log(slide.slideName);
    this.slide = slide;
    modal.show();
  }

  public addNewSlideModal(modal: ModalDirective): void {
    let slide = new Slide();
    this.slide = slide;
    this.setEditForm(slide);
    modal.show();
  }

  public deleteSlideModal(slide: Slide, modal: ModalDirective): void {
    console.log(slide.slideName);
    this.slide = slide;
    modal.show();
  }


public addEditSlide(slide: Slide, isValid: boolean, modal: ModalDirective) {
    this.submitted = true;
    slide.test = this.test;
    this.slide = slide;
    console.log(slide, isValid);
    modal.hide();

    let formData = new FormData();
    formData.append('primingImageFile', this.primingFile);
    formData.append('testImageFile', this.testFile);
    formData.append('slide', new Blob([JSON.stringify(slide)], {type: "application/json"}));
    this.subscriptions = this.testService.uploadSlide(formData, this.id)
      .subscribe(
      (res: any) => {
        this.getTest(this.id);
      },
      (err: any) => {
        this.router.navigateByUrl('login/');
      })
  }


  public deleteSlide(slide: Slide, modal: ModalDirective) {
    console.log('Slide deleted:' + slide.slideName);
    modal.hide();
    this.subscriptions = this.testService.deleteSlide(slide)
      .subscribe(
      (res: any) => {
        this.getTest(this.test.id);
      },
      (err: any) => {
        this.router.navigateByUrl('login/');
      })
  }


  public onChangePrimingImageFile(event: any): void {
    if (event.target.files[0].name) {
      this.primingFile=event.target.files[0];
    }
  }


  public onChangeTestImageFile(event: any): void {
    if (event.target.files[0].name) {
      this.testFile=event.target.files[0];
    }
  }


  public getTest(id: number) {
    this.testService.getTest(id)
      .subscribe(
      test => {this.test = test; this.slides = test.slideList},
      error => {
        this.errorMessage = <any>error
        this.router.navigateByUrl('login/');
      });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}