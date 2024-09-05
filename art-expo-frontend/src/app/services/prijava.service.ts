import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prijava } from '../common/prijava';

export interface Manifestacija {
  id: number;
  naziv: string;
}


export interface IPrijava {
  ime: string;
  prezime: string;
  profesija?: string;
  adresa1: string;
  adresa2?: string;
  postanskiBroj: string;
  mesto: string;
  drzava: string;
  email: string;
  potvrdaEmail: string;
  brojOsoba: number;
  manifestacijaId: number;
  tipPrijave: string;
  promoKodUnos?: string;
}

@Injectable({
  providedIn: 'root'
})

export class PrijavaService {

  private baseUrl = 'http://localhost:8080/api/prijave';

  constructor(private httpClient: HttpClient) { }

  

  kreirajPrijavu(prijava: IPrijava): Observable<any> {
    console.log(`${this.baseUrl}/kreiraj`, prijava);
    return this.httpClient.post(`${this.baseUrl}/kreiraj`, prijava);
  }
  
  
  updatePrijava(email: string, token: string, novaTipPrijave: string, noviBrojOsoba: number): Observable<Prijava> {
    return this.httpClient.put<Prijava>(`${this.baseUrl}/izmena`, { email, token, novaTipPrijave, noviBrojOsoba });
  }

  otkaziPrijavu(email: string, token: string): Observable<Prijava> {
    return this.httpClient.put<Prijava>(`${this.baseUrl}/otkazivanje`, { email, token });
  }

  
  
}
