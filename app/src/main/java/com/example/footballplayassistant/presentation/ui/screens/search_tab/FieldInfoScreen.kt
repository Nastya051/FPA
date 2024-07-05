package com.example.footballplayassistant.presentation.ui.screens.search_tab

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.ui.theme.spacing
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FieldInfoScreen() {
    val navController = LocalNavController.current!!
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.fieldInfoScreenHeader),
            imageButton = R.drawable.ic_arrow_share_25,
            modifier = Modifier.padding(MaterialTheme.spacing.horizontal)
        )
        ImageCarousel()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Row(
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    stringResource(R.string.fieldInfoName),
                    style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                    modifier = Modifier
                        .weight(1f, fill = false)
                        .padding(end = MaterialTheme.spacing.medium),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                FavoriteButton()
            }
            RatingInfo()
            FlowRow(modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)) {
                TagField(text = stringResource(R.string.convenientFieldPlacementTag), amount = 20)
                TagField(text = stringResource(R.string.fieldServiceTag), amount = 18)
                TagField(text = stringResource(R.string.fieldConditionTag), amount = 10)
            }
            BasicInfo {
                navController.navigate(Route.AdditionalFieldInfoScreen.path)
            }


        }
        EventsCarousel {
            navController.navigate(Route.ComingEventsScreen.path)
        }

        FeedbackSection()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.large, bottom = MaterialTheme.spacing.small,
                    start = MaterialTheme.spacing.medium, end = MaterialTheme.spacing.medium
                )
        ) {
            CommonButton(text = stringResource(R.string.chooseFieldButtonText))
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RatingInfo() {
    FlowRow(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.medium)
            .fillMaxWidth()
    ) {
        Icon(
            painterResource(id = R.drawable.ic_star),
            contentDescription = stringResource(R.string.fieldRatingIconDescription),
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .size(24.dp)
                .padding(end = MaterialTheme.spacing.small)
        )
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onPrimaryContainer)) {
                    append(stringResource(R.string.fieldAverageRating))
                }
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSecondaryContainer)) {
                    append(" ")
                    append(stringResource(R.string.feedbackAmount))
                }
            },
            style = MaterialTheme.typography.titleMedium
        )
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageCarousel() {
    val imagePagerState = rememberPagerState(pageCount = { 10 })
    Box(
        modifier = Modifier
            .height(190.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomEnd
    ) {
        HorizontalPager(
            state = imagePagerState, modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
            pageSpacing = MaterialTheme.spacing.small
        ) { _ ->

            Image(
                painter = painterResource(R.drawable.field_example_photo),
                contentDescription = stringResource(id = R.string.fieldPhotoImageDescription),
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            //NoImage()  //  Если нет фотографий
        }
        ImageCounter(
            currentCount = imagePagerState.currentPage + 1,
            maxCount = imagePagerState.pageCount
        )
    }
}
@Composable
fun FeedbackSection() {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(32.dp))
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer
            )
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            stringResource(R.string.feedbackSectionTitle), style =
            MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
        //NoFeedback() // Если нет отзывов
        FeedbackList(feedbackCount = 4)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventsCarousel(onClick : () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = MaterialTheme.spacing.large,
                    bottom = MaterialTheme.spacing.medium,
                    start = MaterialTheme.spacing.horizontal,
                    end = MaterialTheme.spacing.horizontal
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                stringResource(R.string.comingEventsSectionTitle), style =
                MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.W600,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.weight(1f, fill = false)
            )
            Text(
                stringResource(R.string.showAllUpcomingEventsClickableText),
                style = MaterialTheme.typography.bodyMedium
                    .copy(color = MaterialTheme.colorScheme.onSecondaryContainer),
                modifier = Modifier.clickable {
                    onClick.invoke()
                }
            )
        }
        val pagerState = rememberPagerState(pageCount = { 3 })
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
        val eventList = listOf(event, event, event)
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.medium),
            modifier = Modifier
                .fillMaxWidth(),
            pageSpacing = MaterialTheme.spacing.small
        ) { page ->
            EventCard(eventList[page])
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(
                    top = MaterialTheme.spacing.medium,
                    end = MaterialTheme.spacing.extraSmall,
                    bottom = MaterialTheme.spacing.medium
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.tertiaryContainer
                Box(
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.extraSmall)
                        .size(8.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(color)
                )
            }
        }

    }
}

@Composable
fun BasicInfo(onClick : () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = MaterialTheme.spacing.small)
            .height(IntrinsicSize.Min)
    ) {
        InfoCard(
            icon = R.drawable.ic_location, text = stringResource(R.string.basicInfoFieldLocation),
            Modifier
                .fillMaxWidth(0.5f)
                .padding(end = MaterialTheme.spacing.small)
        )
        InfoCard(
            icon = R.drawable.ic_call, text = stringResource(R.string.basicInfoFieldPhoneNumber),
            Modifier
                .fillMaxWidth()
        )

    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(IntrinsicSize.Min)) {
        InfoCard(
            icon = R.drawable.ic_time_black_24, text = stringResource(R.string.basicInfoFieldWorkHours),
            Modifier
                .fillMaxWidth(0.5f)
                .padding(end = MaterialTheme.spacing.small)
        )
        AdditionalInfo(
            Modifier
                .fillMaxWidth(), onClick = onClick)
    }
}

@Composable
fun FeedbackList(feedbackCount : Int) {
    repeat(if (feedbackCount > 3) { 3 } else { feedbackCount }) {
        FeedbackCard(
            "Дмитрий Дмитриев", R.drawable.player_avatar, 4, "14 декабря 2022",
            "13 января в 15-30 приехал с друзьями, все норм, но особенно понравилось отношение администратора, приятный вежливый парень, спасибо большое. 13 января в 15-30 приехал с друзьями, все норм"
        ) // Инфа заглушка
    }
    if (feedbackCount > 3) {
        ShowMoreButton()
    }
}

@Composable
fun ShowMoreButton() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Button(
            onClick = {},
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.outlineVariant
            )
        ) {
            Text(
                stringResource(R.string.showMoreFeedbackButton),
                style = MaterialTheme.typography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.W600,

                    ), modifier = Modifier.padding(end = MaterialTheme.spacing.extraSmall)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_arrows_down_24),
                contentDescription = stringResource(R.string.expandIconDescription), tint = MaterialTheme.colorScheme.secondary
            )
        }
    }
}
@Composable
fun ImageCounter(currentCount: Int, maxCount : Int) {
    Row(
        modifier = Modifier
            .padding(
                vertical = MaterialTheme.spacing.medium,
                horizontal = MaterialTheme.spacing.large
            )
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color.Black.copy(alpha = 0.6f))
    ) {
        Text(
            "$currentCount\\$maxCount", style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onPrimary, lineHeight = 20.sp
            ),
            modifier = Modifier.padding(
                vertical = MaterialTheme.spacing.small,
                horizontal = MaterialTheme.spacing.small
            )
        )
    }
}
@Composable
fun NoImage() {
    Row(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .height(190.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.primaryContainer)
        .padding(horizontal = MaterialTheme.spacing.horizontal),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Icon(painterResource(id = R.drawable.ic_no_image), contentDescription = stringResource(R.string.noImageIconDescription),
            tint = MaterialTheme.colorScheme.tertiaryContainer)

    }
}

@Composable
fun NoFeedback() {
    Row(modifier = Modifier
        .height(263.dp)
        .fillMaxWidth(), horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        Text(stringResource(R.string.fieldHasNoFeedback),
            style = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp
            ))
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FeedbackCard(name : String, photo : Int, rating : Int, date : String, feedback: String) {
    Card(shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimary),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.small),
        ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Row(modifier = Modifier
                .padding(bottom = MaterialTheme.spacing.medium)
                .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                ) {
                Image(painterResource(photo), contentDescription = stringResource(R.string.feedbackAvatarImageDescription), contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(12.dp))
                    )
                Column(modifier = Modifier
                    .heightIn(min = 44.dp)
                    .fillMaxHeight()) {
                    Text(name, style = MaterialTheme.typography.displayMedium.copy(color =
                    MaterialTheme.colorScheme.onPrimaryContainer))
                    Spacer(modifier = Modifier.weight(1f))
                    FlowRow(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)) {
                        RatingStars(rating, Modifier.align(Alignment.CenterVertically))
                        Text(date, style = MaterialTheme.typography.displaySmall.copy(color =
                        MaterialTheme.colorScheme.onSecondaryContainer))
                    }
                }

            }
            Text(feedback, style = MaterialTheme.typography.labelMedium.copy(fontWeight =
            FontWeight.W400, color = MaterialTheme.colorScheme.onPrimaryContainer,
                lineHeight = 20.sp))
        }

    }
}

@Composable
fun RatingStars(rating : Int, @SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    Row(horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier) {
        repeat(rating) {
            Icon(painter = painterResource(id = R.drawable.ic_star), contentDescription = stringResource(R.string.filledRatingStarIconDescription),
                tint = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.size(16.dp))
        }
        repeat (5 - rating) {
            Icon(painter = painterResource(id = R.drawable.ic_star), contentDescription = stringResource(R.string.blankRatingStarIconDescription),
                tint = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.size(16.dp))
        }
    }
}

data class Player(
    val name : String,
    val surname : String,
    val photo : Int
)

data class Event(
    val name: String,
    val participants: List<Player>,
    val date: String,
    val time: String,
    val price: Int
)


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun EventCard(event : Event) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.outlineVariant
        ),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            Text(
                event.name, style = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.small)
            )
            Row(modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium)) {
                PhotoGrid(event)

                FlowRow(modifier = Modifier
                    .padding(horizontal = MaterialTheme.spacing.small)
                    .weight(1f)) {
                    ListNames(event)
                }
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(26.dp))
                        .background(MaterialTheme.colorScheme.secondary)
                        .width(62.dp)
                ) {
                    Text(
                        "8/10", // Заглушка
                        style = MaterialTheme.typography.labelSmall.copy(MaterialTheme.colorScheme.onPrimaryContainer),
                        modifier = Modifier
                            .padding(
                                horizontal = MaterialTheme.spacing.medium,
                                vertical = MaterialTheme.spacing.extraSmall
                            )
                    )
                }
            }
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            EventDetails(event)

        }
    }
}



@Composable
fun EventDetails(event : Event, isHost : Boolean = false, isSuccessful : Boolean = true) {
    Row(
        modifier = Modifier
            .padding(top = MaterialTheme.spacing.medium)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f, fill = false),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painterResource(id = R.drawable.ic_calendar_gray_24),
                contentDescription = stringResource(R.string.eventCardCalendarIconDescription),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                event.date, style = MaterialTheme.typography.displaySmall.
                copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.extraSmall,
                    end = MaterialTheme.spacing.large
                )
            )
            Icon(
                painterResource(id = R.drawable.ic_time_24), contentDescription =
                stringResource(R.string.eventCardTimeIconDescription),
                modifier = Modifier.padding(end = MaterialTheme.spacing.extraSmall),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Text(
                event.time, style = MaterialTheme.typography.displaySmall
                    .copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
            )
        }
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.W700)) {
                    if (isHost) {
                        append("Вывод: ${
                            if (isSuccessful) {
                                event.price * event.participants.size
                            } else {
                                0
                        }}")
                    } else {
                        append(event.price.toString())
                    }
                }
                if (!isHost) {
                    append(stringResource(R.string.pricePerPerson))
                }
            },
            style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
        )

    }
}
@Composable
fun PhotoGrid(event : Event, addBorder : Boolean = false) {
    LazyVerticalGrid(
        modifier = Modifier.size(62.dp),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(MaterialTheme.spacing.extraSmall),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(if (event.participants.size > 4) 4 else event.participants.size) {
            index ->
            if (event.participants.size > 4 && index == 3 && !addBorder) {
                Row(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .size(26.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "+${event.participants.size - 3}",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.W400,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    )
                }
            } else {
                Image(
                    painterResource(id = event.participants[index].photo),
                    contentDescription = stringResource(R.string.eventParticipantAvatarImageDescription),
                    modifier = Modifier
                        .size(26.dp)
                        .clip(RoundedCornerShape(8.dp))
                            .then(if
                                    (addBorder) {
                                Modifier.border(width = 1.dp,
                                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                                    shape = RoundedCornerShape(8.dp))
                            }
                            else {
                                Modifier
                            }
                            ),
                    contentScale = ContentScale.Crop

                )
            }
        }
    }
}
@Composable
fun ListNames(event : Event) {
    for (i in 0..2) {
        Text(
            "${event.participants[i].name} ",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.onPrimaryContainer,

            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            "${event.participants[i].surname}, ",
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
    if (event.participants.size == 5) {
        Text(
            stringResource(R.string.anotherOneParticipantListed),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    } else if (event.participants.size > 4) {
        Text(
            "и ${event.participants.size - 3} других",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    } else
        Text(
            event.participants[3].name,
            style = MaterialTheme.typography.bodySmall.copy(
                fontWeight = FontWeight.W600,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
}

@Composable
fun AdditionalInfo(@SuppressLint("ModifierParameter") modifier: Modifier = Modifier, onClick : () -> Unit) {
    Button(onClick = onClick, modifier = modifier.fillMaxHeight(),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(MaterialTheme.spacing.small),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.outlineVariant
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSecondaryContainer)
    ) {
            Text(stringResource(R.string.additionalInfoButtonText),
                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onPrimaryContainer),
                modifier = Modifier.weight(1f, fill = false),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis)
            Icon(
                painterResource(id = R.drawable.ic_white_arrows_18), contentDescription = stringResource(R.string.additionalInfoIconDescription),
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.onPrimaryContainer)
                    .padding(MaterialTheme.spacing.small)

            )

    }

}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagField(text : String, amount : Int) {
    FlowRow(modifier = Modifier
        .padding(bottom = MaterialTheme.spacing.small, end = MaterialTheme.spacing.small)
        .border(1.dp, MaterialTheme.colorScheme.onSecondaryContainer, RoundedCornerShape(68.dp))
        .padding(MaterialTheme.spacing.small)) {
        Text(text, style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
        Icon(painterResource(id = R.drawable.ic_indicator_6), contentDescription = stringResource(R.string.tagDotIconDescription),
            tint = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(horizontal = MaterialTheme.spacing.small)
                .align(Alignment.CenterVertically))
        Text(amount.toString(), style = MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
    }
}

@Composable
fun InfoCard(icon : Int, text: String,
             @SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.heightIn(min = 80.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.small)) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = stringResource(R.string.infoCardIconDescription),
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(modifier = Modifier
                .size(MaterialTheme.spacing.small)
                .weight(1f))
            Text(
                text, style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.W600
                ), maxLines = 2, overflow = TextOverflow.Ellipsis
            )
        }
    }
}