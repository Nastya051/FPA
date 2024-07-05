package com.example.footballplayassistant.presentation.customviews.dialogwindows

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogScreen(
    image: Int = 0,
    header: String,
    description: String,
    greenButton: String = "",
    whiteButton: String = "",
    bottomButton: String = "",
    arrowsIcon: Boolean = true,
    onClickGreen: () -> Unit = {},
    onClickWhite: () -> Unit = {},
    onClickBottom: () -> Unit = {},
    onDismissRequest: () -> Unit,
    showDialog: Boolean = false//мгновенно отображает/скрывает диалог
) {
    //нужно, чтобы анимация выполнилась перед закрытием диалога
    var showAnimatedDialog by remember { mutableStateOf(false) }

    LaunchedEffect(showDialog) {
        if (showDialog) showAnimatedDialog = true
    }

    if (showAnimatedDialog)
        BasicAlertDialog(
            onDismissRequest = onDismissRequest,
            modifier = Modifier.size(370.dp, 500.dp),
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            //нужно, чтобы анимация началась после того, как отобразилось диалоговое окно
            var animateIn by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) { animateIn = true }
            AnimatedVisibility(
                visible = animateIn && showDialog,
                enter = fadeIn(spring(stiffness = Spring.StiffnessHigh)) + scaleIn(
                    initialScale = 0.8f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessMediumLow
                    )
                ),
                exit = slideOutVertically { it / 8 } + fadeOut() + scaleOut(targetScale = .95f)
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(375.dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = MaterialTheme.colorScheme.primary)
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_ball_dialog_161_158),
                            contentDescription = "Ball left",
                            modifier = Modifier
                                .align(Alignment.TopStart)
                        )

                        LazyColumn(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            item {
                                if (image != 0)
                                    Image(
                                        imageVector = ImageVector.vectorResource(image),
                                        contentDescription = "Status"
                                    )
                            }

                            item {
                                Text(
                                    text = header,
                                    style = MaterialTheme.typography.displayLarge
                                        .copy(fontWeight = FontWeight.W600),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.padding(vertical = 12.dp)
                                )
                            }
                            item {
                                Text(
                                    text = description,
                                    style = MaterialTheme.typography.labelLarge
                                        .copy(fontWeight = FontWeight.W400),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 20.dp)
                                )
                            }
                            item {
                                if (greenButton.isNotEmpty())
                                    CommonButton(
                                        text = greenButton,
                                        style = MaterialTheme.typography.bodyLarge,
                                        onClick = onClickGreen,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                            }
                            item {
                                if (whiteButton.isNotEmpty())
                                    CommonButton(
                                        text = whiteButton,
                                        onClick = onClickWhite,
                                        containerColor = MaterialTheme.colorScheme.onPrimary,
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(bottom = 12.dp)
                                    )
                            }
                            item {
                                if (bottomButton.isNotEmpty())
                                    TextButton(onClick = onClickBottom) {
                                        Text(
                                            text = bottomButton,
                                            style = MaterialTheme.typography.bodyMedium
                                                .copy(fontWeight = FontWeight.W600),
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.padding(end = 4.dp)
                                        )
                                        if (arrowsIcon)
                                            Image(
                                                imageVector = ImageVector.vectorResource(R.drawable.ic_arrows_18_14),
                                                contentDescription = "Arrows"
                                            )
                                    }
                            }
                        }

                        Image(
                            imageVector = ImageVector.vectorResource(R.drawable.ic_ball_dialog_158_196),
                            contentDescription = "Ball right",
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                        )
                    }
                }
                DisposableEffect(Unit) {
                    onDispose {
                        showAnimatedDialog = false//завершение анимации перед закрытием диалога
                    }
                }
            }
        }
}