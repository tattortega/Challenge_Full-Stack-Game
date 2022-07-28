import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateGameComponent } from './create-game/create-game.component';
import { GameComponent } from './game/game.component';
import { RegisterLoginComponent } from './register-login/register-login.component';
import { StartGameComponent } from './start-game/start-game.component';
import {AuthGuard} from "./guard/auth.guard";


const routes: Routes = [
  { path: '', redirectTo: 'registro', pathMatch: 'full' },
  { path: 'crear', component: CreateGameComponent, canActivate: [AuthGuard] },
  { path: 'iniciar/:id', component: StartGameComponent, canActivate: [AuthGuard] },
  { path: 'registro', component: RegisterLoginComponent },
  { path: 'juego/:id', component: GameComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
