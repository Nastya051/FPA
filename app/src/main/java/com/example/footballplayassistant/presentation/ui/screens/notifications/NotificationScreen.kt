package com.example.footballplayassistant.presentation.ui.screens.notifications

import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.ui.screens.search_tab.Event
import com.example.footballplayassistant.presentation.ui.screens.search_tab.EventAddressInfo
import com.example.footballplayassistant.presentation.ui.screens.search_tab.EventCard
import com.example.footballplayassistant.presentation.ui.screens.search_tab.EventDetails
import com.example.footballplayassistant.presentation.ui.screens.search_tab.Player
import com.example.footballplayassistant.presentation.ui.screens.search_tab.ShowMoreButton
import com.example.footballplayassistant.presentation.ui.theme.spacing
import kotlin.math.roundToInt


@Composable
fun NotificationScreen() {
    val event = Event(
        "Арена Новый Футбол поле  Крылатское",
        listOf(
            Player("Игорь", "Султанов", R.drawable.loadexample),
            Player("Егор", "Дружин", R.drawable.player_example),
            Player("Серявгей", "Сергеев", R.drawable.player_example_1),
            Player("Игорь", "Султанов", R.drawable.loadexample),
            Player("Игорь", "Султанов", R.drawable.loadexample),
            Player("Игорь", "Султанов", R.drawable.loadexample),
            Player("Игорь", "Султанов", R.drawable.loadexample),
        ),
        "27.07.2023",
        "10:00",
        500
    )  // Заглушка
    Column(modifier = Modifier.background(color = MaterialTheme.colorScheme.primaryContainer)) {
        HeaderWithBackButton(
            text = stringResource(R.string.notificationsHeader), modifier = Modifier.padding(
                start = MaterialTheme.spacing.medium,
                end = MaterialTheme.spacing.medium,
                top = MaterialTheme.spacing.medium,
                bottom = MaterialTheme.spacing.small
            )
        )
        val hasNotifications = true
        if (hasNotifications) {
            LazyColumn {
                item {
                    Text(
                        stringResource(R.string.newNotifications), style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.padding(MaterialTheme.spacing.medium)
                    )
                }
                items(1) {
                    NotificationCard {
                        EventFinishedNotification(
                            isHost = true,
                            event = event,
                            time = "1 ч 50 мин",
                            isUnread = true
                        )
                    }
                    NotificationCard {
                        PersonSubscribedToYouNotification(
                            image = R.drawable.player_avatar,
                            name = "Сергей Савельев",
                            time = "1 ч 40 мин",
                            isUnread = true
                        )
                    }
                    NotificationCard {
                        MatchNotification(
                            image = R.drawable.player_avatar,
                            name = "Сергей Савельев",
                            time = "1 ч 40 мин",
                            isUnread = true,
                            event = event,
                            hasInvitedYou = true,
                        )
                    }
                    NotificationCard {
                        MatchNotification(
                            image = R.drawable.player_avatar,
                            name = "Сергей Савельев",
                            time = "1 ч 40 мин",
                            isUnread = true,
                            event = event,
                            sentNewMessage = true
                        )
                    }
                    NotificationCard {
                        MatchNotification(
                            image = R.drawable.player_avatar,
                            name = "Сергей Савельев",
                            time = "1 ч 40 мин",
                            isUnread = true,
                            event = event,
                            newChanges = true
                        )
                    }
                    NotificationCard {
                        FieldCreationNotification(
                            isSuccessful = true,
                            isUnread = true,
                            time = "1 ч 40 мин",
                            event = event
                        )
                    }
                    NotificationCard {
                        FieldCreationNotification(
                            isSuccessful = false,
                            isUnread = true,
                            time = "1 ч 40 мин",
                            event = event,
                            reason = "неверный адрес"
                        )
                    }
                    NotificationCard {
                        EventFinishedUnsuccessfullyNotification(
                            event = event,
                            name = "Сергей Савельев",
                            isHost = false,
                            time = "1 ч 40 мин",
                            isUnread = true,
                            image = R.drawable.player_avatar,
                        )
                    }
                    NotificationCard {
                        EventFinishedUnsuccessfullyNotification(
                            event = event,
                            name = "Сергей Савельев",
                            isHost = true,
                            time = "1 ч 40 мин",
                            isUnread = true,
                            image = R.drawable.player_avatar,
                        )
                    }
                    NotificationCard {
                        EventFinishedUnsuccessfullyNotification(
                            event = event,
                            name = "Сергей Савельев",
                            isHost = false,
                            time = "1 ч 40 мин",
                            isUnread = true,
                            image = R.drawable.player_avatar,
                            wasRemoved = true
                        )
                    }

                }
                item {
                    Text(
                        stringResource(R.string.seenNotifications), style = MaterialTheme.typography.displayMedium.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        modifier = Modifier.padding(MaterialTheme.spacing.medium)
                    )
                }
                items(1) {
                    NotificationCard {
                        EventFinishedNotification(
                            isHost = false,
                            event = event,
                            time = "1 ч 50 мин",
                            isUnread = false
                        )
                    }
                    NotificationCard {
                        PersonSubscribedToYouNotification(
                            image = R.drawable.player_avatar,
                            name = "Сергей Савельев",
                            time = "1 ч 40 мин",
                            isUnread = false
                        )
                    }
                }
                item {
                    ShowMoreButton()
                }
            }

        } else {
            NoNotifications()
        }


    }
}

@Composable
fun NoNotifications() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.noNotificationsTitle), style = MaterialTheme.typography.titleMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.W600
            )
        )
        Text(
            stringResource(R.string.noNotificationsDescription),
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

enum class DragAnchors {
    Center,
    End,
}

@Composable
fun MatchNotification(
    image: Int, name: String, time: String, isUnread: Boolean, event: Event,
    hasInvitedYou: Boolean = false, sentNewMessage: Boolean = false, newChanges : Boolean = false
) {
    Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        NotificationAvatar(image = image)
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f, fill = false)) {
                    if (newChanges) {
                        Text(
                            stringResource(R.string.hostTitle),
                            style = MaterialTheme.typography.displaySmall.copy(
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            ),
                            modifier = Modifier.padding(bottom = MaterialTheme.spacing.extraSmall))
                    }
                    Text(
                        name,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                TimeAndIsUnread(time = time, isUnread = isUnread)

            }
            Text(
                if (hasInvitedYou) {
                    stringResource(R.string.hasInvitedYouNotification)
                } else if (sentNewMessage) {
                    stringResource(R.string.sentYouNewMessageNotification)
                } else if (newChanges) {
                    stringResource(R.string.hostChangedGameNotification)
                } else {
                    ""
                }, modifier = Modifier
                    .padding(end = MaterialTheme.spacing.large)
                    .weight(1f, fill = false),
                style = MaterialTheme.typography.displaySmall.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    GoToEventButton(
        if (hasInvitedYou) {
            stringResource(R.string.participateInMatchAction)
        } else if (sentNewMessage) {
            stringResource(R.string.goToMessageAction)
        } else if (newChanges) {
            stringResource(R.string.seeChangesAction)
        } else {
            ""
        }
    )
    EventCard(event = event)

}

@Composable
fun FieldCreationNotification(
    isSuccessful: Boolean,
    reason: String = "",
    isUnread: Boolean,
    time: String,
    event: Event
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        if (isSuccessful) {
            Text(
                stringResource(R.string.fieldCreationSuccessNotification),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.weight(1f, fill = false)
            )
        } else {
            Column(modifier = Modifier.weight(1f, fill = false),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)) {
                Text(
                    stringResource(R.string.fieldWasRejectedNotification),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.W400
                    )
                )
                Text(
                    buildAnnotatedString {
                        withStyle(SpanStyle(fontWeight = FontWeight.W600)) {
                            append(stringResource(R.string.rejectionReason))
                        }
                        withStyle(SpanStyle(fontWeight = FontWeight.W400)) {
                            append(reason)
                        }
                    },
                    style =  MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                    )

                )
            }
        }
        TimeAndIsUnread(time = time, isUnread = isUnread)
    }
    GoToEventButton(
        text =
        if (isSuccessful) {
            stringResource(R.string.goToFieldScreenAction)
        } else {
            stringResource(R.string.createFieldAgainAction)
        }
    )
    ShortInfoCard(event = event, isHost = true, showAddress = true, address = "г. Москва, ул. Ломоносовский проспект, строение 3, корпус 20, строение 3, корпус 20", distance = "2,5км")

}

@Composable
fun EventFinishedUnsuccessfullyNotification(
    event: Event,
    name: String,
    image: Int = 0,
    isHost: Boolean,
    time: String,
    isUnread: Boolean,
    wasRemoved : Boolean = false
) {
    if (isHost) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                stringResource(R.string.playersHaventJoinedMatch),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.weight(1f, fill = false)
            )
            TimeAndIsUnread(time = time, isUnread = isUnread)
        }
    } else if (wasRemoved) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    stringResource(R.string.hostRemovedYouNotification),
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.W400
                    ),
                    modifier = Modifier.weight(1f, fill = false)
                )
                TimeAndIsUnread(time = time, isUnread = isUnread)
            }
            GoToEventButton(text = stringResource(R.string.goToGameScreenAction))
        }
    }
    else {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
            NotificationAvatar(image = image)
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Column(modifier = Modifier.weight(1f, fill = false)) {
                    Text(
                        stringResource(R.string.hostTitle),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontWeight = FontWeight.W400
                        ),
                    )
                    Text(
                        name, modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.W600,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        stringResource(R.string.matchWasDisbandedNotification),
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                            fontWeight = FontWeight.W400
                        ),
                    )

                }
                TimeAndIsUnread(time = time, isUnread = isUnread)
            }
        }
    }
    Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
    ShortInfoCard(event = event, isHost = isHost, isSuccessful = false)
}


@Composable
fun NotificationAvatar(image: Int) {
    Image(
        painterResource(id = image), contentDescription = stringResource(R.string.userAvatarInNotificationDescription),
        modifier = Modifier
            .size(56.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotificationCard(content: @Composable () -> Unit) {
    val density = LocalDensity.current
    val defaultActionSize = 60.dp
    val endActionSizePx = with(density) { defaultActionSize.toPx() }

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Center,
            anchors = DraggableAnchors {
                DragAnchors.Center at 0f
                DragAnchors.End at endActionSizePx
            },
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            animationSpec = tween(),
        )
    }
    Box(
        modifier = Modifier
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.extraSmall
            )
            .fillMaxWidth()
            .height(IntrinsicSize.Max), contentAlignment = Alignment.CenterEnd
    ) {
        IconButton(
            onClick = {}, modifier = Modifier
                .width(44.dp)
                .fillMaxHeight()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.onPrimary)

        ) {
            Icon(
                painterResource(R.drawable.ic_delete), contentDescription = stringResource(R.string.deleteNotification),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        Row(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = -state
                            .requireOffset()
                            .roundToInt(),
                        y = 0,
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal, reverseDirection = true),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(color = MaterialTheme.colorScheme.onPrimary)
                    .padding(MaterialTheme.spacing.medium)
            ) {
                content()
            }
        }
    }
}


@Composable
fun EventFinishedNotification(
    isHost: Boolean,
    event: Event,
    time: String,
    isUnread: Boolean,
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            if (isHost) {
                stringResource(R.string.hostMatchFinishedNotification)
            } else {
                stringResource(R.string.participantMatchFinishedNotification)
            },
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier.weight(1f, fill = false)
        )
        TimeAndIsUnread(time = time, isUnread = isUnread)
    }
    GoToEventButton(
            if (isHost) {
                stringResource(R.string.markParticipantsAction)
            } else {
                stringResource(R.string.evaluateEventAction)
            }
    )


    ShortInfoCard(event, isHost)

}

@Composable
fun GoToEventButton(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight = FontWeight.W600
            ),
            modifier = Modifier.weight(1f, fill = false)
        )
        IconButton(
            onClick = {}, modifier = Modifier
                .size(24.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.ic_arrow_next_24),
                contentDescription = stringResource(R.string.eventButtonIconDescription),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}

@Composable
fun ShortInfoCard(
    event: Event,
    isHost: Boolean,
    showAddress: Boolean = false,
    address: String = "",
    distance: String = "",
    isSuccessful: Boolean = true
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Text(
                event.name, style = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium)
            )
            if (showAddress) {
                EventAddressInfo(address = address, distance = distance)
            } else {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                )
                EventDetails(event = event, isHost = isHost, isSuccessful = isSuccessful)
            }
        }
    }
}

@Composable
fun TimeAndIsUnread(time: String, isUnread: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
    ) {
        if (isUnread) {
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onBackground)
            )
        }
        Text(
            time, style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.W400
            )
        )
    }
}

@Composable
fun PersonSubscribedToYouNotification(image: Int, name: String, time: String, isUnread: Boolean) {
    Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
        NotificationAvatar(image = image)
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    name, modifier = Modifier.weight(1f, fill = false),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                TimeAndIsUnread(time = time, isUnread = isUnread)

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    stringResource(R.string.subscribedToYourUpdatesNotification),
                    modifier = Modifier
                        .weight(1f, fill = false),
                    style = MaterialTheme.typography.displaySmall.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        lineHeight = 16.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.Bottom)
                ) {
                    Icon(
                        painterResource(id = R.drawable.ic_profile_add),
                        contentDescription = stringResource(R.string.addUserToFriendsDescription),
                        tint = MaterialTheme.colorScheme.secondary
                    )

                }

            }
        }

    }

}