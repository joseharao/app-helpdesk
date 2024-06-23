import { Tecnico } from './../models/tecnico';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_CONFIG } from '../config/api.config';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TecnicoService {

  constructor(private http:HttpClient) { }

  findAll():Observable<Tecnico[]>{
    return this.http.get<Tecnico[]>(`${API_CONFIG.base_url}/tecnicos`);
  }

  create(tecnico:Tecnico):Observable<Tecnico>{
     return this.http.post<Tecnico>(`${API_CONFIG.base_url}/tecnicos`, tecnico);
  }
}
