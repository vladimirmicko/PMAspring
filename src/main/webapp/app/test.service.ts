import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { Test } from './test';
import {RequestOptions, Request, RequestMethod} from '@angular/http';


@Injectable()
export class TestService {

  private headers = new Headers();
  private options: RequestOptions;
  
  
  constructor(private http: Http) { 
    this.headers.append('Content-Type', 'application/json');
    this.headers.append('Authorization', localStorage.getItem('currentUser'));
    // this.headers.append('Authorization', 'Basic ' + btoa('v' + ':' + 'v'));
    this.options = new RequestOptions({ headers: this.headers });
  }

  uploadRest(formData: FormData): Observable<any> {
      return this.http.post('rest/tests/upload', formData, this.options)
          .map(this.extractData)
          .catch(this.handleError);
  }

  getTests (): Observable<Test[]> {
    return this.http.get('rest/tests', this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  getTest (): Observable<Test> {
   return this.http.get('rest/tests/1', this.options)
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

