import { Card } from "./app.interface-card";

export interface Player {
  id?: string;
  name: string,
  score: number;
  cards: Card[];
  turn: boolean;
  user: string;
}

