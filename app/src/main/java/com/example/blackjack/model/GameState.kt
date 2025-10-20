package com.example.blackjack.model

/**
 * Representa el estado del juego
 */
enum class GamePhase {
    BETTING,        // Apostando
    PLAYER_TURN,    // Turno del jugador
    DEALER_TURN,    // Turno del dealer
    GAME_OVER       // Juego terminado
}

/**
 * Resultado del juego
 */
enum class GameResult {
    NONE,           // Sin resultado aún
    PLAYER_WIN,     // Jugador gana
    DEALER_WIN,     // Dealer gana
    PUSH,           // Empate
    BLACKJACK       // BlackJack del jugador
}

/**
 * Estado completo del juego de BlackJack
 * @property playerHand Mano del jugador
 * @property dealerHand Mano del dealer
 * @property deck Mazo de cartas
 * @property phase Fase actual del juego
 * @property result Resultado del juego
 * @property playerBalance Saldo del jugador
 * @property currentBet Apuesta actual
 */
data class GameState(
    val playerHand: Hand = Hand(),
    val dealerHand: Hand = Hand(),
    val deck: Deck = Deck(),
    val phase: GamePhase = GamePhase.BETTING,
    val result: GameResult = GameResult.NONE,
    val playerBalance: Int = 1000,
    val currentBet: Int = 0,
    val message: String = "¡Bienvenido al BlackJack!"
)
