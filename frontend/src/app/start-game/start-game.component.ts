import { Component, Input, OnInit, Output } from '@angular/core';
import { Board } from '../app.interface-board';
import { Game } from '../app.interface-game';
import { Player } from '../app.interface-player';
import { GameService } from '../game.service';
import { PlayersService } from '../players.service';
import { CreateGameComponent } from '../create-game/create-game.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-start-game',
  templateUrl: './start-game.component.html',
  styleUrls: ['./start-game.component.css']
})
export class StartGameComponent implements OnInit {

  game: Game;
  playersGame: Player[];
  board: Board = {
    id: "1",
    cardsBetPlayers: null,
    turn: null,
    winnerRound: null
  }

  constructor(private gameService: GameService, private playerService:PlayersService, private router:Router) { }

  ngOnInit(): void {
    this.playerService.getPlayers().subscribe(players => this.playersGame = players);
  }

  start(): void {
    this.gameService.startGame({id:"1233", round:0, players:this.playersGame, cards:[], board:this.board} as Game)
      .subscribe(game => this.game = game);
    this.router.navigate(['apuesta']);
  }

}
