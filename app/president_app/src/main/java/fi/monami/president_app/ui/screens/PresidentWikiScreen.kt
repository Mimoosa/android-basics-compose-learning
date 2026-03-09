package fi.monami.president_app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fi.monami.president_app.President
import fi.monami.president_app.PresidentTopAppBar
import fi.monami.president_app.data.DataProvider

@Composable
fun PresidentWikiScreen (modifier: Modifier = Modifier){
    val viewModel: PresidentApiViewModel = viewModel()
    val uiState = viewModel.wikiUiState
    val presidents = DataProvider.presidents
    Scaffold(

        topBar = {
            PresidentTopAppBar()
        },

        ) { innerPadding ->
        HomeBody(uiState,
            presidents,
            {viewModel.getHits(it)},
          modifier.padding(innerPadding)
        )
    }

}

@Composable
fun HomeBody(
    uiState: Int,
    presidents: List<President>,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // Total Hits Card
        item {
            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                colors = androidx.compose.material3.CardDefaults.cardColors(
                    containerColor = androidx.compose.material3.MaterialTheme.colorScheme.primaryContainer
                ),
                elevation = androidx.compose.material3.CardDefaults.cardElevation(6.dp)
            ) {
                Text(
                    text = "Wikipedia search results for the selected president.",
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                    style = androidx.compose.material3.MaterialTheme.typography.titleLarge
                )
                Text(
                    text = uiState.toString(),
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize(),
                    style = androidx.compose.material3.MaterialTheme.typography.displayMedium
                )
            }
        }

        // List of presidents
        items(presidents) { president ->
            androidx.compose.material3.Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 6.dp)
            ) {
                Text(
                    text = president.name,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                        .clickable { onClick(president.name) },
                    style = androidx.compose.material3.MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}