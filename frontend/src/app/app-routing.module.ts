import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateGameComponent } from './create-game/create-game.component';
import { GameComponent } from './game/game.component';
import { RegisterLoginComponent } from './register-login/register-login.component';
import { StartGameComponent } from './start-game/start-game.component';


const routes: Routes = [
  { path: '', redirectTo: '/registro', pathMatch: 'full' },
  { path: 'crear', component: CreateGameComponent },
  { path: 'iniciar/:id', component: StartGameComponent },
  { path: 'registro', component: RegisterLoginComponent },
  { path: 'juego/:id', component: GameComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
