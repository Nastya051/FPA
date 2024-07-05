package com.example.footballplayassistant.presentation.ui.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.SelectionButtons
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.CommonActionsMenu
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.rows.SubscriberRow
import com.example.footballplayassistant.presentation.enums.FilterSubscribers
import com.example.footballplayassistant.presentation.enums.getFiltersSubscribers
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun SubscriptionsScreen() {
    val navController = LocalNavController.current!!
    val filtersList = FilterSubscribers.entries.toList()
    val filterSubscribers = remember { mutableIntStateOf(filtersList[0].ordinal) }
    val expanded = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.subscriptions),
            imageButton = R.drawable.ic_three_dots_22,
            onClickBack = { navController.navigate(Route.UserProfileScreen.withArgs("false")) },
            onClickOther = { expanded.value = true },
            actionsMenu = {
                CommonActionsMenu(
                    expand = expanded,
                    icons = listOf(R.drawable.ic_blocked_24),
                    actions = listOf(R.string.blocked),
                    onClicks = listOf { navController.navigate(Route.BlockedUsersScreen.path) }
                )
            },
            modifier = Modifier.padding(top = 12.dp, bottom = 16.dp)
        )

        SelectionButtons(
            valueList = getFiltersSubscribers(),
            selectedItemIndex = filterSubscribers.intValue,
            onSelected = { filterSubscribers.intValue = it },
            modifier = Modifier.padding(bottom = 32.dp)
        )

        //нет подписок
        if(filterSubscribers.intValue==FilterSubscribers.Subscribers.ordinal)
            NoSubscribers()
        else
            NoSubscriptions()

        //есть подписки
//        LazyColumn {
//            item {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(bottom = 16.dp),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Text(
//                        text = stringResource(id =
//                            if (filterSubscribers.intValue == FilterSubscribers.Subscribers.ordinal)
//                                R.string.subscribers
//                            else R.string.subscriptions
//                        ),
//                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
//                        color = MaterialTheme.colorScheme.primary,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis,
//                    )
//
//                    Icon(
//                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_green_rectangle_6),
//                        contentDescription = "Dot",
//                        tint = MaterialTheme.colorScheme.secondary,
//                        modifier = Modifier.padding(horizontal = 9.dp)
//                    )
//                    Text(
//                        text = "5",
//                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
//                        color = MaterialTheme.colorScheme.primary,
//                        maxLines = 1,
//                        overflow = TextOverflow.Ellipsis
//                    )
//                }
//            }
//            items(5) {
//                SubscriberRow(
//                    text = "name",
//                    name = "@name",
//                    foto = R.drawable.user_foto,
//                    onClick = {/*go to user profile*/ })
//            }
//        }
    }
}

@Composable
private fun NoSubscriptions() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.noSubscriptions),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = stringResource(id = R.string.noSubscriptionsDescription),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
            color = MaterialTheme.colorScheme.primary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun NoSubscribers() {
    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.noSubscribers),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = stringResource(id = R.string.noSubscribersDescription),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
            color = MaterialTheme.colorScheme.primary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}