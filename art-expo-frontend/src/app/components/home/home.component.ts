import { Component, OnInit } from '@angular/core';
import { ManifestacijaService } from '../../services/manifestacija-service';
import { Manifestacija } from '../../common/manifestacija';
import { Izlozba } from '../../common/izlozba';
import { IzlozbaService } from '../../services/izlozba-service';
import { Umetnik } from '../../common/umetnik';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  manifestacije: Manifestacija[] = []; // Lista manifestacija
  selectedManifestacija: Manifestacija | null = null; // Izabrana manifestacija
  izlozbe: Izlozba[] = []; // Lista izložbi za izabranu manifestaciju
  umetniciMap: { [key: number]: Umetnik[] } = {}; // Mapa ID izložbe -> Lista umetnika
  slobodnaMestaMap: { [key: number]: number } = {}; // Mapa ID izložbe -> Broj slobodnih mesta
  pregledVisible = false;
  prijaveVisible = false;

  constructor(private manifestacijaService: ManifestacijaService, private izlozbaService: IzlozbaService) {} // Injektuj ApiService

  ngOnInit() {
    this.loadManifestacije(); // Učitaj manifestacije kad se komponenta inicijalizuje
  }

 
  loadManifestacije() {
    this.manifestacijaService.getAllManifestacije().subscribe(
      data => {
        console.log('Manifestacije =' + JSON.stringify(data));
        this.manifestacije = data;
      }
    );
  }

  showPregled() {
    this.pregledVisible = true;
    this.prijaveVisible = false;
  }

  showPrijave() {
    this.pregledVisible = false;
    this.prijaveVisible = true;
  }

  onManifestacijaChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const manifestacijaId = parseInt(selectElement.value, 10);

    // Učitaj detalje manifestacije na osnovu ID-a
    this.manifestacijaService.getManifestacija(manifestacijaId).subscribe(
      data => {
        this.selectedManifestacija = data;
        console.log('Izabrana manifestacija:', this.selectedManifestacija);
        
        // Učitaj izložbe za izabranu manifestaciju
        this.loadIzlozbe(manifestacijaId);
      },
      error => {
        console.error('Error fetching manifestacija details:', error);
      }
    );
  }

  loadIzlozbe(manifestacijaId: number) {
    this.izlozbaService.getIzlozbeByManifestacijaId(manifestacijaId).subscribe(
      izlozbe => {
        this.izlozbe = izlozbe;
        izlozbe.forEach(izlozba => {
          // Učitaj broj slobodnih mesta za svaku izložbu
          this.izlozbaService.getBrojSlobodnihMesta(izlozba.id).subscribe(
            slobodnaMesta => {
              this.slobodnaMestaMap[izlozba.id] = slobodnaMesta;
            }
          );

          // Učitaj umetnike za svaku izložbu
          this.izlozbaService.getUmetniciByIzlozbaId(izlozba.id).subscribe(
            umetnici => {
              this.umetniciMap[izlozba.id] = umetnici;
            }
          );
        });
      }
    );
  }



  
}


