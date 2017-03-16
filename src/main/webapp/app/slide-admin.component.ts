import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { Test } from './test';
import { Slide } from './slide';
import { TestService } from './test.service';
import { DataTableModule, SharedModule } from 'primeng/primeng';
import { ModalDirective } from 'ng2-bootstrap';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'slide-admin',
  templateUrl: './slide-admin.component.html',
  styleUrls: ['./slide-admin.component.css']
})


export class SlideAdminComponent implements OnInit, OnDestroy {
  public editForm: FormGroup;
  public submitted: boolean;
  public events: any[] = [];
  errorMessage: string;
  test: Test = new Test();
  slide: Slide = new Slide();
  slides: Slide[] = [];
  primingImageFile: any;
  testImageFile: any;
  subscriptions: Object;
  id: number;
  private sub: any;

  constructor(private testService: TestService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.id = + params['id']; 
      console.log("This is the id: " + this.id);
      this.getTest(this.id);
      this.setEditForm();
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
    console.log(slide, isValid);
    modal.hide();

    let formData = new FormData();
    formData.append('primingImageFile', this.primingImageFile);
    formData.append('testImageFile', this.testImageFile);
    formData.append('slide', new Blob([JSON.stringify(slide)], {type: "application/json"}));
    this.subscriptions = this.testService.uploadSlide(formData)
      .subscribe(
      (res: any) => {
        this.getTest(this.id);
      },
      (err: any) => {

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

      })
  }

  public onChangeJavaFile(event: any): void {
    if (event.target.files[0]) {
      this.primingImageFile = event.target.files[0];
    }
  }


  public getTest(id: number) {
    this.testService.getTest(id)
      .subscribe(
      test => {this.test = test; this.slides = test.slideList},
      error => this.errorMessage = <any>error);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}