import { Card } from "./app.interface-card";

export interface Player {
  id: string;
  score: number;
  cards: Card[];
  turn: boolean;
  user: string;
}

