import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Manifestacija } from '../common/manifestacija';

@Injectable({
  providedIn: 'root'
})
export class ManifestacijaService {

  private baseUrl = 'http://localhost:8080/api/manifestacije';

  constructor(private httpClient: HttpClient) { }

  
  getManifestacija(theManifestacijaId: number): Observable<Manifestacija> {
    
    // need to build URL based on manifestacija id
    const manifestacijaUrl = `${this.baseUrl}/${theManifestacijaId}`;

    return this.httpClient.get<Manifestacija>(manifestacijaUrl);
  }

  getAllManifestacije(): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/all`);
  }

  getManifestacijaDetalji(manifestacijaId: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}/${manifestacijaId}`);
  }


}


