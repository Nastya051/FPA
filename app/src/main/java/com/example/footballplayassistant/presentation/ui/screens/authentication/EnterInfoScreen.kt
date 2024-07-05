package com.example.footballplayassistant.presentation.ui.screens.authentication

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.ButtonCalendar
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.ButtonDropDownMenu
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.DropDownMenu
import com.example.footballplayassistant.presentation.enums.getGenders
import com.example.footballplayassistant.presentation.enums.getLevels
import com.example.footballplayassistant.presentation.enums.getPositions
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.screens.search_tab.NecessaryTextField
import com.example.footballplayassistant.presentation.ui.theme.spacing
import java.util.Date

@Composable
@Preview
fun EnterInfoScreen() {
    val navController = LocalNavController.current!!
    val context = LocalContext.current

    val showDialog = remember { mutableStateOf(false) }
    val buttonEnable = remember { mutableStateOf(false) }

    val permission = remember { mutableStateOf(false) }

    val date = remember { mutableStateOf("") }
    val gender = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val level = remember { mutableStateOf("") }
    val position = remember { mutableStateOf("") }
    val tellAboutYourself = remember { mutableStateOf("") }

    DialogScreen(
        header = stringResource(id = R.string.dataNotSaved),
        description = stringResource(id = R.string.dataNotSavedDescription),
        greenButton = stringResource(id = R.string.enterInfoAboutYourselt),
        whiteButton = stringResource(id = R.string.continueBtn),
        image = R.drawable.ic_warning_93,
        onClickGreen = { showDialog.value = false },
        onClickWhite = { permission.value = true },
        onDismissRequest = { showDialog.value = false },
        showDialog = showDialog.value
    )

    Scaffold(bottomBar = {
        Column {
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.tertiary)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primaryContainer)
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                LaunchedEffect(
                    date.value,
                    gender.value,
                    city.value,
                    level.value,
                    position.value,
                    tellAboutYourself.value
                ) {
                    buttonEnable.value =
                        (date.value.isNotEmpty() && gender.value.isNotEmpty() && city.value.isNotEmpty()
                                && level.value.isNotEmpty() && position.value.isNotEmpty() &&
                                tellAboutYourself.value.isNotEmpty())
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
                    text = stringResource(id = R.string.save),
                    containerColor = animatedContainerColor,
                    contentColor = animatedContentColor,
                    enable = buttonEnable.value,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500),
                    onClick = { navController.navigate(Route.MainScreen.path) },
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(
                            start = MaterialTheme.spacing.medium,
                            end = MaterialTheme.spacing.small
                        )
                        .align(Alignment.CenterVertically)
                )
                CommonButton(
                    text = stringResource(id = R.string.skip),
                    onClick = { showDialog.value = true },
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W500),
                    modifier = Modifier
                        .weight(0.4f)
                        .padding(end = MaterialTheme.spacing.medium)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }) {
        LazyColumn(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(it)
                .padding(top = 12.dp, start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.enterInfoYourself),
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W600),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                )
            }

            item {
                AnimatedVisibility(visible = date.value.isNotEmpty()){
                    Text(
                        text = stringResource(id = R.string.birthday),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                ButtonCalendar(
                    onCLick = { openCalendar(context, date) },
                    date = date.value,
                    placeholder = stringResource(id = R.string.birthday),
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .heightIn(min = 48.dp)
                )
            }

            item {
                AnimatedVisibility(visible = gender.value.isNotEmpty()){
                    Text(
                        text = stringResource(id = R.string.sexMatch),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                ButtonDropDownMenu(
                    placeholder = stringResource(id = R.string.sex),
                    values = getGenders(),
                    imStart = R.drawable.ic_sex_23,
                    onClick = { value ->
                        gender.value = value
                    },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .heightIn(min = 48.dp)
                )
            }

            item {
                AnimatedVisibility(visible = city.value.isNotEmpty()){
                    Text(
                        text = stringResource(id = R.string.yourCity),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                DropDownMenu(
                    placeholder = stringResource(id = R.string.city),
                    imTrail = R.drawable.ic_arrow_menu_18_10,
                    values = listOf("1", "2", "3"),//из БД
                    onClick = { value ->
                        city.value = value
                    },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .heightIn(min = 48.dp)
                )
            }

            item {
                AnimatedVisibility(visible = level.value.isNotEmpty()){
                    Text(
                        text = stringResource(id = R.string.playLevel),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                ButtonDropDownMenu(
                    placeholder = stringResource(id = R.string.levelPlay),
                    values = getLevels(),
                    onClick = { value ->
                        level.value = value
                    },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .heightIn(min = 48.dp)
                )
            }

            item {
                AnimatedVisibility(visible = position.value.isNotEmpty()){
                    Text(
                        text = stringResource(id = R.string.prefferedPosition),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                ButtonDropDownMenu(
                    placeholder = stringResource(id = R.string.position),
                    values = getPositions(),
                    onClick = { value ->
                        position.value = value
                    },
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .heightIn(min = 48.dp)
                )
            }

            item {
                AnimatedVisibility(visible = tellAboutYourself.value.isNotEmpty()){
                    Text(
                        text = stringResource(id = R.string.tellYourself),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    )
                }
                NecessaryTextField(
                    label = stringResource(id = R.string.tellYourself),
                    isSingleLine = false,
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    shape = RoundedCornerShape(20.dp),
                    toCountWords = 300,
                    removeLabelAbove = true,
                    onClick = { text -> tellAboutYourself.value = text }
                )
            }
        }
    }

    if(permission.value) {
        if (checkPermissions())
            navController.navigate(Route.MainScreen.path)
        else
            RequestPermissions()
    }
}

fun openCalendar(context: Context, date: MutableState<String>) {
    val m_context = context

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val Date = date

    val mDatePickerDialog = android.app.DatePickerDialog(
        m_context,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            Date.value = "$mDayOfMonth.${mMonth + 1}.$mYear"
        }, year, month, day
    )

    mDatePickerDialog.show()
}


@Composable
private fun checkPermissions(): Boolean {
    return ActivityCompat.checkSelfPermission(
        LocalContext.current, Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
        LocalContext.current, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

@Composable
private fun RequestPermissions() {
    val navController = LocalNavController.current!!
    val locationPermissions = arrayOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    val permissionRequest =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val granted = permissions.entries.all {
                it.value
            }

            if (granted)
                navController.navigate(Route.MainScreen.path)
        }
    LaunchedEffect(true) {
        permissionRequest.launch(locationPermissions)
    }
}