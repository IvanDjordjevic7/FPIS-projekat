import { Component, OnInit } from '@angular/core';
import { PrijavaService, IPrijava } from '../../services/prijava.service';
import { Prijava } from '../../common/prijava';
import { ManifestacijaService } from '../../services/manifestacija-service';
import { Manifestacija } from '../../common/manifestacija';
import { PromoKodService } from '../../services/promo-kod.service';
import { PromoKod } from '../../common/promo-kod';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-prijave',
  templateUrl: './prijave.component.html',
  styleUrl: './prijave.component.css'
})
export class PrijaveComponent {

  prijavaForm! : FormGroup;
  manifestacije: Manifestacija[] = [];
  tipoviPrijave = ['Slikarstvo', 'Fotografija', 'Oba dana'];
  updatedPrijava: Prijava = new Prijava();
  email: string = '';
  token: string = '';
  showForm: string = ''; // Za prikazivanje formulara (kreiraj, izmeni, otkazi)
  promoKod! : PromoKod;

  constructor(private manifestacijaService: ManifestacijaService, 
              private prijavaService: PrijavaService,
              private promoKodService: PromoKodService,
              private fb: FormBuilder) {
  }

  ngOnInit() {
    this.prijavaForm = this.fb.group({
      ime: ['', Validators.required],
      prezime: ['', Validators.required],
      profesija: [''],
      adresa1: ['', Validators.required],
      adresa2: [''],
      postanskiBroj: ['', Validators.required],
      mesto: ['', Validators.required],
      drzava: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      potvrdaEmail: ['', [Validators.required, Validators.email]],
      brojOsoba: ['', [Validators.required, Validators.min(1)]],
      manifestacijaId: ['', Validators.required],
      tipPrijave: ['', Validators.required],
      promoKodUnos: ['']
    });

    this.ucitajManifestacije();
  }

  ucitajManifestacije() {
    this.manifestacijaService.getAllManifestacije().subscribe(
      (data: Manifestacija[]) => {
        this.manifestacije = data;  // Dodeli dobijene manifestacije lokalnoj promenljivoj
      },
      error => {
        console.error('Greška prilikom dobavljanja manifestacija', error);
      }
    );
  }

  onCreatePrijava() {
    this.showForm = 'create';
  }

  onUpdatePrijava() {
    this.showForm = 'update';
  }

  onCancelPrijava() {
    this.showForm = 'cancel';
  }

  createPrijava() {
    if (this.prijavaForm.valid) {
      const prijava: IPrijava = this.prijavaForm.value;
      this.prijavaService.kreirajPrijavu(prijava).subscribe(response => {
        this.promoKodService.getPromoKodByPrijavaId(response.id).subscribe(
          promoKod => { 
            alert(`Prijava uspešno kreirana!\n Vaš token: ${response.token},\n Konačna cena: ${response.konacnaCena},
              \n Promo kod za popust:${promoKod.kod}`)})  
      }, error => {
        alert('Greška prilikom kreiranja prijave:'+ error.message);
      });
    }
  }
  

  updatePrijava() {
    this.prijavaService.updatePrijava(this.email, this.token, this.updatedPrijava.tipPrijave, this.updatedPrijava.brojOsoba).subscribe(
      response => {
        alert('Prijava uspešno izmenjena! Nova cena: ' + response.konacnaCena);
        this.resetForm();
      },
      error => {
        alert('Greška pri izmeni prijave: ' + error.message);
      }
    );
  }
  
  cancelPrijava() {
    this.prijavaService.otkaziPrijavu(this.email, this.token).subscribe(
      response => {
        alert('Uspesno otkazana prijava.');
        this.resetForm();
      },
      error => {
        alert('Greška pri otkazivanju prijave: ' + error.message);
      }
    );
  }

  private resetForm() {
    //this.prijava = new Prijava();
    this.updatedPrijava = new Prijava();
    this.email = '';
    this.token = '';
    this.showForm = '';
  }

}
