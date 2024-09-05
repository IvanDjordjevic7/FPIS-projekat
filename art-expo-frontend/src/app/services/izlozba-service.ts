import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Izlozba } from '../common/izlozba';
import { Umetnik } from '../common/umetnik';

@Injectable({
  providedIn: 'root'
})
export class IzlozbaService {

  private baseUrl = 'http://localhost:8080/api/izlozbe';

  constructor(private httpClient: HttpClient) { }

  // Metoda koja vraća izložbe po ID-u manifestacije
  getIzlozbeByManifestacijaId(manifestacijaId: number): Observable<Izlozba[]> {
    return this.httpClient.get<Izlozba[]>(`${this.baseUrl}/manifestacija/${manifestacijaId}`);
  }

  // Dobijanje broja slobodnih mesta na osnovu ID izložbe
  getBrojSlobodnihMesta(izlozbaId: number): Observable<number> {
    return this.httpClient.get<number>(`${this.baseUrl}/${izlozbaId}/slobodna-mesta`);
  }

  // Dobijanje umetnika po ID izložbe
  getUmetniciByIzlozbaId(izlozbaId: number): Observable<Umetnik[]> {
    return this.httpClient.get<Umetnik[]>(`${this.baseUrl}/izlozba/${izlozbaId}`);
  }

}
