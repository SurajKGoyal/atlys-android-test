package com.example.atlystest.ui.trending

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atlystest.util.clearFocusOnKeyboardDismiss
import com.example.atlystest.util.debounce

@Composable
fun SearchComposeView(
    hint: String = "Search Movies",
    borderColor: Color = Color.Gray,
    searchIconTint: Color = Color.Gray,
    searchIconSize: Dp = 45.dp,
    onSearchClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    var searchValue by remember {
        mutableStateOf("")
    }

    searchValue.debounce {
        onValueChange(it)
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onSearchClick?.invoke() }
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val interactionSource = remember { MutableInteractionSource() }

        TextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            modifier = Modifier
                .fillMaxSize()
                .clearFocusOnKeyboardDismiss()
                .onFocusChanged {
                    if (it.hasFocus) {
                        onSearchClick?.invoke()
                    }
                },
            interactionSource = interactionSource,
            enabled = true,
            singleLine = true,
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = hint,
                    color = searchIconTint,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 24.sp
                    )
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .size(searchIconSize)
                        .padding(10.dp),
                    tint = searchIconTint
                )
            },
            trailingIcon = {
                if (searchValue != "") {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .size(searchIconSize)
                            .clickable {
                                searchValue = ""
                            },
                        tint = searchIconTint
                    )
                }
            },
        )
    }

}