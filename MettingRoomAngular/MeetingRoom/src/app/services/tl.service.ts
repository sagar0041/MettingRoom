import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from '../models/user';


let API_URL =  "http://localhost:8080/api/tl/";

@Injectable({
  providedIn: 'root'
})
export class TlService {

  currentUser: User;
  headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.headers = new HttpHeaders({
      authorization: 'Bearer ' + this.currentUser.token,
      "Content-Type":"application/json; charset=UTF-8",
    });
   }

   findAllUsers(): Observable<any> {
     return this.http.get(API_URL + "all", {headers: this.headers});
   }
}