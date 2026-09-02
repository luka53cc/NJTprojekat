import { StatusPredmeta } from './status-predmeta.model';
import { Literatura } from './literatura.model';
import { PredispitnaObaveza } from './predispitna-obaveza.model';

export interface Predmet {
  id: number;
  naziv: string;
  sifra: string;
  godinaStudija: number;
  semestar: number;
  espb: number;
  fondPredavanja: number;
  fondVezbi: number;
  status: StatusPredmeta;
  cilj: string;
  ishodiUcenja: string;
  sadrzajPredavanja: string;
  sadrzajVezbi: string;
  nacinPolaganja: string;
  poeniIspit: number;
  studijskiProgramId: number;
  studijskiProgramNaziv?: string;
  modulId?: number;
  modulNaziv?: string;
  nosilacId: number;
  nosilacImePrezime?: string;
  nastavniciIds: number[];
  nastavniciImenaPrezimena?: string[];
  literatura: Literatura[];
  predispitneObaveze: PredispitnaObaveza[];
}
