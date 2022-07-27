import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Game } from '../app.interface-game';
import { GameService } from '../game.service';

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.css']
})
export class CreateGameComponent implements OnInit {

    @Input() game: Game;
    partidaId : string;

  constructor(private gameService: GameService, private router:Router) {}

  ngOnInit(): void {}

  create(): void {
    this.gameService.createGame({round:0, players:[], cards:[], board:null } as Game)
      .subscribe(game => {
        this.partidaId = game.id;
        console.log(game);
        this.router.navigate([`iniciar/${this.partidaId}`]);
      });
    // console.log(this.partidaId)
    // this.router.navigate([`iniciar/${this.partidaId}`]);
  }

}
