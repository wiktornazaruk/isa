import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private baseUrl = 'http://localhost:8090/api'; // Gateway API URL

  constructor(private http: HttpClient) {}

  // Warehouse endpoints
  getWarehouses(): Observable<any> {
    return this.http.get(`${this.baseUrl}/warehouses`);
  }

  addWarehouse(warehouse: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/warehouses`, warehouse);
  }

  updateWarehouse(name: string, warehouse: any): Observable<any> {
    return this.http.patch(`${this.baseUrl}/warehouses/${name}`, warehouse);
  }

  deleteWarehouse(name: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/warehouses/${name}`);
  }

  getWarehouseDetails(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/warehouses/${name}`);
  }

  // Product endpoints
  addProduct(product: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/products`, product);
  }

  getProducts(): Observable<any> {
    return this.http.get(`${this.baseUrl}/products`);
  }

  getProductDetails(name: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/products/${name}`);
  }

  updateProduct(name: string, product: any): Observable<any> {
    return this.http.patch(`${this.baseUrl}/products/${name}`, product);
  }

  deleteProduct(name: string): Observable<any> {
    return this.http.delete(`${this.baseUrl}/products/${name}`);
  }
}
