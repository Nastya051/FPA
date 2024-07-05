package com.example.footballplayassistant.presentation.ui.screens.search_tab

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.footballplayassistant.R
import com.example.footballplayassistant.presentation.customviews.headers.HeaderWithBackButton
import com.example.footballplayassistant.presentation.ui.theme.spacing

@Composable
fun AdditionalFieldInfoScreen() {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
            HeaderWithBackButton(text = stringResource(R.string.additionalInfoScreenTitle))
            Text(
                stringResource(R.string.fieldNameTitle),
                style = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.W600
                ),
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.medium,
                    top = MaterialTheme.spacing.small)
            )
            Text(
                stringResource(R.string.fieldInfoDescription),
                style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    fontWeight = FontWeight.W400
                ),
                modifier = Modifier.padding(bottom = MaterialTheme.spacing.extraLarge)
            )
        }
        Column(modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(MaterialTheme.spacing.medium)) { // Данные - заглушка
            FieldInfoDetail(icon = R.drawable.ic_ruler, title = stringResource(R.string.fieldSizeInfo), value = stringResource(R.string.fieldSizeValue))
            FieldInfoDetail(icon = R.drawable.ic_field, title = stringResource(R.string.fieldTypeInfo), value = stringResource(R.string.fieldTypeValue))
            FieldInfoDetail(icon = R.drawable.ic_covering, title = stringResource(R.string.fieldCoveringInfo), value = stringResource(R.string.fieldCoveringValue))
            FieldInfoDetail(icon = R.drawable.ic_shower, title = stringResource(R.string.fieldShoweringInfo), value = stringResource(R.string.fieldShoweringValue))
            FieldInfoDetail(icon = R.drawable.ic_lightbulb, title = stringResource(R.string.fieldLightInfo), value = stringResource(R.string.fieldLightValue))
            FieldInfoDetail(icon = R.drawable.ic_changing_room, title = stringResource(R.string.fieldChangingRoomInfo), value = stringResource(R.string.fieldChangingRoomValue))
            FieldInfoDetail(icon = R.drawable.ic_tribune, title = stringResource(R.string.fieldTribuneInfo), value = stringResource(R.string.fieldTribuneValue))
            FieldInfoDetail(icon = R.drawable.ic_people_24, title = stringResource(R.string.gamePlayerAmountInfo), value = stringResource(R.string.gamePlayerAmountValue))

        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FieldInfoDetail(icon : Int, title : String, value : String) {
    Column {
        FlowRow(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier.weight(1f, fill = false).align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(painterResource(id = icon), contentDescription = stringResource(R.string.fieldInfoIconDescription),
                    tint = MaterialTheme.colorScheme.secondary)
                Text(title, style = MaterialTheme.typography.labelMedium.copy(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    fontWeight = FontWeight.W400
                ))
            }
            Text(value, style = MaterialTheme.typography.displayMedium.copy(
                color = MaterialTheme.colorScheme.onPrimaryContainer
            ))
        }
        HorizontalDivider(color = MaterialTheme.colorScheme.tertiaryContainer, thickness = 1.dp,
            modifier = Modifier.padding(vertical = MaterialTheme.spacing.extraSmall))
    }
}