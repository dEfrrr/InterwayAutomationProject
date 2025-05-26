import {Injectable} from '@angular/core';
import {environment} from '../enviroment/enviroment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Category} from '../model/category';

@Injectable({providedIn: 'root'})
export class CategoryService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(`${this.apiServerUrl}/categories/all`)
  }
}
