package com.example.footballplayassistant.presentation.ui.screens.search_tab

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.buttons.CommonButton
import com.example.footballplayassistant.presentation.customviews.dialogwindows.DialogScreen
import com.example.footballplayassistant.presentation.customviews.dropdownmenus.DropDownMenu
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.ui.theme.spacing


@Composable
@Preview
fun CreateFieldScreen() {
    var showImageBoolean by remember { mutableStateOf(false) }
    var showFieldCreatedDialog by remember {
        mutableStateOf(false)
    }
    Box (modifier = Modifier
        .fillMaxSize()
        .then(if (showImageBoolean) Modifier.blur(16.dp) else Modifier)) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            HeaderWithBackButton(stringResource(id = R.string.addField), onClickBack = { TODO() })
            Text(
                text = stringResource(id = R.string.fieldInfoTitle),
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium),
                style = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
            )
            NecessaryTextField(
                label = stringResource(id = R.string.fieldName),
                true,
                leadingIcon = R.drawable.ic_field
            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
            DropDownMenu(placeholder = stringResource(R.string.inputCity), values = listOf())
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
            NecessaryTextField(
                label = stringResource(id = R.string.inputAddress),
                true,
                leadingIcon = R.drawable.ic_location
            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                NecessaryTextField(
                    label = stringResource(id = R.string.openTime),
                    true,
                    trailingIcon = R.drawable.ic_time_black_24,
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(end = MaterialTheme.spacing.extraSmall)
                )
                NecessaryTextField(
                    label = stringResource(id = R.string.closingTime),
                    true,
                    trailingIcon = R.drawable.ic_time_black_24,
                    modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall)
                )
            }
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
            NecessaryTextField(
                label = stringResource(id = R.string.inputContacts),
                isNecessary = true,
                leadingIcon = R.drawable.ic_call
            )
            DropDownMenu(
                placeholder = stringResource(id = R.string.closeMetro),
                values = listOf(),
                imStart = R.drawable.ic_metro
            )
            NecessaryTextField(
                label = stringResource(id = R.string.inputSite),
                leadingIcon = R.drawable.ic_world
            )
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))
            NecessaryTextField(
                label = stringResource(id = R.string.inputDescription),
                modifier = Modifier.defaultMinSize(minHeight = MaterialTheme.spacing.extraLarge),
                isSingleLine = false,
                shape = RoundedCornerShape(20.dp),
                toCountWords = 255
            )

            Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AddAsterisk(
                    text = stringResource(id = R.string.howManyPeopleInGame),
                    style = MaterialTheme.typography.displayMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.W400,
                    isNecessary = true,
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.small)
                        .weight(1f, fill = false)
                )
                DropDownMenu(
                    placeholder = stringResource(id = R.string.personInput), values = listOf(),
                    modifier = Modifier.width(120.dp)
                )
            }

            LoadImageField()
            FinishedLoadingPhoto(
                image = R.drawable.loadexample,
                fileName = "football.jpeg",
                size = "260 Кбайт",
                onShowChange = { newValue -> showImageBoolean = newValue }
            )
            InProcessLoadingPhoto(fileName = "football.jpeg", fileSize = "260 Кбайт")
            InProcessLoadingPhoto(fileName = "football.jpeg", fileSize = "260 Кбайт")
            ErrorText(errorType = LoadError.FileTooBig.ordinal)
            ErrorText(errorType = LoadError.FormatUnsupported.ordinal)
            ErrorText(errorType = LoadError.LimitExceeded.ordinal)


            Column(
                modifier = Modifier.padding(
                    top = MaterialTheme.spacing.medium,
                    bottom = MaterialTheme.spacing.large
                )
            ) {
                Text(
                    text = stringResource(id = R.string.fieldSize),
                    style = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
                )
                Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
                FilterRangeSlider(
                    text = stringResource(id = R.string.lengthInput),
                    MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.W400
                    ),
                    activeRangeStart = 0f,
                    activeRangeEnd = 1000.0f
                )
                FilterRangeSlider(
                    text = stringResource(id = R.string.widthInput),
                    MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.W400
                    ),
                    activeRangeStart = 0f,
                    activeRangeEnd = 1000.0f
                )
            }
            CustomRadioButtons(
                stringResource(id = R.string.typesOfArena),
                true,
                stringArrayResource(id = R.array.typesOfArenaArray)
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
            CustomRadioButtons(
                stringResource(id = R.string.lightningType),
                true,
                stringArrayResource(id = R.array.lightningTypeArray)
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
            CustomRadioButtons(
                stringResource(id = R.string.showering),
                false,
                stringArrayResource(id = R.array.hasOrNo)
            )
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.tertiaryContainer
            )
            CustomRadioButtons(
                stringResource(id = R.string.changingRoom),
                false,
                stringArrayResource(id = R.array.hasOrNo)
            )
            Column(
                modifier = Modifier.padding(vertical = MaterialTheme.spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CommonButton(stringResource(id = R.string.addField),
                    onClick = { showFieldCreatedDialog = true })
                Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))
                ClickableText(text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.W600,
                            color = MaterialTheme.colorScheme.onSecondaryContainer
                        )
                    ) {
                        append(stringResource(id = R.string.cancel))
                    }
                },
                    style = MaterialTheme.typography.bodySmall,
                    onClick = {})

            }

        }
    }

    if (showFieldCreatedDialog) {
        DialogScreen(header = stringResource(R.string.requestSentTitle), description =
        stringResource(R.string.requestSentDescription),
            greenButton = stringResource(R.string.returnToMainPage),
            image = R.drawable.ic_check_92,
            onDismissRequest = {})
    }
}

@Composable
fun LoadImageField() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(
            top = MaterialTheme.spacing.large,
            bottom = MaterialTheme.spacing.medium
        ),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Column(modifier = Modifier
            .weight(1f, fill = false)
            .padding(vertical = MaterialTheme.spacing.extraSmall)) {
            Text(stringResource(R.string.loadImageInput), style = MaterialTheme.typography.displayMedium.copy(color = MaterialTheme.colorScheme.onPrimaryContainer))
            Spacer(modifier = Modifier.size(MaterialTheme.spacing.extraSmall))
            Text(
                stringResource(id = R.string.loadImageDescription), style = MaterialTheme.typography.displaySmall
                    .copy(color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.W400))
        }
        OutlinedButton(onClick = { /*TODO*/ },
            shape = CircleShape,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(48.dp)
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_download), contentDescription = stringResource(id = R.string.loadPhoto),
                tint = MaterialTheme.colorScheme.onPrimaryContainer)

        }
    }
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun InProcessLoadingPhoto(fileName : String, fileSize : String) {
    val currentProgress by remember { mutableFloatStateOf(50.0f) }
    Column (modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = MaterialTheme.spacing.medium)){
        FlowRow(verticalArrangement = Arrangement.Center) {
            Text(fileName, style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400,
                color = MaterialTheme.colorScheme.onPrimaryContainer),
                modifier = Modifier.padding(end = MaterialTheme.spacing.small))
            Text(fileSize, style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.W400), modifier = Modifier.padding(end = MaterialTheme.spacing.small))
            IconButton(onClick = {}, modifier = Modifier.size(16.dp)) {
                Icon(painterResource(id = R.drawable.ic_close), contentDescription = stringResource(
                    id = R.string.deletePhoto
                ),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer)
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = MaterialTheme.spacing.small)) {
            LinearProgressIndicator(
                    progress = { currentProgress },
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.tertiaryContainer
                )
        }
    }
}
@Composable
fun FinishedLoadingPhoto(image : Int, fileName : String, size : String, onShowChange : (Boolean) -> Unit) {
    var enabled by remember { mutableStateOf(false) }
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.spacing.small),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(modifier = Modifier.weight(1f, fill = false)) {
                    Image(
                        painterResource(id = image),
                        contentDescription = stringResource(R.string.loadPhoto),
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                enabled = true
                                onShowChange(true)
                            },
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier.padding(start = MaterialTheme.spacing.small)) {
                        Text(
                            fileName,
                            style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onPrimaryContainer)
                        )
                        Text(
                            size, style = MaterialTheme.typography.displaySmall.copy(
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            ), modifier = Modifier.padding(top = MaterialTheme.spacing.extraSmall)
                        )
                    }
                }

                IconButton(onClick = {}) {
                    Icon(
                        painterResource(id = R.drawable.ic_close),
                        contentDescription = stringResource(
                            id = R.string.deletePhoto
                        ),
                        modifier = Modifier.size(14.dp),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }

            }
            HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.primaryContainer)
        }
    if (enabled) {
        ImageDialog(image) {
            enabled = false
            onShowChange(false)
        }
    }
}


@Composable
fun ImageDialog(image : Int, onDismissRequest : () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)
        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.large
                )) {
                Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(onClick = onDismissRequest,
                modifier = Modifier.size(42.dp),
                contentPadding = PaddingValues(MaterialTheme.spacing.small)
                ) {
                Icon(painterResource(id = R.drawable.ic_close), contentDescription =
                    stringResource(R.string.closeImagePreview),
                    tint = MaterialTheme.colorScheme.onPrimaryContainer)
            }
            }

                Image(
                    painterResource(image), contentDescription = stringResource(R.string.loadedImage),
                    modifier = Modifier.clip(RoundedCornerShape(12.dp))
                )
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.large)) {
                OutlinedButton(onClick = {},
                    modifier = Modifier.size(42.dp),
                    contentPadding = PaddingValues(MaterialTheme.spacing.small)
                    ) {
                    Icon(painterResource(id = R.drawable.ic_back_arrow_10_18), contentDescription = stringResource(R.string.showPreviousImage),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                OutlinedButton(onClick = {},
                    modifier = Modifier.size(42.dp),
                    contentPadding = PaddingValues(MaterialTheme.spacing.small)) {
                    Icon(painterResource(id = R.drawable.ic_arrow_next_24), contentDescription = stringResource(id = R.string.showNextImage),
                        tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        }
    }
}
@Composable
fun ErrorText(errorType : Int) {
    Row(modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall)) {
        Icon(
            painterResource(id = R.drawable.ic_warning_12),
            contentDescription = stringResource(R.string.warningIcon),
            tint = MaterialTheme.colorScheme.error
        )
        Text(
            stringArrayResource(id = R.array.loadFileErrorArray)[errorType],
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.W400
            ),
            modifier = Modifier.padding(start = MaterialTheme.spacing.extraSmall)
        )
    }
}

enum class LoadError {
    FileTooBig,
    FormatUnsupported,
    LimitExceeded
}
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CustomRadioButtons(title: String, isNecessary: Boolean, items: Array<String>) {
    val state = remember { mutableIntStateOf(-1) }
    Column(modifier = Modifier.fillMaxWidth()) {
        AddAsterisk(
            text = title,
            style = MaterialTheme.typography.displayMedium,
            isNecessary = isNecessary,
            modifier = Modifier.padding(top = MaterialTheme.spacing.small)
        )
        FlowRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = MaterialTheme.spacing.medium)
        ) {
            items.forEachIndexed { index, name ->
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(end = MaterialTheme.spacing.medium)) {
                    IconToggleButton(
                        modifier = Modifier.size(24.dp),
                        checked = index == state.intValue,
                        onCheckedChange = { state.intValue = index },
                    ) {
                        if (state.intValue == index) {
                            Icon(
                                painterResource(R.drawable.ic_radio_checked),
                                contentDescription = stringResource(R.string.radioButtonOn),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        } else {
                            Icon(
                                painterResource(R.drawable.ic_radio_unchecked),
                                contentDescription = stringResource(R.string.radioButtonOff),
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                    Text(
                        name,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400),
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.small)
                    )
                }
            }
        }
    }
}


@Composable
fun NecessaryTextField(label : String,
                       isNecessary : Boolean = false,
                       trailingIcon : Int = 0,
                       leadingIcon : Int = 0,
                       isSingleLine : Boolean = true,
                       shape : RoundedCornerShape = RoundedCornerShape(60.dp),
                       toCountWords : Int = 0,
                       removeLabelAbove : Boolean = false,
                       containerColor : Color = MaterialTheme.colorScheme.onPrimary,
                       placeholderColor : Color = MaterialTheme.colorScheme.onSecondaryContainer,
                       isEmpty : (Boolean) -> Unit = {},
                       onClick: (String) -> Unit = {},
                       @SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    var value by remember { mutableStateOf(TextFieldValue(""))}
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val visible by remember { derivedStateOf { (isFocused || value.text.isNotEmpty()) } }
    Column {
            AnimatedVisibility(
                visible = visible && !removeLabelAbove,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                AddAsterisk(
                    text = label, style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.W400,
                    isNecessary = isNecessary,
                    modifier = Modifier.padding(MaterialTheme.spacing.small)
                )
            }
        Box(contentAlignment = Alignment.BottomEnd) {
            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(shape)
                    .background(containerColor)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                value = value,
                onValueChange = {
                    value = it
                    onClick(value.text)
                    isEmpty(value.text.isEmpty())
                                },
                interactionSource = interactionSource,
                singleLine = isSingleLine,
                textStyle = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W400, lineHeight = 24.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            ) { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.small),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        Modifier.weight(1f, fill = false),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (leadingIcon != 0) {
                            Icon(
                                painter = painterResource(id = leadingIcon),
                                contentDescription = stringResource(R.string.leadingFieldIcon),
                                modifier = Modifier.padding(end = MaterialTheme.spacing.small),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        if (!isFocused && value.text.isEmpty()) {
                            AddAsterisk(
                                text = label, style = MaterialTheme.typography.labelLarge,
                                color = placeholderColor,
                                fontWeight = FontWeight.W400,
                                isNecessary = isNecessary
                            )
                        }

                        innerTextField()
                    }
                    if (trailingIcon != 0) {
                        Icon(
                            painter = painterResource(id = trailingIcon), contentDescription = stringResource(R.string.trailingFieldIcon),
                            modifier = Modifier.padding(start = MaterialTheme.spacing.small),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }

            }
            if (toCountWords > 0) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_rezible_10),
                    contentDescription = stringResource(R.string.extendedTextFieldIconDescription),
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(
                        bottom = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.small
                    )
                )
            }
        }
        AnimatedVisibility(
            visible = value.text.isNotEmpty() && toCountWords > 0,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Text(
                    text = "${value.text.count()}/$toCountWords",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = MaterialTheme.spacing.extraSmall),
                    textAlign = TextAlign.End,
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        fontWeight = FontWeight.W400
                    )
            )
        }
    }


}

@Composable
fun AddAsterisk(text : String,
                style : TextStyle,
                color : Color = MaterialTheme.colorScheme.onPrimaryContainer,
                fontWeight: FontWeight = FontWeight.W500,
                isNecessary: Boolean = false,
                maxLines : Int = 1,
                overflow: TextOverflow = TextOverflow.Ellipsis,
                @SuppressLint("ModifierParameter") modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = color,
                    fontWeight = fontWeight
                )
            ) {
                append(text)
            }
            if (isNecessary) {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onBackground)) {
                    append("*")
                }
            }
        }, style = style,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow
    )


}