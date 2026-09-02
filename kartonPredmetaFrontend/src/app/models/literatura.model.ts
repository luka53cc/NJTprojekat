import { TipLiterature } from './tip-literature.model';

export interface Literatura {
  id?: number;
  naziv: string;
  autor: string;
  tip: TipLiterature;
}
