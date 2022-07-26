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

  public getPlayers(): Observable<Player[]> {
    let header = new HttpHeaders().set('Type-content','aplication/json')
    return this.http.get<Player[]>(this.playersUrl, {headers: header});
  }

}
