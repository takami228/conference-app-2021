package io.github.droidkaigi.confnews2021

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import io.github.droidkaigi.confnews2021.staff.theme.Conferenceapp2021newsTheme

@Composable
fun DroidKaigiApp(firstSplashScreenState: SplashState = SplashState.Shown) {
    Conferenceapp2021newsTheme {
        var splashShown by remember {
            mutableStateOf(firstSplashScreenState)
        }
        val transition = updateTransition(splashShown)
        val splashAlpha: Float by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 100) }
        ) { state ->
            if (state == SplashState.Shown) 1f else 0f
        }
        val contentAlpha: Float by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 300) }
        ) { state ->
            if (state == SplashState.Shown) 0f else 1f
        }

        Box {
            LandingScreen(
                modifier = Modifier.alpha(splashAlpha),
            ) {
                splashShown = SplashState.Completed
            }
        }
        AppContent(
            modifier = Modifier
                .alpha(contentAlpha)
        )
    }
}

enum class SplashState { Shown, Completed }