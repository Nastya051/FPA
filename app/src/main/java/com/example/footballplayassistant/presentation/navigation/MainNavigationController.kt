package com.example.footballplayassistant.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.domain.models.auth.UserRegistrationStepOne
import com.example.footballplayassistant.presentation.constants.PhoneEmail
import com.example.footballplayassistant.presentation.constants.RulesPolitic
import com.example.footballplayassistant.presentation.enums.CameraGallery
import com.example.footballplayassistant.presentation.enums.FilterCurrentArchive
import com.example.footballplayassistant.presentation.ui.screens.authentication.EnterInfoScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.ForgotPasswordScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.RecoveryPasswordScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.RulesAndPoliticScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.SignInScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.SignUpCodeScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.SignUpEnterPhoneScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.SignUpStepOneScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.SignUpStepTwoScreen
import com.example.footballplayassistant.presentation.ui.screens.authentication.StartScreen
import com.example.footballplayassistant.presentation.ui.screens.calendar_tab.CalendarScreen
import com.example.footballplayassistant.presentation.ui.screens.main.ChooseTeamScreen
import com.example.footballplayassistant.presentation.ui.screens.main.CreateEventScreen
import com.example.footballplayassistant.presentation.ui.screens.main.EditGameScreen
import com.example.footballplayassistant.presentation.ui.screens.main.InviteFriendsScreen
import com.example.footballplayassistant.presentation.ui.screens.main.LeaveMessageScreen
import com.example.footballplayassistant.presentation.ui.screens.main.MainScreen
import com.example.footballplayassistant.presentation.ui.screens.main.ManagingParticipantsScreen
import com.example.footballplayassistant.presentation.ui.screens.main.MatchInfoScreen
import com.example.footballplayassistant.presentation.ui.screens.main.MatchParticipantsScreen
import com.example.footballplayassistant.presentation.ui.screens.main.MatchScreen
import com.example.footballplayassistant.presentation.ui.screens.main.MyGamesScreen
import com.example.footballplayassistant.presentation.ui.screens.main.NewsScreen
import com.example.footballplayassistant.presentation.ui.screens.main.PaymentScreen
import com.example.footballplayassistant.presentation.ui.screens.main.WalletScreen
import com.example.footballplayassistant.presentation.ui.screens.notifications.BestPlayerScreen
import com.example.footballplayassistant.presentation.ui.screens.notifications.EvaluateEventScreen
import com.example.footballplayassistant.presentation.ui.screens.notifications.MarkParticipantsScreen
import com.example.footballplayassistant.presentation.ui.screens.notifications.NotificationScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.AboutAppScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.BlockedUsersScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.ChangeProfileScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.FAQScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.FeedbackScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.PlayerProfileScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.SafetyScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.SubscriptionsScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.UserProfileScreen
import com.example.footballplayassistant.presentation.ui.screens.profile.ViewingPhotoScreen
import com.example.footballplayassistant.presentation.ui.screens.search_tab.AdditionalFieldInfoScreen
import com.example.footballplayassistant.presentation.ui.screens.search_tab.ComingEventsScreen
import com.example.footballplayassistant.presentation.ui.screens.search_tab.CreateFieldScreen
import com.example.footballplayassistant.presentation.ui.screens.search_tab.FieldInfoScreen
import com.example.footballplayassistant.presentation.ui.screens.search_tab.FilterScreen
import com.example.footballplayassistant.presentation.ui.screens.search_tab.SearchScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigationController(
    navController: NavHostController,
) {
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = Route.MainScreen.path,
            enterTransition = { fadeIn(animationSpec = tween(350)) },
            exitTransition = { fadeOut(animationSpec = tween(350)) },
        ) {
            composable(route = Route.StartScreen.path) {
                StartScreen()
            }

            composable(route = Route.SignInScreen.path) {
                SignInScreen()
            }

            composable(route = Route.SignUpEnterPhoneScreen.path) {
                SignUpEnterPhoneScreen()
            }

            composable(
                route = Route.SignUpCodeScreen.path + "/{type}",
                arguments = listOf(navArgument("type") {
                    type = NavType.StringType
                    defaultValue = PhoneEmail.PHONE
                    nullable = false
                })
            ) { entry ->
                entry.arguments?.getString("type").let { type ->
                    if (type != null) {
                        SignUpCodeScreen(sendCode = type)
                    }
                }

            }

            composable(route = Route.SignUpStepOneScreen.path) {
                SignUpStepOneScreen()
            }

            composable(
                route = Route.SignUpStepTwoScreen.path + "/{nickname}" + "/{name}" + "/{surname}" + "/{email}",
                arguments = listOf(
                    navArgument("nickname") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    },
                    navArgument("name") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    },
                    navArgument("surname") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    },
                    navArgument("email") {
                        type = NavType.StringType
                        defaultValue = ""
                        nullable = false
                    }
                )
            ) { entry ->
                val nickname = entry.arguments?.getString("nickname")
                val name = entry.arguments?.getString("name")
                val surname = entry.arguments?.getString("surname")
                val email = entry.arguments?.getString("email")
                if (nickname != null && name != null && surname != null && email != null) {
                    val user = UserRegistrationStepOne(
                        nickname = nickname,
                        name = name,
                        surname = surname,
                        email = email
                    )
                    SignUpStepTwoScreen(userStepOne = user)
                }
            }

            composable(route = Route.EnterInfoScreen.path) {
                EnterInfoScreen()
            }

            composable(route = Route.MainScreen.path) {
                MainScreen()
            }

            composable(route = Route.CreateEventScreen.path) {
                CreateEventScreen()
            }

            composable(route = Route.NewsScreen.path) {
                NewsScreen()
            }

            composable(route = Route.MatchScreen.path) {
                MatchScreen()
            }

            composable(route = Route.MatchInfoScreen.path) {
                MatchInfoScreen()
            }

            composable(route = Route.ChooseTeamScreen.path) {
                ChooseTeamScreen()
            }

            composable(route = Route.PaymentScreen.path) {
                PaymentScreen()
            }

            composable(route = Route.InviteFriendsScreen.path) {
                InviteFriendsScreen()
            }

            composable(route = Route.SearchScreen.path) {
                SearchScreen()
            }

            composable(route = Route.FilterScreen.path) {
                FilterScreen()
            }

            composable(route = Route.CreateFieldScreen.path) {
                CreateFieldScreen()
            }

            composable(route = Route.FieldInfoScreen.path) {
                FieldInfoScreen()
            }

            composable(route = Route.AdditionalFieldInfoScreen.path) {
                AdditionalFieldInfoScreen()
            }

            composable(route = Route.ComingEventsScreen.path) {
                ComingEventsScreen()
            }

            composable(route = Route.MatchParticipantsScreen.path) {
                MatchParticipantsScreen()
            }

            composable(route = Route.ManagingParticipantsScreen.path) {
                ManagingParticipantsScreen()
            }

            composable(
                route = Route.RulesAndPoliticScreen.path + "/{type}",
                arguments = listOf(navArgument("type") {
                    type = NavType.StringType
                    defaultValue = RulesPolitic.RULES
                    nullable = false
                })
            ) { entry ->
                entry.arguments?.getString("type").let { type ->
                    if (type != null) {
                        RulesAndPoliticScreen(header = type)
                    }
                }
            }

            composable(route = Route.ForgotPasswordScreen.path) {
                ForgotPasswordScreen()
            }

            composable(route = Route.RecoveryPasswordScreen.path) {
                RecoveryPasswordScreen()
            }

            composable(route = Route.EditGameScreen.path) {
                EditGameScreen()
            }

            composable(
                route = Route.MyGamesScreen.path + "/{filter}",
                arguments = listOf(navArgument("filter") {
                    type = NavType.IntType
                    defaultValue = FilterCurrentArchive.Current.ordinal
                    nullable = false
                })
            ) { entry ->
                entry.arguments?.getInt("filter").let { filter ->
                    if (filter != null) {
                        MyGamesScreen(filter = filter)
                    }
                }
            }

            composable(route = Route.WalletScreen.path) {
                WalletScreen()
            }

            composable(route = Route.SafetyScreen.path) {
                SafetyScreen()
            }

            composable(route = Route.UserProfileScreen.path + "/{button}",
                arguments = listOf(navArgument("button") {
                    type = NavType.BoolType
                    defaultValue = false
                    nullable = false
                })
            ) { entry ->
                entry.arguments?.getBoolean("button").let { button ->
                    if (button != null) {
                        UserProfileScreen(isBackButton = button)
                    }
                }
            }

            composable(route = Route.AboutAppScreen.path) {
                AboutAppScreen()
            }

            composable(route = Route.FeedbackScreen.path) {
                FeedbackScreen()
            }

            composable(route = Route.FAQScreen.path) {
                FAQScreen()
            }

            composable(route = Route.SubscriptionsScreen.path) {
                SubscriptionsScreen()
            }

            composable(route = Route.BlockedUsersScreen.path) {
                BlockedUsersScreen()
            }

            composable(route = Route.PlayerProfileScreen.path) {
                PlayerProfileScreen()
            }

            composable(route = Route.MarkParticipantsScreen.path) {
                MarkParticipantsScreen()
            }

            composable(route = Route.EvaluateEventScreen.path) {
                EvaluateEventScreen()
            }

            composable(route = Route.BestPlayerScreen.path) {
                BestPlayerScreen()
            }

            composable(route = Route.NotificationScreen.path) {
                NotificationScreen()
            }

            composable(route = Route.CalendarScreen.path) {
                CalendarScreen()
            }

            composable(route = Route.ChangeProfileScreen.path) {
                ChangeProfileScreen()
            }

            composable(route = Route.ViewingPhotoScreen.path + "/{type}",
                arguments = listOf(navArgument("type") {
                    type = NavType.IntType
                    defaultValue = CameraGallery.Gallery.ordinal
                    nullable = false
                })
            ) { entry ->
                entry.arguments?.getInt("type").let { type ->
                    if (type != null) {
                        ViewingPhotoScreen(type = type)
                    }
                }
            }

            composable(route = Route.LeaveMessageScreen.path) {
                LeaveMessageScreen()
            }
        }
    }
}
