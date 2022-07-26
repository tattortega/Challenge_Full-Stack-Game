import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Game } from './app.interface-game';

@Injectable({
  providedIn: 'root'
})
export class GameService {

  private gameCreateUrl = "/api/game";
  private gameStartUrl = "/api/game/start";

  constructor(private httpClient: HttpClient, private router:Router) { }

  createGame(game: Game): Observable<Game> {
    return this.httpClient.post<Game>(this.gameCreateUrl, game, this.httpOptions);
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  startGame(game: Game): Observable<Game> {
    return this.httpClient.post<Game>(this.gameStartUrl, game, this.httpOptions);
  }

}

