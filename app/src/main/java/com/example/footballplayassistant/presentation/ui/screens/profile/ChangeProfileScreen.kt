package com.example.footballplayassistant.presentation.ui.screens.profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.ButtonCalendar
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.ButtonDropDownMenu
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.CommonActionsMenu
import com.example.footballplayassistant.presentation.customviews.headers.HeaderUserProfile
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.customviews.textfields.CommonTextField
import com.example.footballplayassistant.presentation.enums.CameraGallery
import com.example.footballplayassistant.presentation.enums.getGenders
import com.example.footballplayassistant.presentation.enums.getLevels
import com.example.footballplayassistant.presentation.enums.getPositions
import com.example.footballplayassistant.presentation.navigation.LocalNavController
import com.example.footballplayassistant.presentation.navigation.Route
import com.example.footballplayassistant.presentation.ui.screens.authentication.addStar
import com.example.footballplayassistant.presentation.ui.screens.authentication.openCalendar
import com.example.footballplayassistant.presentation.ui.screens.search_tab.NecessaryTextField

@Composable
fun ChangeProfileScreen() {
    val navController = LocalNavController.current!!
    val expanded = remember { mutableStateOf(false) }
    val buttonEnable = remember { mutableStateOf(false) }
    val showDialogSave = remember { mutableStateOf(false) }
    val showDialogDeleteFoto = remember { mutableStateOf(false) }
    val deleteCurrentFoto = remember { mutableStateOf(false) }
    val currentFoto = remember { mutableIntStateOf(R.drawable.user_foto) }
    val emailConfirm = remember { mutableStateOf(false) }

    DialogScreen(
        header = stringResource(id = R.string.doneWithPoint),
        description = stringResource(id = R.string.changesSavedSuccess),
        greenButton = stringResource(id = R.string.returnToProfile),
        whiteButton = stringResource(id = R.string.toMain),
        image = R.drawable.ic_check_92,
        onClickGreen = { navController.navigate(Route.UserProfileScreen.withArgs("false")) },
        onClickWhite = { navController.navigate(Route.MainScreen.path) },
        onDismissRequest = { showDialogSave.value = false },
        showDialog = showDialogSave.value
    )

    DialogScreen(
        header = stringResource(id = R.string.deleteQuestionPhoto),
        description = stringResource(id = R.string.youWillNotHavePhoto),
        greenButton = stringResource(id = R.string.delete),
        whiteButton = stringResource(id = R.string.cancel),
        onClickGreen = {
            deleteCurrentFoto.value = true
            showDialogDeleteFoto.value = false
            currentFoto.intValue = R.drawable.unknown_user_foto
        },
        onClickWhite = { showDialogDeleteFoto.value = false },
        onDismissRequest = { showDialogDeleteFoto.value = false },
        showDialog = showDialogDeleteFoto.value
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
    ) {
        HeaderUserProfile(modifier = Modifier.align(Alignment.TopCenter))

        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 30.dp)
        ) {
            HeaderWithBackButton(
                text = stringResource(id = R.string.myAccount),
                colorText = MaterialTheme.colorScheme.onPrimary,
                tint = MaterialTheme.colorScheme.onPrimary,
                onClickBack = { navController.navigate(Route.UserProfileScreen.withArgs("false")) },
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Box(
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
            ) {
                Image(
                    painter = painterResource(id = currentFoto.intValue),
                    contentDescription = "User foto",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.sizeIn(108.dp)
                )
                IconButton(
                    onClick = { expanded.value = !expanded.value },
                    colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.onPrimary),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .border(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            width = 4.dp,
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_pencil_22),
                        contentDescription = "Pencil",
                        tint = MaterialTheme.colorScheme.onSecondaryContainer
                    )

                    CommonActionsMenu(
                        expand = expanded,
                        actions = listOf(
                            R.string.chooseFromLibrary,
                            R.string.takeFoto,
                            R.string.deleteCurrent
                        ),
                        icons = listOf(
                            R.drawable.ic_image_24,
                            R.drawable.ic_camera_24,
                            R.drawable.ic_delete_foto_24
                        ),
                        onClicks = listOf(
                            { navController.navigate(Route.ViewingPhotoScreen.withArgs(
                                        CameraGallery.Gallery.ordinal.toString())) },
                            { navController.navigate(Route.ViewingPhotoScreen.withArgs(
                                CameraGallery.Camera.ordinal.toString())) },
                            { showDialogDeleteFoto.value = true }),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            LazyColumn(modifier = Modifier.padding(all = 16.dp)) {
                item {
                    Text(
                        text = addStar(id = R.string.nick),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )

                    CommonTextField(
                        placeholder = stringResource(id = R.string.enterNick),
                        keyBoard = KeyboardType.Text,
                        color = MaterialTheme.colorScheme.onPrimary,
                        onClick = { buttonEnable.value = true },
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
                item {
                    Text(
                        text = addStar(id = R.string.name),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )

                    CommonTextField(
                        placeholder = stringResource(id = R.string.enterName),
                        keyBoard = KeyboardType.Text,
                        color = MaterialTheme.colorScheme.onPrimary,
                        onClick = { buttonEnable.value = true },
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
                item {
                    Text(
                        text = addStar(id = R.string.surname),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )

                    CommonTextField(
                        placeholder = stringResource(id = R.string.enterSurname),
                        keyBoard = KeyboardType.Text,
                        color = MaterialTheme.colorScheme.onPrimary,
                        onClick = { buttonEnable.value = true },
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
                item {
                    Text(
                        text = addStar(id = R.string.bigEmail),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )

                    CommonTextField(
                        placeholder = stringResource(id = R.string.enterBigEmail),
                        keyBoard = KeyboardType.Text,
                        color = MaterialTheme.colorScheme.onPrimary,
                        onClick = { buttonEnable.value = true }
                    )
                    AnimatedVisibility(visible = emailConfirm.value) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, bottom = 12.dp, top = 2.dp)
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(R.drawable.ic_warning_12),
                                contentDescription = "Warning",
                                tint = MaterialTheme.colorScheme.errorContainer,
                                modifier = Modifier.padding(end = 2.dp)
                            )
                            Text(
                                text = stringResource(id = R.string.emailNotConfirm),
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.W400),
                                color = MaterialTheme.colorScheme.errorContainer
                            )
                        }
                    }
                    AnimatedVisibility(visible = emailConfirm.value){
                        Row(modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start) {
                            Button(colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.outlineVariant),
                                contentPadding = PaddingValues(0.dp),
                                onClick = { /*TODO*/ }) {
                                Text(
                                    text = stringResource(id = R.string.confirmEmail),
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontWeight = FontWeight.W600),
                                    color = MaterialTheme.colorScheme.primary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.ic_arrows_18_14),
                                    contentDescription = "Arrows"
                                )
                            }
                        }
                    }
                }
                item {
                    Text(
                        text = addStar(id = R.string.cityProfile),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp, top = 10.dp)
                    )

                    ButtonDropDownMenu(
                        placeholder = stringResource(id = R.string.city),
                        values = listOf("moskva", "spb", "ekb"),
                        textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                        onClick = { if(it.isNotEmpty()) buttonEnable.value = true },
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .heightIn(min = 48.dp)
                    )
                }
                item {
                    val context = LocalContext.current
                    val date = remember { mutableStateOf("") }

                    Text(
                        text = stringResource(id = R.string.birthday),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )
                    ButtonCalendar(
                        onCLick = { openCalendar(context, date) },
                        date = date.value,
                        placeholder = stringResource(id = R.string.birthday),
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .heightIn(min = 48.dp)
                    )
                    LaunchedEffect(key1 = date.value) {
                        if (date.value.isNotEmpty())
                            buttonEnable.value = true
                    }
                }
                item {
                    Text(
                        text = stringResource(id = R.string.sexMatch),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )
                    ButtonDropDownMenu(
                        placeholder = stringResource(id = R.string.sex),
                        values = getGenders(),
                        imStart = R.drawable.ic_sex_23,
                        onClick = { if(it.isNotEmpty()) buttonEnable.value = true },
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .heightIn(min = 48.dp)
                    )
                }
                item {
                    Text(
                        text = stringResource(id = R.string.levelPlayProfile),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )
                    ButtonDropDownMenu(
                        placeholder = stringResource(id = R.string.levelPlay),
                        values = getLevels(),
                        onClick = { if(it.isNotEmpty()) buttonEnable.value = true },
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .heightIn(min = 48.dp)
                    )
                }
                item {
                    Text(
                        text = stringResource(id = R.string.preferredPosition),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )
                    ButtonDropDownMenu(
                        placeholder = stringResource(id = R.string.position),
                        values = getPositions(),
                        onClick = { if(it.isNotEmpty()) buttonEnable.value = true },
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .heightIn(min = 48.dp)
                    )
                }
                item {
                    Text(
                        text = stringResource(id = R.string.infoYourself),
                        style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.W500),
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(bottom = 8.dp, start = 10.dp, end = 10.dp)
                    )
                    NecessaryTextField(
                        label = stringResource(id = R.string.tellYourself),
                        isSingleLine = false,
                        containerColor = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(20.dp),
                        toCountWords = 300,
                        removeLabelAbove = true,
                        onClick = { buttonEnable.value = it.isNotEmpty() }
                        )
                }
                item {
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
                        text = stringResource(id = R.string.saveChanges),
                        containerColor = animatedContainerColor,
                        contentColor = animatedContentColor,
                        enable = buttonEnable.value,
                        onClick = { showDialogSave.value = true },
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                }
            }
        }
    }
    LaunchedEffect(currentFoto.intValue == R.drawable.unknown_user_foto) {
        deleteCurrentFoto.value = false
    }
}