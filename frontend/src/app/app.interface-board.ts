import { Player } from "./app.interface-player";
import { Card } from "./app.interface-card";

export interface Board {
  id: string;
  cardsBetPlayers: Map<string, Card>;
  turn: Map<number, Player>;
  winnerRound: Player;
}

