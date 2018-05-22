import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/Rx';
import 'rxjs/add/observable/throw';
import { Result } from './result';
import {RequestOptions, Request, RequestMethod} from '@angular/http';


@Injectable()
export class ResultService {

  private headers = new Headers();
  private options: RequestOptions;
  
  
  constructor(private http: Http) { 
    this.prepareHeaders();
  }

  prepareHeaders(){
    this.headers = new Headers();
    this.headers.append('Content-Type', 'application/json');
    this.options = new RequestOptions({ headers: this.headers });
  }

 
  getResults (): Observable<any> {
    this.prepareHeaders();
    return this.http.get('rest/results', this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  getResult (id: number): Observable<any> {
    this.prepareHeaders();
    return this.http.get('rest/results/'+id, this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }


  toggleSupervised (id: number): Observable<any> {
    this.prepareHeaders();
    return this.http.get('rest/results/toggleSupervised/'+id, this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

  deleteResult (result: Result): Observable<any> {
    this.prepareHeaders();
    return this.http.delete('rest/results/'+result.id, this.options)
                    .map(this.extractData)
                    .catch(this.handleError);
  }

   
  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }


  private handleError (error: Response | any) {
    let errMsg: string;
    let body: any;
    if (error instanceof Response) {
      body = error.json() || '';
      const err = body.message || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(body);
;
  }
}

