import { Injectable } from '@angular/core';
import { Headers, Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import { Test } from './test';
import {RequestOptions, Request, RequestMethod} from '@angular/http';


@Injectable()
export class AuthenticationService {

  private headers = new Headers();
  private options: RequestOptions;
  private token: string;
  
  
  constructor(private http: Http) { 
    this.headers.append('Content-Type', 'application/json');
    this.options = new RequestOptions({ headers: this.headers });
  }


  login(username: string, password: string): Observable<boolean> {
        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.options = new RequestOptions({ headers: this.headers });
        
        return this.http.post('rest/security/authenticate', JSON.stringify({ username: username, password: password }), this.options)
            .map((response: Response) => {
                    return true;
                })
            .catch(this.handleError);
    }

    logout() {
        this.token = null;
        localStorage.removeItem('currentUser');

        this.headers = new Headers();
        this.headers.append('Content-Type', 'application/json');
        this.options = new RequestOptions({ headers: this.headers });

        this.http.get('rest/security/logout').subscribe();
    }


  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }


  private handleError (error: Response | any) {
    let errMsg: string;
    if (error.status === 401) {
       return Observable.throw('Unauthorized');
    }
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

