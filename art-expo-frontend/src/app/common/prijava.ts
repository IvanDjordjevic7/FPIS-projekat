import { Manifestacija } from "./manifestacija";

export class Prijava {

    id! : number;
    ime! : string;
    prezime! : string;
    profesija! : string;
    adresa1! : string;
    adresa2! : string;
    postanskiBroj! : string;
    mesto! : string;
    drzava! : string;
    email! : string;
    potvrdaEmail! : string;
    token! : string;
    promoKodUnos! : string;
    brojOsoba! : number;
    datumPrijave! : Date;
    otkazana! : boolean;
    tipPrijave! : string;
    konacnaCena! : number; 
    manifestacijaId! : number;

}
