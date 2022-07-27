import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component,  OnInit } from '@angular/core';
import { interval } from 'rxjs';
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

  // cards: Card[] = [{
  //   id: "1",
  //   description: "prueba",
  //   power: 150,
  //   status: false,
  //   image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F044.jpg?alt=media&token=f6f6b840-a785-46fc-839f-20e51344e454",
  //   feature: "descripción",
  //   level: 3
  // },
  // {
  //   id: "2",
  //   description: "prueba2",
  //   power: 180,
  //   status: false,
  //   image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F168.jpg?alt=media&token=322ad3a2-746d-4591-8721-f68bd1749b3c",
  //   feature: "descripción2",
  //   level: 4
  //   },
  // {
  //   id: "3",
  //   description: "prueba3",
  //   power: 200,
  //   status: false,
  //   image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F0BF.jpg?alt=media&token=4768b45e-c24a-44e5-8bb6-8eed477569f8",
  //   feature: "descripción3",
  //   level: 5
  // },{
  //   id: "4",
  //   description: "prueba",
  //   power: 150,
  //   status: false,
  //   image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F044.jpg?alt=media&token=f6f6b840-a785-46fc-839f-20e51344e454",
  //   feature: "descripción",
  //   level: 3
  // },
  // {
  //   id: "5",
  //   description: "prueba2",
  //   power: 180,
  //   status: false,
  //   image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F168.jpg?alt=media&token=322ad3a2-746d-4591-8721-f68bd1749b3c",
  //   feature: "descripción2",
  //   level: 4
  //   },
  // {
  //   id: "6",
  //   description: "prueba3",
  //   power: 200,
  //   status: false,
  //   image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F0BF.jpg?alt=media&token=4768b45e-c24a-44e5-8bb6-8eed477569f8",
  //   feature: "descripción3",
  //   level: 5
  // }];
  game2: string = "";
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

  ngOnInit(): void {
    this.partidaId = this.routeActive.snapshot.paramMap.get("id");
    this.myList = [];
    // const obs$ = interval(4000);
    // obs$.subscribe(a => {
    //   this.contador = a;
    //   this.url = this.cards[a].image;
    // });
    // interval(2000).subscribe(card =>{
    //   this.cards = this.game.players[0].cards;
    //   this.url = this.cards[0].image;
    // }
  // )
    this.getGame();
  }

  getGame(): void {
    this.gameService.getGame(this.partidaId).subscribe(games => {
      console.log("game"+games)
        this.game = games;
      this.game2 = games.id;
      console.log(this.game)
    });
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
