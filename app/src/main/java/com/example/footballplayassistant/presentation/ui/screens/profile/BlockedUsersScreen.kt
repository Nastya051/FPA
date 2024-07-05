package com.example.footballplayassistant.presentation.ui.screens.profile

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.checkboxes.CheckBoxFriend
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.CommonActionsMenu
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route

@Composable
fun BlockedUsersScreen() {
    val navController = LocalNavController.current!!
    val expanded = remember { mutableStateOf(false) }
    val unlock = remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        HeaderWithBackButton(
            text = stringResource(id = R.string.blockedUsers),
            imageButton = R.drawable.ic_three_dots_22,
            onClickBack = {
                if (unlock.value) unlock.value = false
                else navController.navigate(Route.SubscriptionsScreen.path)
            },
            onClickOther = { if (!unlock.value) expanded.value = true },
            actionsMenu = {
                CommonActionsMenu(
                    expand = expanded,
                    icons = listOf(R.drawable.ic_user_check_24),
                    actions = listOf(R.string.unlock),
                    onClicks = listOf { unlock.value = true }
                )
            },
            modifier = Modifier.padding(top = 12.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        )

        //нет заблокированных пользователей
        NoBlockedUsers()

        //есть заблокированные
//        if (unlock.value)
//            UnlockUsers(usersList = listOf(1,1,1,1,1), navController = navController)
//        else
//            LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
//                items(5) {
//                    SubscriberRow(
//                        text = "name",
//                        name = "@name",
//                        foto = R.drawable.user_foto,
//                        onClick = {/*go to user profile*/ })
//                }
//            }
    }
}

@Composable
private fun UnlockUsers(navController: NavHostController, usersList: List<Int>) {
    val buttonEnable = remember { mutableStateOf(false) }
    val showDialog = remember { mutableStateOf(false) }
    val statesList = MutableList(usersList.size) { false }

    DialogScreen(
        header = stringResource(id = R.string.doneWithPoint),
        description = stringResource(id = R.string.successUnlock),
        greenButton = stringResource(id = R.string.goToSubscriptions),
        whiteButton = stringResource(id = R.string.goToProfile),
        image = R.drawable.ic_check_92,
        onClickGreen = { navController.navigate(Route.SubscriptionsScreen.path) },
        onClickWhite = { navController.navigate(Route.UserProfileScreen.withArgs("false")) },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyColumn {
            items(usersList.size) {
                CheckBoxFriend(
                    text = "name",
                    name = "@name",
                    foto = R.drawable.user_foto,
                    onClick = { state ->
                        statesList[it] = state
                        buttonEnable.value = statesList.contains(true)
                    }
                )
            }
        }

        val animatedContainerColor: Color by animateColorAsState(
            targetValue = if (buttonEnable.value) MaterialTheme.colorScheme.secondary
            else MaterialTheme.colorScheme.tertiary,
            animationSpec = tween(500, 0, LinearEasing)
        )
        val animatedContentColor: Color by animateColorAsState(
            targetValue = if (buttonEnable.value) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.onSecondaryContainer,
            animationSpec = tween(500, 0, LinearEasing)
        )

        CommonButton(
            text = stringResource(id = R.string.unlock),
            containerColor = animatedContainerColor,
            contentColor = animatedContentColor,
            enable = buttonEnable.value,
            onClick = { showDialog.value = true },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
        )
    }

}

@Composable
private fun NoBlockedUsers() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.noBlockedUsers),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.primary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = stringResource(id = R.string.noBlockedUsersDescription),
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.W400),
            color = MaterialTheme.colorScheme.primary,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Center
        )
    }
}