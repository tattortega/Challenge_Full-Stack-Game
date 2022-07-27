import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import {Component, Input, OnInit} from '@angular/core';
import {interval, Subscription} from 'rxjs';
import { Card } from '../app.interface-card';
import { Player } from '../app.interface-player';
import { PlayersService } from '../players.service';
import { ActivatedRoute} from "@angular/router";
import {GameService} from "../game.service";
import {Game} from "../app.interface-game";


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit {

  game: Game
  cards: Card[]
  myList: Card[] = [];
  partidaId: string;
  contador: number = 0;
  url: string = "";
  players: Player[] = [];
  subscripcion!: Subscription;
  constructor(
    private playersService: PlayersService,
    private gameService: GameService,
    private routeActive: ActivatedRoute)
  {this.subscripcion = this.gameService.refresh$.subscribe(()=>{
      this.getGame()
  })}

  ngOnInit(): void {
    this.myList = [];
    // const obs$ = interval(4000);
    // obs$.subscribe(a => {
    //   this.contador = a;
    //   this.url = this.cards[a].image;
    // });
    this.getGame();
    this.cards = this.game.players[0].cards;
    this.url = this.cards[0].image;
  }

  getGame(): void {
    let gameCreated = JSON.parse(localStorage.getItem('game')!);
    this.game = gameCreated;
    console.log(this.game)
    // this.partidaId = this.routeActive.snapshot.params['id'];
    // this.gameService.getGame(this.partidaId).subscribe(games => {
    //   console.log("game"+games)
    //     this.game.push(games);
    //   console.log(this.game)
    // });
  }



  dropOnList($event: CdkDragDrop<Card[]>) {

    if($event.previousContainer === $event.container){
      moveItemInArray(
      $event.container.data,
      $event.previousIndex,
      $event.currentIndex
      )
  }else{
    transferArrayItem(
      $event.previousContainer.data,
      $event.container.data,
      $event.previousIndex,
      $event.currentIndex
    );
  }
  }

}
