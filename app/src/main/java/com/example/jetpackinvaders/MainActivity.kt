package com.example.jetpackinvaders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackinvaders.ui.theme.JetpackInvadersTheme

enum class AppScreen {
    MENU,
    GAME
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackInvadersTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var currentScreen by remember { mutableStateOf(AppScreen.MENU) }

                    when (currentScreen) {
                        AppScreen.MENU -> {
                            ComponentsScreen(
                                onStartClick = { currentScreen = AppScreen.GAME },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                        AppScreen.GAME -> {
                            GameOverScreen(
                                onBackToMenu = { currentScreen = AppScreen.MENU },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComponentsScreen(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SCORE: 0050",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "LIVES: ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                val enemyColors = listOf(
                    Color(0xFF16F40E),
                    Color(0xFF16F40E),
                    Color(0xFF16F40E)
                )

                enemyColors.forEach { color ->
                    Invaders(
                        color = color,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(horizontal = 2.dp)
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val enemyColors = listOf(
                Color(0xFF16F40E),
                Color(0xFFF50000),
                Color(0xFF042BD3),
                Color(0xFFFFEB3B),
                Color(0xFF9434C2)
            )

            enemyColors.forEach { color ->
                Invaders(
                    color = color,
                    modifier = Modifier
                        .size(50.dp)
                        .padding(3.dp)
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        SpaceShip(
            color = Color.White,
            modifier = Modifier
                .size(52.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF222222))
                .clickable { onStartClick() }
                .padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "PRESS START",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                letterSpacing = 2.sp
            )
        }
    }
}

@Composable
fun Invaders(
    color: Color,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.space_invaders_svgrepo_com),
        contentDescription = "Invaders",
        colorFilter = ColorFilter.tint(color),
        alignment = Alignment.Center,
        modifier = modifier
    )
}

@Composable
fun SpaceShip(
    color: Color,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(R.drawable.spaceship_invaders),
        contentDescription = "Space Ship",
        colorFilter = ColorFilter.tint(color),
        alignment = Alignment.Center,
        modifier = modifier
    )
}

@Composable
fun GameOverScreen(
    onBackToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val enemyColors = listOf(
                    Color(0xFF16F40E),
                    Color(0xFFF50000),
                    Color(0xFF042BD3),
                    Color(0xFFFFEB3B),
                    Color(0xFF9434C2)
                )

                enemyColors.forEach { color ->
                    Invaders(
                        color = color,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "GAME OVER",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFF50000),
                letterSpacing = 4.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF222222))
                    .clickable { onBackToMenu() }
                    .padding(vertical = 12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "VOLTAR AO MENU",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    letterSpacing = 2.sp
                )
            }
        }
    }
}