package com.faraji.sanags.feature_get_info.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.faraji.sanags.core.domain.model.AddressList

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddressItem(
    item: AddressList
) {

    var showMore by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .defaultMinSize(minHeight = 150.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        onClick = {
            showMore = !showMore
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            if (showMore) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item.address,
                        style = MaterialTheme.typography.body2.copy(
                            textAlign = TextAlign.Justify
                        ),
                        modifier = Modifier.align(Alignment.CenterEnd),
                        overflow = TextOverflow.Ellipsis
                    )
                }
            } else {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item.address,
                        style = MaterialTheme.typography.body2.copy(
                            textAlign = TextAlign.Justify
                        ),
                        modifier = Modifier.align(Alignment.CenterEnd),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                color = Color.LightGray
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = item.mobile,
                    style = MaterialTheme.typography.body2.copy(
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.body2.copy(
                        textAlign = TextAlign.Justify
                    ),
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

        }

    }

}