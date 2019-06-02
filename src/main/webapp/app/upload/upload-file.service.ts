import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL } from 'app/app.constants';
 
@Injectable({
  providedIn: 'root'
})
export class UploadFileService {
 
  constructor(private http: HttpClient) { }
 
  pushFileToStorage(file: File): Observable<HttpEvent<{}>> {
    const formdata: FormData = new FormData();
 
    formdata.append('file', file);
 
    const req = new HttpRequest('POST', SERVER_API_URL + '/api/upload/post', formdata, {
      reportProgress: true,
      responseType: 'text'
    });
 
    return this.http.request(req);
  }
 
  getFiles(): Observable<any> {
    return this.http.get(SERVER_API_URL + '/api/upload/getallfiles');
  }
}