import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Player } from './app.interface-player';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlayersService {

  private playersUrl: string = '/api/players';

  constructor(private http: HttpClient) { }

  public getPlayers(): Observable<any> {
    let header = new HttpHeaders().set('Type-content','application/json')
    return this.http.get(this.playersUrl, {headers: header});
  }

}
