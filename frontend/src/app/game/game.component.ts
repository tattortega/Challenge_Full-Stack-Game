import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {Card} from '../interface/app.interface-card';
import {Player} from '../interface/app.interface-player';
import {PlayersService} from '../service/player/players.service';
import {ActivatedRoute} from "@angular/router";
import {GameService} from "../service/game/game.service";
import {Game} from "../interface/app.interface-game";


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
    private routeActive: ActivatedRoute) {
  }

  ngAfterViewInit(): void {
    this.getGame()
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

  betCards(idCard: string) {
    console.log(this.game)
    this.gameService.betCard(idCard, this.game).subscribe(game => {
      console.log(game)
      this.game = game;
    })
  }


  dropOnList($event: CdkDragDrop<Card[]>) {

    if ($event.previousContainer === $event.container) {
      moveItemInArray(
        $event.container.data,
        $event.previousIndex,
        $event.currentIndex
      )
    } else {
      transferArrayItem(
        $event.previousContainer.data,
        $event.container.data,
        $event.previousIndex,
        $event.currentIndex
      );
    }

    let cartaApostada: Card = $event.container.data[0]
    console.log(cartaApostada);
    this.betCards(cartaApostada.id);
  }

}
