package com.faraji.sanags.core.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.faraji.sanags.R
import com.faraji.sanags.core.presentation.ui.theme.Green

@Composable
fun CustomTextField(
    isEntryValid: Boolean = false,
    text: String,
    textFieldTitle: Int = 0,
    keyboardType: KeyboardType = KeyboardType.Text,
    keyboardActions: KeyboardActions = KeyboardActions(),
    imeAction: ImeAction = ImeAction.Default,
    isMobileNumber: Boolean = false,
    isPhoneNumber: Boolean = false,
    error: String = "",
    maxLines: Int = 1,
    singleLine: Boolean = true,
    onValueChange: (String) -> Unit
) {

    val focusRequester = remember {
        FocusRequester()
    }


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable {
                focusRequester.requestFocus()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .padding(4.dp)
                .size(24.dp)
                .clip(CircleShape)
                .background(if (isEntryValid) Green else Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            if (isEntryValid) {
                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = stringResource(id = R.string.check),
                    tint = MaterialTheme.colors.onSecondary.copy(
                        alpha = 0.8f
                    ),
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(tween(150))
                    .weight(1f)
                    .focusRequester(focusRequester)
            ) {
                TextField(
                    value = text,
                    onValueChange = {
                        onValueChange(it)
                    },
                    maxLines = maxLines,
                    singleLine = singleLine,
                    textStyle = MaterialTheme.typography.body2,
                    keyboardActions = keyboardActions,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction,
                        autoCorrect = false
                    ),
                    isError = error.isNotBlank(),
                    colors = TextFieldDefaults.textFieldColors(
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        backgroundColor = Color.Transparent
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
                if (error.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 4.dp),
                        text = error,
                        textAlign = TextAlign.Start,
                        style = MaterialTheme.typography.body2.copy(
                            textAlign = TextAlign.Justify,
                            color = MaterialTheme.colors.error
                        )
                    )
                }
            }
        }

        Text(
            text = stringResource(id = textFieldTitle),
            style = MaterialTheme.typography.body2.copy(
                color = MaterialTheme.colors.primary
            ),
            modifier = Modifier
                .padding(horizontal = 4.dp)
                .defaultMinSize(minWidth = 72.dp)
        )

    }

}