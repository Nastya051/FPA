package com.example.footballplayassistant.presentation.navigation

sealed class Route(val path: String) {

    data object StartScreen : Route(path = "start_screen_route")

    data object SignInScreen : Route(path = "sign_in_screen")

    data object SignUpEnterPhoneScreen : Route(path = "sign_up_enter_phone_screen")

    data object SignUpCodeScreen : Route(path = "sign_up_code_screen")

    data object SignUpStepOneScreen : Route(path = "sign_up_step_one_screen")

    data object SignUpStepTwoScreen : Route(path = "sign_up_step_two_screen")

    data object EnterInfoScreen : Route(path = "enter_info_screen")

    data object MainScreen : Route(path = "main_screen")

    data object CreateEventScreen : Route(path = "create_event_screen")

    data object NewsScreen : Route(path = "news_screen")

    data object MatchScreen : Route(path = "match_screen")

    data object MatchInfoScreen : Route(path = "match_info_screen")

    data object ChooseTeamScreen : Route(path = "choose_team_screen")

    data object PaymentScreen : Route(path = "payment_screen")

    data object InviteFriendsScreen : Route(path = "invite_friends_screen")

    data object SearchScreen : Route(path = "search_screen")

    data object FilterScreen : Route(path = "filter_screen")

    data object CreateFieldScreen : Route(path = "create_field_screen")

    data object FieldInfoScreen : Route(path = "field_info_screen")

    data object AdditionalFieldInfoScreen : Route(path = "additional_field_info_screen")

    data object ComingEventsScreen : Route(path = "coming_events_screen")

    data object MatchParticipantsScreen : Route(path = "match_participants_screen")

    data object ManagingParticipantsScreen : Route(path = "managing_participants_screen")

    data object RulesAndPoliticScreen : Route(path = "rules_and_politic_screen")

    data object ForgotPasswordScreen : Route(path = "forgot_password_screen")

    data object RecoveryPasswordScreen : Route(path = "recovery_password_screen")

    data object EditGameScreen : Route(path = "edit_game_screen")

    data object MyGamesScreen : Route(path = "my_games_screen")

    data object UserProfileScreen : Route(path = "user_profile_screen")

    data object WalletScreen : Route(path = "wallet_screen")

    data object SafetyScreen : Route(path = "safety_screen")

    data object AboutAppScreen : Route(path = "about_app_screen")

    data object FeedbackScreen : Route(path = "feedback_screen")

    data object FAQScreen : Route(path = "faq_screen")

    data object SubscriptionsScreen : Route(path = "subscriptions_screen")

    data object BlockedUsersScreen : Route(path = "blocked_users_screen")

    data object PlayerProfileScreen : Route(path = "player_profile_screen")

    data object CalendarScreen : Route(path = "calendar_screen")

    data object MarkParticipantsScreen : Route(path = "mark_participants_screen")

    data object NotificationScreen : Route(path = "notification_screen")

    data object EvaluateEventScreen : Route(path = "evaluate_event_screen")

    data object BestPlayerScreen : Route(path = "best_player_screen")

    data object ChangeProfileScreen : Route(path = "change_profile_screen")

    data object ViewingPhotoScreen : Route(path = "viewing_photo_screen")


    data object LeaveMessageScreen : Route(path = "leave_message_screen")


    fun withArgs(vararg args: String): String {
        return buildString {
            append(path)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}