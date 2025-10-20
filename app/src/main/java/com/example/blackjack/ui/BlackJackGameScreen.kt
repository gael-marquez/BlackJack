package com.example.blackjack.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.blackjack.ui.components.*
import com.example.blackjack.viewmodel.BlackJackViewModel

/**
 * Pantalla principal del juego de BlackJack
 * @param viewModel ViewModel del juego
 */
@Composable
fun BlackJackGameScreen(
    viewModel: BlackJackViewModel = viewModel()
) {
    val gameState by viewModel.gameState.collectAsState()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Área del dealer
            DealerArea(
                hand = gameState.dealerHand,
                modifier = Modifier.weight(1f)
            )

            // Mensaje de estado
            MessageDisplay(
                message = gameState.message,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Área del jugador
            PlayerArea(
                hand = gameState.playerHand,
                modifier = Modifier.weight(1f)
            )

            // Controles del juego
            GameControls(
                phase = gameState.phase,
                result = gameState.result,
                playerBalance = gameState.playerBalance,
                currentBet = gameState.currentBet,
                onPlaceBet = { amount -> viewModel.placeBet(amount) },
                onHit = { viewModel.hit() },
                onStand = { viewModel.stand() },
                onNewRound = { viewModel.newRound() },
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}
