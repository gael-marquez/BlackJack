package com.example.blackjack.ui.components

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blackjack.model.GamePhase
import com.example.blackjack.model.GameResult

/**
 * Componente para los controles del juego
 * @param phase Fase actual del juego
 * @param result Resultado del juego
 * @param playerBalance Saldo del jugador
 * @param currentBet Apuesta actual
 * @param onPlaceBet Callback para realizar una apuesta
 * @param onHit Callback para pedir carta
 * @param onStand Callback para plantarse
 * @param onNewRound Callback para nueva ronda
 * @param modifier Modificador de Compose
 */
@Composable
fun GameControls(
    phase: GamePhase,
    result: GameResult,
    playerBalance: Int,
    currentBet: Int,
    onPlaceBet: (Int) -> Unit,
    onHit: () -> Unit,
    onStand: () -> Unit,
    onNewRound: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar saldo y apuesta actual
        BalanceDisplay(
            balance = playerBalance,
            currentBet = currentBet,
            phase = phase
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Controles según la fase del juego
        when (phase) {
            GamePhase.BETTING -> {
                BettingControls(
                    balance = playerBalance,
                    onPlaceBet = onPlaceBet
                )
            }
            GamePhase.PLAYER_TURN -> {
                PlayerTurnControls(
                    onHit = onHit,
                    onStand = onStand
                )
            }
            GamePhase.DEALER_TURN -> {
                Text(
                    text = "Turno del dealer...",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            GamePhase.GAME_OVER -> {
                GameOverControls(
                    result = result,
                    onNewRound = onNewRound
                )
            }
        }
    }
}

/**
 * Componente para mostrar el saldo y la apuesta
 */
@Composable
private fun BalanceDisplay(
    balance: Int,
    currentBet: Int,
    phase: GamePhase
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Saldo",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "$$balance",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
        
        if (phase != GamePhase.BETTING && currentBet > 0) {
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Apuesta",
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Text(
                        text = "$$currentBet",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

/**
 * Controles para realizar apuestas
 */
@Composable
private fun BettingControls(
    balance: Int,
    onPlaceBet: (Int) -> Unit
) {
    val betAmounts = listOf(10, 25, 50, 100, 250, 500)
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Selecciona tu apuesta",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            betAmounts.chunked(3).forEach { row ->
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEach { amount ->
                        Button(
                            onClick = { onPlaceBet(amount) },
                            enabled = amount <= balance,
                            modifier = Modifier
                                .width(100.dp)
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                            )
                        ) {
                            Text(
                                text = "$$amount",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Controles para el turno del jugador
 */
@Composable
private fun PlayerTurnControls(
    onHit: () -> Unit,
    onStand: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(
            onClick = onHit,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50)
            )
        ) {
            Text(
                text = "PEDIR",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        
        Button(
            onClick = onStand,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF9800)
            )
        ) {
            Text(
                text = "PLANTARSE",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Controles para cuando termina el juego
 */
@Composable
private fun GameOverControls(
    result: GameResult,
    onNewRound: () -> Unit
) {
    // Animación de pulso para el botón
    val infiniteTransition = rememberInfiniteTransition(label = "buttonAnimation")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "buttonScale"
    )
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Mostrar mensaje de resultado
        val (resultText, resultColor) = when (result) {
            GameResult.PLAYER_WIN -> "¡GANASTE!" to Color(0xFF4CAF50)
            GameResult.DEALER_WIN -> "PERDISTE" to Color(0xFFF44336)
            GameResult.PUSH -> "EMPATE" to Color(0xFF2196F3)
            GameResult.BLACKJACK -> "¡BLACKJACK!" to Color(0xFFFFD700)
            GameResult.NONE -> "" to MaterialTheme.colorScheme.onBackground
        }
        
        if (resultText.isNotEmpty()) {
            Text(
                text = resultText,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = resultColor,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        
        Button(
            onClick = onNewRound,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "NUEVA RONDA",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
