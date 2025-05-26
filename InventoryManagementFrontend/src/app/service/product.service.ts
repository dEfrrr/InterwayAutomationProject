import {Injectable} from '@angular/core';
import {environment} from '../enviroment/enviroment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/product';

@Injectable({providedIn: 'root'})
export class ProductService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getAllProducts(formValues: any): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiServerUrl}/products`, {
      params: {
        name: formValues.name,
        priceFrom: formValues.priceFrom,
        priceTo: formValues.priceTo,
        categoryId: formValues.categoryId
      }
    })
  }

  public getProduct(id: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiServerUrl}/products/${id}`)
  }

  public addProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(`${this.apiServerUrl}/products`, product)
  }

  public editProduct(id: number, product: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiServerUrl}/products/${id}`, product)
  }

  public deleteProduct(id: number): Observable<any> {
    return this.http.delete(`${this.apiServerUrl}/products/${id}`)
  }
}


