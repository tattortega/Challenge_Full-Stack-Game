import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateGameComponent } from './create-game/create-game.component';
import { GameComponent } from './game/game.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { StartGameComponent } from './start-game/start-game.component';


const routes: Routes = [
  { path: '', redirectTo: '/registro', pathMatch: 'full' },
  { path: 'crear', component: CreateGameComponent },
  { path: 'iniciar', component: StartGameComponent },
  { path: 'registro', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
  { path: 'apuesta', component: GameComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
