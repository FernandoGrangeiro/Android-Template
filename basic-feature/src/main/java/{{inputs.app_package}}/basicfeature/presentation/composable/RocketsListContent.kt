package {{inputs.app_package}}.basicfeature.presentation.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import {{inputs.app_package}}.basicfeature.R
import {{inputs.app_package}}.basicfeature.presentation.model.RocketDisplayable

@Composable
fun RocketsListContent(
    rocketList: List<RocketDisplayable>,
    onRocketClick: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(
                horizontal = dimensionResource(id = R.dimen.dimen_medium)
            )
    ) {
        itemsIndexed(
            items = rocketList,
            key = { _, rocket -> rocket.id }
        ) { index, item ->
            RocketItem(
                rocket = item,
                onRocketClick = { onRocketClick(item.wikiUrl) }
            )

            if (index < rocketList.lastIndex) {
                Divider()
            }
        }
    }
}
