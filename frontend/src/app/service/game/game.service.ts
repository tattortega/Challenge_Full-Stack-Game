import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Observable, Subject, tap} from 'rxjs';
import { Game } from '../../interface/app.interface-game';

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

  getGame(id: string): Observable<Game> {
    return this.httpClient.get<Game>(`${this.gameUrl}/${id}`, this.httpOptions);
  }

  betCard(idCard: string, game:Game): Observable<any> {
    console.log(idCard)
    console.log(game)
    let res = this.httpClient.post(`${this.gameUrl}/bet-card/${idCard}`,game, this.httpOptions)
    console.log(res.subscribe(p=>{
      console.log(p)
    }))
    return res;
    // .pipe(tap(() => this.getRefresh$().next()))
  }



}


