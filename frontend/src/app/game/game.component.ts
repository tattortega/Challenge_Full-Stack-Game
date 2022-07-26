import { CdkDrag, CdkDragDrop, copyArrayItem, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component,  OnInit } from '@angular/core';
import { interval } from 'rxjs';
import { Card } from '../app.interface-card';
import { Player } from '../app.interface-player';
import { PlayersService } from '../players.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css'],
})
export class GameComponent implements OnInit {



  cards: Card[] = [{
    id: "1",
    description: "prueba",
    power: 150,
    status: false,
    image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F044.jpg?alt=media&token=f6f6b840-a785-46fc-839f-20e51344e454",
    feature: "descripción",
    level: 3
  },
  {
    id: "2",
    description: "prueba2",
    power: 180,
    status: false,
    image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F168.jpg?alt=media&token=322ad3a2-746d-4591-8721-f68bd1749b3c",
    feature: "descripción2",
    level: 4
    },
  {
    id: "3",
    description: "prueba3",
    power: 200,
    status: false,
    image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F0BF.jpg?alt=media&token=4768b45e-c24a-44e5-8bb6-8eed477569f8",
    feature: "descripción3",
    level: 5
  },{
    id: "4",
    description: "prueba",
    power: 150,
    status: false,
    image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F044.jpg?alt=media&token=f6f6b840-a785-46fc-839f-20e51344e454",
    feature: "descripción",
    level: 3
  },
  {
    id: "5",
    description: "prueba2",
    power: 180,
    status: false,
    image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F168.jpg?alt=media&token=322ad3a2-746d-4591-8721-f68bd1749b3c",
    feature: "descripción2",
    level: 4
    },
  {
    id: "6",
    description: "prueba3",
    power: 200,
    status: false,
    image: "https://firebasestorage.googleapis.com/v0/b/game-cards-de8c1.appspot.com/o/cards%2F0BF.jpg?alt=media&token=4768b45e-c24a-44e5-8bb6-8eed477569f8",
    feature: "descripción3",
    level: 5
  }];

  myList: Card[] = [];

  contador: number = 0;
  url: string = "";
  players: Player[] = [];

  constructor(private playersService: PlayersService) {
  }

  ngOnInit(): void {

    this.myList = [];
    this.url = this.cards[0].image;
    const obs$ = interval(4000);
    obs$.subscribe(a => {
      this.contador = a;
      this.url = this.cards[a].image;
    });
    this.getPlayers();
  }

  getPlayers(): void {
    this.playersService.getPlayers().subscribe(players => this.players = players);
  }

  dropOnList($event: CdkDragDrop<Card[]>) {
    console.log(this.cards);
    console.log(this.myList);
    console.log(this.players);

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
    //Obtenemos el elemento
    // const element = ($event.previousContainer.data as Array<Card>)[
    //   $event.previousIndex
    // ];
    // // //Comprobamos que no exista este elemento en el array
    // const isExist = ($event.container.data as Array<Card>).includes(element);

    // if (!isExist)
    //   copyArrayItem(
    //     $event.previousContainer.data,
    //     $event.container.data,
    //     $event.previousIndex,
    //     $event.currentIndex
    //   );
  }

}
