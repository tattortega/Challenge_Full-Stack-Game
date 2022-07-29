import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Game } from '../interface/app.interface-game';
import { GameService } from '../service/game/game.service';
import {AuthenticationService} from "../service/authentication/authentication.service";

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.css']
})
export class CreateGameComponent implements OnInit {

    @Input() game: Game;
    partidaId : string;

  constructor(
    private gameService: GameService,
    private router:Router,
    public serviceAuth: AuthenticationService) {}

  ngOnInit(): void {}

  create(): void {
    this.gameService.createGame({round:0, players:[], cards:[], board:null } as Game)
      .subscribe(game => {
        this.partidaId = game.id;
        this.router.navigate([`iniciar/${this.partidaId}`]);
      });
  }

}
