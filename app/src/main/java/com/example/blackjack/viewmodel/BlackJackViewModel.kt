package com.example.blackjack.viewmodel

import androidx.lifecycle.ViewModel
import com.example.blackjack.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel para manejar la lógica del juego de BlackJack
 * Usa StateFlow para gestión de estado reactiva
 */
class BlackJackViewModel : ViewModel() {
    
    private val _gameState = MutableStateFlow(GameState())
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    /**
     * Inicia un nuevo juego con una apuesta
     * @param betAmount Cantidad de la apuesta
     */
    fun placeBet(betAmount: Int) {
        val currentState = _gameState.value
        
        if (betAmount > currentState.playerBalance) {
            _gameState.value = currentState.copy(
                message = "¡Saldo insuficiente!"
            )
            return
        }

        if (betAmount <= 0) {
            _gameState.value = currentState.copy(
                message = "¡La apuesta debe ser mayor a 0!"
            )
            return
        }

        // Iniciar nuevo juego
        val newDeck = Deck()
        
        // Repartir cartas iniciales
        val playerCard1 = newDeck.deal() ?: return
        val dealerCard1 = newDeck.deal() ?: return
        val playerCard2 = newDeck.deal() ?: return
        val dealerCard2 = newDeck.deal(faceUp = false) ?: return // Segunda carta del dealer boca abajo

        val playerHand = Hand()
            .addCard(playerCard1)
            .addCard(playerCard2)
        
        val dealerHand = Hand()
            .addCard(dealerCard1)
            .addCard(dealerCard2)

        // Verificar BlackJack
        if (playerHand.isBlackJack()) {
            val finalDealerHand = dealerHand.revealAllCards()
            _gameState.value = GameState(
                playerHand = playerHand,
                dealerHand = finalDealerHand,
                deck = newDeck,
                phase = GamePhase.GAME_OVER,
                result = GameResult.BLACKJACK,
                playerBalance = currentState.playerBalance + (betAmount * 3 / 2),
                currentBet = betAmount,
                message = "¡BlackJack! Ganaste ${betAmount * 3 / 2} fichas"
            )
        } else {
            _gameState.value = GameState(
                playerHand = playerHand,
                dealerHand = dealerHand,
                deck = newDeck,
                phase = GamePhase.PLAYER_TURN,
                result = GameResult.NONE,
                playerBalance = currentState.playerBalance - betAmount,
                currentBet = betAmount,
                message = "Tu turno. ¿Pedir carta o plantarse?"
            )
        }
    }

    /**
     * Jugador pide una carta
     */
    fun hit() {
        val currentState = _gameState.value
        
        if (currentState.phase != GamePhase.PLAYER_TURN) return

        val newCard = currentState.deck.deal() ?: return
        val newPlayerHand = currentState.playerHand.addCard(newCard)

        if (newPlayerHand.isBusted()) {
            // Jugador se pasó
            val finalDealerHand = currentState.dealerHand.revealAllCards()
            _gameState.value = currentState.copy(
                playerHand = newPlayerHand,
                dealerHand = finalDealerHand,
                phase = GamePhase.GAME_OVER,
                result = GameResult.DEALER_WIN,
                message = "¡Te pasaste! Perdiste ${currentState.currentBet} fichas"
            )
        } else {
            _gameState.value = currentState.copy(
                playerHand = newPlayerHand,
                message = "Tienes ${newPlayerHand.calculateValue()}. ¿Pedir otra carta?"
            )
        }
    }

    /**
     * Jugador se planta
     */
    fun stand() {
        val currentState = _gameState.value
        
        if (currentState.phase != GamePhase.PLAYER_TURN) return

        // Revelar la carta oculta del dealer
        var dealerHand = currentState.dealerHand.revealAllCards()
        
        // El dealer debe pedir cartas hasta llegar a 17 o más
        while (dealerHand.calculateValue() < 17) {
            val newCard = currentState.deck.deal() ?: break
            dealerHand = dealerHand.addCard(newCard)
        }

        // Determinar el ganador
        val playerValue = currentState.playerHand.calculateValue()
        val dealerValue = dealerHand.calculateValue()
        
        val (result, message, balanceChange) = when {
            dealerHand.isBusted() -> {
                Triple(
                    GameResult.PLAYER_WIN,
                    "¡El dealer se pasó! Ganaste ${currentState.currentBet} fichas",
                    currentState.currentBet * 2
                )
            }
            playerValue > dealerValue -> {
                Triple(
                    GameResult.PLAYER_WIN,
                    "¡Ganaste! ${playerValue} vs ${dealerValue}. +${currentState.currentBet} fichas",
                    currentState.currentBet * 2
                )
            }
            playerValue < dealerValue -> {
                Triple(
                    GameResult.DEALER_WIN,
                    "Perdiste. ${playerValue} vs ${dealerValue}. -${currentState.currentBet} fichas",
                    0
                )
            }
            else -> {
                Triple(
                    GameResult.PUSH,
                    "¡Empate! ${playerValue} vs ${dealerValue}. Recuperas tu apuesta",
                    currentState.currentBet
                )
            }
        }

        _gameState.value = currentState.copy(
            dealerHand = dealerHand,
            phase = GamePhase.GAME_OVER,
            result = result,
            playerBalance = currentState.playerBalance + balanceChange,
            message = message
        )
    }

    /**
     * Reinicia el juego para una nueva ronda
     */
    fun newRound() {
        val currentBalance = _gameState.value.playerBalance
        _gameState.value = GameState(
            playerBalance = currentBalance,
            message = if (currentBalance > 0) {
                "Haz tu apuesta para la siguiente ronda"
            } else {
                "¡Te quedaste sin fichas! El juego se reiniciará"
            }
        )
        
        // Si el jugador se quedó sin fichas, reiniciar el saldo
        if (currentBalance <= 0) {
            _gameState.value = GameState(
                playerBalance = 1000,
                message = "Nuevo juego iniciado con 1000 fichas"
            )
        }
    }

    /**
     * Reinicia completamente el juego
     */
    fun resetGame() {
        _gameState.value = GameState()
    }
}
