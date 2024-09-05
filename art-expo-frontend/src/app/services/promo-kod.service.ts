import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PromoKod } from '../common/promo-kod';

@Injectable({
  providedIn: 'root'
})
export class PromoKodService {

  private baseUrl = 'http://localhost:8080/api/kodovi';

  constructor(private httpClient: HttpClient) { }

  getPromoKodByPrijavaId(prijavaId : number): Observable<PromoKod>{
    return this.httpClient.get<PromoKod>(`${this.baseUrl}/${prijavaId}`);
  }
}
