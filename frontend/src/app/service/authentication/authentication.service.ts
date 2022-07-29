import {Injectable, NgZone} from '@angular/core';
import {AngularFireAuth} from '@angular/fire/compat/auth';
import {AngularFirestore, AngularFirestoreDocument} from '@angular/fire/compat/firestore';
import {Router} from '@angular/router';
import * as auth from 'firebase/auth';
import {User} from '../../interface/app.interface-user';
import {PlayersService} from "../player/players.service";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  userData: any;

  constructor(
    public afAuth: AngularFireAuth,
    public afs: AngularFirestore,
    private router: Router,
    public ngZone: NgZone,
    private http: HttpClient,
    private playerService: PlayersService) {
    this.afAuth.authState.subscribe((user) => {
      if (user) {
        this.userData = user;
        localStorage.setItem('user', JSON.stringify(this.userData));
        JSON.parse(localStorage.getItem('user')!);
      } else {
        localStorage.setItem('user', 'null');
        JSON.parse(localStorage.getItem('user')!);
      }
    });
  }

  SignIn(email: string, password: string) {
    return this.afAuth
      .signInWithEmailAndPassword(email, password)
      .then((result) => {
        this.SetUserData(result.user);
        this.router.navigate(['crear']);
      })
      .catch((error) => {
        window.alert(error.message);
      });
  }

  SignOut() {
    return this.afAuth.signOut().then(() => {
      localStorage.removeItem('player');
      this.router.navigate(['registro']);
    });
  }

  SetUserData(user: any) {
    const userRef: AngularFirestoreDocument<any> = this.afs.doc(
      `users/${user.uid}`
    );
    const userData: User = {
      uid: user.uid,
      email: user.email,
      displayName: user.displayName,
      photoURL: user.photoURL,
      emailVerified: user.emailVerified,
    };
    return userRef.set(userData, {
      merge: true,
    });
  }

  AuthLogin(provider: any) {
    return this.afAuth
      .signInWithPopup(provider)
      .then((result) => {
        this.ngZone.run(() => {
          this.router.navigate(['crear']);
        });
        this.SetUserData(result.user);
        this.newPlayer(result.user.email ,result.user!.uid);
      })
      .catch((error) => {
        window.alert(error);
      });
  }

  GoogleAuth() {
    return this.AuthLogin(new auth.GoogleAuthProvider()).then((res: any) => {
      if (res) {
        this.router.navigate(['crear']);
      }
    });
  }

  SignUp(email: string, password: string) {
    return this.afAuth
      .createUserWithEmailAndPassword(email, password)
      .then((result) => {
        this.SetUserData(result.user);
        alert("Registro exitoso")
        document.getElementById("reg-log").click();
        this.newPlayer(result.user.email ,result.user!.uid);
      })
      .catch((error) => {
        window.alert(error.message);
      });
  }

  newPlayer(username:string,uid:string){
    this.playerService.getPlayer(uid).subscribe(data => {
      localStorage.setItem('player', JSON.stringify(data));
    }, error => {
      this.playerService.createPlayer(username,uid).subscribe(data => {
        localStorage.setItem('player', JSON.stringify(data));
      })
    })
  }
}
