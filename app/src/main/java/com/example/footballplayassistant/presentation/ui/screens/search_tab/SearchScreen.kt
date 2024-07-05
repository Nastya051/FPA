package com.example.footballplayassistant.presentation.ui.screens.search_tab

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.theme.spacing


@Composable
@Preview
fun SearchScreen() {
    val navController = LocalNavController.current!!
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(MaterialTheme.spacing.medium)) {
        HeaderWithBackButton(text = stringResource(id = R.string.search),
            onClickBack = { TODO() })
        SearchBar (enabled = false) {
            navController.navigate(Route.FilterScreen.path)
        }
//        LazyColumn (userScrollEnabled = true){
//            item {
//                SearchCard("Арена Новый Футбол | Крылатское",
//                    "г. Москва, ул. Ломоносовский проспект, строение 3, корпус 20, строение 3, корпус 20", "4,2км")
//                SearchCard("Футбольный манеж Академия будущего на Кузнецова",
//                    "г. Москва, ул. Ломоносовский проспект, строение 3, корпус 20, строение 3, корпус 20", "4,2км")
//                SearchCard("Арена Новый Футбол | Крылатское",
//                    "г. Москва, ул. Ломоносовский проспект, строение 3, корпус 20, строение 3, корпус 20", "4,2км")
//            }
//        }
        // Если ничего не найдено по запросу
//        NoResultsScreen(stringResource(R.string.areaNotFound), stringResource(R.string.tryChangeFilters),
//            stringResource(R.string.addField)) {
//            navController.navigate(Route.CreateFieldScreen.path)
//        }
        // Если отключена геолокация
        NoResultsScreen(title = stringResource(R.string.geolocationIsTurnedOff), 
            description = stringResource(id = R.string.geolocationIsTurnedOffDescription), 
            buttonText = stringResource(id = R.string.turnOnGeolocation)) {

        }

    }
}

@Composable
fun SearchCard(title : String, address : String, distance : String) {
    Card(modifier = Modifier
        .padding(vertical = MaterialTheme.spacing.extraSmall)
        .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        Column(modifier = Modifier.padding(horizontal = MaterialTheme.spacing.medium,
            vertical = MaterialTheme.spacing.medium)) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = MaterialTheme.spacing.medium), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = title, style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.W600,
                    maxLines = 2, overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = false))
                Spacer(modifier = Modifier
                    .size(MaterialTheme.spacing.medium))
                FavoriteButton()
            }
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
            EventAddressInfo(address = address, distance = distance)

        }

    }
}

@Composable
fun EventAddressInfo(address: String, distance: String) {
    val index = (address.indexOfFirst { it == ',' })
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.W700)) {
                    append(address.slice(0 until index))
                }
                append(address.slice(index until address.length))
            }, color = MaterialTheme.colorScheme.onSecondaryContainer, style = MaterialTheme.typography.displaySmall.copy(fontSize = 12.sp,
                lineHeight = 12.sp), maxLines = 2, overflow = TextOverflow.Ellipsis, lineHeight = 16.sp,
            modifier = Modifier.weight(1f, fill = false)
        )
        Spacer(modifier = Modifier
            .size(MaterialTheme.spacing.large))
        Text(
            text = distance, color = MaterialTheme.colorScheme.onSecondaryContainer, style = MaterialTheme.typography.displaySmall.copy(fontSize = 12.sp,
                lineHeight = 12.sp)
        )
    }
}

@Composable
fun FavoriteButton() {
    var isFavorite by remember {
        mutableStateOf(false)
    }
    IconToggleButton(modifier = Modifier.size(24.dp), checked = isFavorite, onCheckedChange = { isFavorite = !isFavorite }) {
        Icon(painter = if (isFavorite) {
            painterResource(id = R.drawable.ic_filled_heart)
        } else {
            painterResource(id = R.drawable.ic_heart)
        },
            contentDescription = stringResource(R.string.addToFavorites),
        tint = MaterialTheme.colorScheme.onPrimaryContainer)
    }
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(enabled : Boolean, onClick : () -> Unit) {
    var value by remember { mutableStateOf(TextFieldValue(""))}
    val interactionSource = remember { MutableInteractionSource() }

    val animatedIconColor: Color by animateColorAsState(
        targetValue = if (enabled) MaterialTheme.colorScheme.onSecondaryContainer
        else MaterialTheme.colorScheme.tertiaryContainer,
        animationSpec = tween(500, 0, LinearEasing)
    )



    Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .padding(bottom = MaterialTheme.spacing.medium, top = MaterialTheme.spacing.small)) {
    BasicTextField(
        value = value,
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f, fill = false)
            .fillMaxHeight(),
        onValueChange = { value = it },
        textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
        interactionSource = interactionSource,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        enabled = enabled
    ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value.toString(),
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                interactionSource = interactionSource,
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    if (value.text.isEmpty()) {
                        Icon(
                            painterResource(id = R.drawable.ic_search_black_25),
                            tint = animatedIconColor,
                            contentDescription = stringResource(id = R.string.searchIconDescription),
                            modifier = Modifier
                                .size(24.dp)
                                .offset((-5).dp)
                        )
                    } else {
                        IconButton(onClick = { value = TextFieldValue("") },
                            modifier = Modifier
                                .size(24.dp)
                                .offset((-5).dp)) {
                            Icon(painterResource(id = R.drawable.ic_close),
                                contentDescription = stringResource(R.string.cleanSearchBar),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer)
                        }
                    }
                },
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = true,
                        isError = false,
                        interactionSource = interactionSource,
                        shape = RoundedCornerShape(60.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                            focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            cursorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            focusedBorderColor = MaterialTheme.colorScheme.onSecondaryContainer,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                        ),
                        focusedBorderThickness = 1.dp,
                        unfocusedBorderThickness = 1.dp
                    )
                },
            )
            if (value.text.isEmpty()) {
                Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
                    PlaceholderText(text = stringResource(R.string.typeInSearchQuery), color = animatedIconColor)
                }
            }
    }
        Spacer(modifier = Modifier
            .size(6.dp))
        OutlinedIconButton(onClick = onClick, modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f),
            border = BorderStroke(1.dp,
                animatedIconColor
            ),
            enabled = enabled
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_icons_24),
                contentDescription = stringResource(id = R.string.filterIconDescription),
                tint = animatedIconColor,
                modifier = Modifier.padding(MaterialTheme.spacing.small))
        }
    }
}

@Composable
fun PlaceholderText(text : String, needToResize : Boolean = false, color : Color) {
    var multiplier by remember { mutableFloatStateOf(1f) }
    Text(
        maxLines = 1,
        text = text,
        modifier = Modifier.padding(
            start = MaterialTheme.spacing.medium,
            top = 12.dp, bottom = 12.dp, end = MaterialTheme.spacing.extraLarge
        ),
        style = MaterialTheme.typography.labelLarge.copy(
            fontWeight = FontWeight.W400,
            color = color,
            fontSize = 16.sp * multiplier
        ),
        overflow = if (needToResize) { TextOverflow.Visible } else { TextOverflow.Ellipsis },
        onTextLayout = {
            if (it.hasVisualOverflow && needToResize) {
                multiplier *= 0.8f
            }
        }
    )
}