import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { ScrollingModule } from '@angular/cdk/scrolling';

import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { CreateGameComponent } from './create-game/create-game.component';
import { RouterModule, Routes } from '@angular/router';
import { StartGameComponent } from './start-game/start-game.component';
import { LoginComponent } from './login/login.component';
import { AngularFireAuthModule } from '@angular/fire/compat/auth';
import { AngularFireModule } from '@angular/fire/compat';
import { environment } from 'src/environments/environment';
import { AngularFirestore, AngularFirestoreDocument, AngularFirestoreModule } from '@angular/fire/compat/firestore';
import { RegisterComponent } from './register/register.component';
import { AppRoutingModule } from './app-routing.module';
import { GameComponent } from './game/game.component';

// const routes: Routes = [
//   { path: '', redirectTo: '/registro', pathMatch: 'full' },
//   { path: 'crear', component: CreateGameComponent },
//   { path: 'iniciar', component: StartGameComponent },
//   { path: 'registro', component: RegisterComponent },
//   { path: 'login', component: LoginComponent },
// ];

@NgModule({
  declarations: [
    AppComponent,
    GameComponent,
    CreateGameComponent,
    StartGameComponent,
    LoginComponent,
    RegisterComponent
  ],
  imports: [
    BrowserModule,
    DragDropModule,
    ScrollingModule,
    HttpClientModule,
    // RouterModule.forRoot(routes),
    AngularFireAuthModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    AngularFirestoreModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
