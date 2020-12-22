package server.messages;

public enum MessageType {
  HelloClient, HelloServer, Welcome, PlayerValues, PlayerAdded, SetStatus, PlayerStatus, GameStarted, SendChat, ReceviedChat, Error, PlayCard, CardPlayed, CurrentPlayer, ActivePhase, SetStartingPoint, StartingPointTaken, YourCards, NotYourCards, ShuffleCoding, SelectCard, CardSelected, SelectionFinished, TimerStarted, TimerEnded, DiscardHand, CardsYouGotNow, CurrentCards, PlayIt, Movement, DrawDamage, PickDamage, SelectDamage, PlayerShooting, Reboot, PlayerTurning, Energy, CheckpointReached, GameWon
}