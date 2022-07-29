import {CdkDragDrop, moveItemInArray, transferArrayItem} from '@angular/cdk/drag-drop';
import {AfterViewInit, Component, OnInit} from '@angular/core';
import {interval, Subscription} from 'rxjs';
import {Card} from '../interface/app.interface-card';
import {Player} from '../interface/app.interface-player';
import {PlayersService} from '../service/player/players.service';
import {ActivatedRoute} from "@angular/router";
import {GameService} from "../service/game/game.service";
import {Game} from "../interface/app.interface-game";
import {Board} from "../interface/app.interface-board";


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit, AfterViewInit {

  game: Game
  cards: Card[]
  board: Board
  myList: Card[] = [];
  partidaId: string;
  contador: number = 0;
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
    this.myList = [];
    this.partidaId = this.routeActive.snapshot.params['id'];
  }

  getGame(): void {
    this.gameService.getGame(this.partidaId).subscribe(games => {
      this.game = games;
      this.board = this.game.board
      this.getCards()

    });
  }

  getCards() {
    this.game.players.forEach(player => {
      if (player.id === JSON.parse(localStorage.getItem('player')!).id) {
        this.cards = player.cards
      }
    })
    this.turn()
  }

  turn(){
    let map = this.board.turn
    console.log(map)
    const output = document.querySelector("#output");
    const display = s => console.log(s);
    const delayLoop = (fn, delay) => {
      return (name, i) => {
        setTimeout(() => {
          // alert("Es tu turno de apostar")
        }, i * 1000);
      }
    };

    Object.keys(map).forEach(delayLoop(display, 1000));
    // Object.keys(map).forEach(function(key){
      // if (key == "1"){
      //   console.log("entro")
      //   alert("Es tu turno de apostar")
      //   const obs$ = interval(5000);
      //   obs$.subscribe(a => {
      //     this.contador = a;
      //   });
      // }
      // let turn = map[key]
    //   if (turn.id != JSON.parse(localStorage.getItem('player')!).id) {
    //     alert("Es tu turno de apostar")
    //     const obs$ = interval(5000);
    //     obs$.subscribe(a => {
    //       this.contador = a;
    //     });
    //   }
    // })
  }

  betCards(idCard: string) {
    console.log(this.game)
    this.gameService.betCard(idCard, this.game).subscribe(game => {
      this.game = game;
      Object.keys(this.game.board.cardsBetPlayers).forEach(p=>{
        this.myList.push(this.game.board.cardsBetPlayers[p])
      })
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
    // if (confirm("turno")) {
    //   window.location.reload()
    // }
  }

}
