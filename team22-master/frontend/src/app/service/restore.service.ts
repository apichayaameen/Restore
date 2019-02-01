import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})

export class RestoreService {

  constructor(private http: HttpClient) {

  }
  getRestore(): Observable<any> {
    return this.http.get('//localhost:8080/restore');
  }
  getCustomer(): Observable<any> {
    return this.http.get('//localhost:8080/rcustomer');
}

  getLease(): Observable<any> {
    return this.http.get('//localhost:8080/rlease');
  }

  getProduct():  Observable<any>{
    return this.http.get('//localhost:8080/rproduct');
  }
}
