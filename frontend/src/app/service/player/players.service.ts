import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Player } from '../../interface/app.interface-player';
import { Observable } from 'rxjs';
import {environment} from "../../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class PlayersService {

  private playersUrl: string = environment.baseUrl+'/api/player';


  constructor(private http: HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  public getPlayers(): Observable<any> {
    return this.http.get(this.playersUrl, this.httpOptions);
  }

  public getPlayer(uid:string): Observable<any> {
    return this.http.get(`${this.playersUrl}/user/${uid}`, this.httpOptions);
  }

  public createPlayer(username:string,uid:string): Observable<any> {
    const data: Player = {
      score: 0,
      name: username,
      cards: [],
      turn: false,
      user: uid
    }
    return this.http.post(this.playersUrl, data, this.httpOptions);
  }

}
