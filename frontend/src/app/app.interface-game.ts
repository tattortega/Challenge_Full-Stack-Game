import { Player } from "./app.interface-player";
import { Card } from "./app.interface-card";
import { Board } from "./app.interface-board";

export interface Game {
  id: string;
  round: number;
  players: Player[];
  cards: Card[];
  board: Board;
}

