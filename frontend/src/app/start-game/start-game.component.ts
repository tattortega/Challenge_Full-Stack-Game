import { Component, Input, OnInit, Output } from '@angular/core';
import { Board } from '../app.interface-board';
import { Game } from '../app.interface-game';
import { Player } from '../app.interface-player';
import { GameService } from '../game.service';
import { PlayersService } from '../players.service';
import { CreateGameComponent } from '../create-game/create-game.component';
import { Router } from '@angular/router';
import { ActivatedRoute} from "@angular/router";
import {CdkDragDrop, moveItemInArray, transferArrayItem} from "@angular/cdk/drag-drop";
import {Card} from "../app.interface-card";

@Component({
  selector: 'app-start-game',
  templateUrl: './start-game.component.html',
  styleUrls: ['./start-game.component.css']
})
export class StartGameComponent implements OnInit {

  game: Game;
  partidaId :string;
  playersGame: Player[] = [];
  rivals: Player[] = [];
  board: Board = {
    id: "1",
    cardsBetPlayers: null,
    turn: null,
    winnerRound: null
  }

  constructor(private gameService: GameService, private playerService:PlayersService, private router:Router,
  private routeActive:ActivatedRoute) { }

  ngOnInit(): void {
    this.partidaId = this.routeActive.snapshot.paramMap.get("id");
    this.playerService.getPlayers().subscribe(players => {
      players.forEach(player => {
        if (player.id !== JSON.parse(localStorage.getItem('player')!).id) {
          this.playersGame.push(player)
        }
      })
    });
  }

  start(): void {
    let playerOwnerGame = JSON.parse(localStorage.getItem('player')!);
    this.rivals.push(playerOwnerGame);
    this.gameService.startGame({id:this.partidaId, round:0, players:this.rivals, cards:[], board:this.board} as Game)
      .subscribe(game => {
        this.game = game;
        this.partidaId = game.id;
      });
    this.router.navigate([`juego/${this.partidaId}`]);

  }

  drop(event: CdkDragDrop<Player[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }


}
