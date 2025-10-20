package com.example.blackjack.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.blackjack.model.Card as GameCard

/**
 * Componente para mostrar una carta individual
 * @param card La carta a mostrar
 * @param modifier Modificador de Compose
 */
@Composable
fun CardView(
    card: GameCard,
    modifier: Modifier = Modifier
) {
    // Animación de entrada de la carta
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(card) {
        visible = true
    }

    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.8f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "cardScale"
    )

    Card(
        modifier = modifier
            .width(70.dp)
            .height(100.dp)
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (card.isFaceUp) Color.White else MaterialTheme.colorScheme.primary
        )
    ) {
        if (card.isFaceUp) {
            // Carta boca arriba
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = card.rank.displayName,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (card.isRed()) Color.Red else Color.Black
                    )
                    Text(
                        text = card.getSuitSymbol(),
                        fontSize = 32.sp,
                        color = if (card.isRed()) Color.Red else Color.Black
                    )
                }
            }
        } else {
            // Carta boca abajo - patrón de dorso
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.onPrimary,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }
    }
}

/**
 * Componente para mostrar una mano de cartas
 * @param cards Lista de cartas a mostrar
 * @param modifier Modificador de Compose
 */
@Composable
fun HandView(
    cards: List<GameCard>,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy((-40).dp)
    ) {
        cards.forEachIndexed { index, card ->
            CardView(
                card = card,
                modifier = Modifier.rotate(index * 2f - (cards.size - 1f))
            )
        }
    }
}
