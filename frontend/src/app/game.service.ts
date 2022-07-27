import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Observable, Subject} from 'rxjs';
import { Game } from './app.interface-game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private gameUrl = "/api/game";

  constructor(private httpClient: HttpClient, private router:Router) { }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  refresh$: Subject<void> = new Subject();

  getRefresh$(){
    return this.refresh$;
  }
  createGame(game: Game): Observable<any> {
    return this.httpClient.post(this.gameUrl, game, this.httpOptions);
  }

  startGame(game: Game): Observable<any> {
    return this.httpClient.post(`${this.gameUrl}/start`, game, this.httpOptions);
  }

  getGame(id: string): Observable<any> {
    return this.httpClient.get(`${this.gameUrl}/${id}`, this.httpOptions);
  }

}


