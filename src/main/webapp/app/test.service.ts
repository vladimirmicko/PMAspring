import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { Test } from './test';


@Injectable()
export class TestService {

  private headers = new Headers({'Content-Type': 'application/json'});
  private getTestUrl = 'rest/tests/1';  // URL to web api
  private getTestsUrl = 'rest/tests';  // URL to web api

  constructor(private http: Http) { }



  uploadRest(formData: FormData): Observable<any> {
      return this.http.post('rest/tests/upload', formData)
          .map(this.extractData)
          .catch(this.handleError);
  }

  getTests (): Observable<Test[]> {
    return this.http.get(this.getTestsUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  getTest (): Observable<Test> {
   return this.http.get(this.getTestUrl)
                    .map(this.extractData)
                    .catch(this.handleError);
  }


  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }


  private handleError (error: Response | any) {
    // In a real world app, we might use a remote logging infrastructure
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
