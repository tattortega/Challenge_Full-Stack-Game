import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Card} from '../app.interface-card';
import {Player} from '../app.interface-player';
import {PlayersService} from '../players.service';
import {ActivatedRoute} from "@angular/router";
import {GameService} from "../game.service";
import {Game} from "../app.interface-game";


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit, AfterViewInit {

  game: Game
  cards: Card[]
  myList: Card[] = [];
  partidaId: string;
  contador: number = 0;
  url: string = "";
  players: Player[] = [];

  constructor(
    private playersService: PlayersService,
    private gameService: GameService,
    private routeActive: ActivatedRoute)
  {}

  ngAfterViewInit(): void {
        this.getGame()
    console.log(this.game)
    }

  ngOnInit(): void {
    // this.myList = [];
    this.partidaId = this.routeActive.snapshot.params['id'];
    // const obs$ = interval(4000);
    // obs$.subscribe(a => {
    //   this.contador = a;
    //   this.url = this.cards[a].image;
    // });
    // this.cards = this.game.players[0].cards;
    // this.url = this.cards[0].image;
  }

  getGame(): void {
    this.gameService.getGame(this.partidaId).subscribe(games => {
      this.game = games;
      this.getCards()
    });
  }

  getCards() {
    this.game.players.forEach(player => {
      if (player.id === JSON.parse(localStorage.getItem('player')!).id) {
        this.cards = player.cards
      }
    })
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
