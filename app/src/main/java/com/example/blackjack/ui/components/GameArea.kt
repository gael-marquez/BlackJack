package com.example.blackjack.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blackjack.model.Hand

/**
 * Componente para mostrar el área de juego del dealer
 * @param hand Mano del dealer
 * @param modifier Modificador de Compose
 */
@Composable
fun DealerArea(
    hand: Hand,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dealer",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Mostrar valor solo si todas las cartas están visibles
        val showValue = hand.cards.all { it.isFaceUp }
        AnimatedVisibility(
            visible = showValue && hand.cards.isNotEmpty(),
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            ScoreDisplay(
                score = hand.calculateValue(),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        if (hand.cards.isNotEmpty()) {
            HandView(cards = hand.cards)
        } else {
            // Placeholder cuando no hay cartas
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(100.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
    }
}

/**
 * Componente para mostrar el área de juego del jugador
 * @param hand Mano del jugador
 * @param modifier Modificador de Compose
 */
@Composable
fun PlayerArea(
    hand: Hand,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (hand.cards.isNotEmpty()) {
            HandView(cards = hand.cards)
        } else {
            // Placeholder cuando no hay cartas
            Box(
                modifier = Modifier
                    .width(70.dp)
                    .height(100.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        AnimatedVisibility(
            visible = hand.cards.isNotEmpty(),
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            ScoreDisplay(
                score = hand.calculateValue(),
                isPlayer = true,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Jugador",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold
        )
    }
}

/**
 * Componente para mostrar la puntuación
 * @param score Puntuación a mostrar
 * @param isPlayer Si es el jugador o el dealer
 * @param modifier Modificador de Compose
 */
@Composable
fun ScoreDisplay(
    score: Int,
    isPlayer: Boolean = false,
    modifier: Modifier = Modifier
) {
    // Animación de pulso para puntuaciones especiales
    val infiniteTransition = rememberInfiniteTransition(label = "scoreAnimation")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = if (score == 21) 1.1f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scoreScale"
    )

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                score > 21 -> Color.Red.copy(alpha = 0.7f)
                score == 21 -> Color(0xFFFFD700).copy(alpha = 0.7f)
                else -> MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
            }
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = score.toString(),
            fontSize = (32 * scale).sp,
            fontWeight = FontWeight.Bold,
            color = when {
                score > 21 -> Color.White
                score == 21 -> Color.Black
                else -> MaterialTheme.colorScheme.onSurface
            },
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp)
        )
    }
}
