import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { Test } from './test';
import { Slide } from './slide';
import {RequestOptions, Request, RequestMethod} from '@angular/http';


@Injectable()
export class TestService {

  private headers = new Headers();
  private options: RequestOptions;
  
  
  constructor(private http: Http) { 
    this.prepareHeaders();
  }

  prepareHeaders(){
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Authorization', localStorage.getItem('currentUser'));
    this.options = new RequestOptions({ headers: this.headers });
  }

  uploadTest(formData: FormData): Observable<any> {
    this.headers = new Headers();
    this.headers.append('Accept', 'application/json');
    this.headers.append('Authorization', localStorage.getItem('currentUser'));
    this.options = new RequestOptions({ headers: this.headers });
      return this.http.post('rest/tests/upload', formData, this.options)
          .map(this.extractData)
          .catch(this.handleError);
  }

  uploadSlide(formData: FormData, id:number): Observable<any> {
    this.headers = new Headers();
    this.headers.append('Accept', 'application/json');
    this.headers.append('Authorization', localStorage.getItem('currentUser'));
    this.options = new RequestOptions({ headers: this.headers });
      return this.http.post('rest/tests/slides/upload/'+id, formData, this.options)
          .map(this.extractData)
          .catch(this.handleError);
  }


  getTests (): Observable<Test[]> {
    this.prepareHeaders();
    return this.http.get('rest/tests', this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  getTest (id: number): Observable<Test> {
    this.prepareHeaders();
    return this.http.get('rest/tests/'+id, this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }


  deleteTest (test: Test): Observable<any> {
    this.prepareHeaders();
    return this.http.delete('rest/tests/'+test.id, this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

   
  deleteSlide (slide: Slide): Observable<any> {
    this.prepareHeaders();
    return this.http.delete('rest/tests/slides/'+slide.id, this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }


  private handleError (error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}

