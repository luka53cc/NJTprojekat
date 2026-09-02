import { Zvanje } from './zvanje.model';

export interface Nastavnik {
  id: number;
  ime: string;
  prezime: string;
  email: string;
  zvanje: Zvanje;
}
