export interface LoginRequest {
  korisnickoIme: string;
  lozinka: string;
}

export interface LoginResponse {
  token: string;
  korisnickoIme: string;
  uloga: string;
}
