import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Player } from '../../interface/app.interface-player';
import { Observable } from 'rxjs';
import {Card} from "../../interface/app.interface-card";

@Injectable({
  providedIn: 'root'
})
export class PlayersService {

  private playersUrl: string = '/api/player';


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

  public createPlayer(uid:string): Observable<any> {
    const data: Player = {
      score: 0,
      cards: [],
      turn: false,
      user: uid
    }
    return this.http.post(this.playersUrl, data, this.httpOptions);
  }

}
