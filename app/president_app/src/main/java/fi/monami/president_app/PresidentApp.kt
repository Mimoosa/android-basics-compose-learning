package fi.monami.president_app

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class PresidentApp {
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PresidentTopAppBar(
    modifier: Modifier = Modifier,
){
    CenterAlignedTopAppBar(
        title = { Text("President App") },
        modifier = modifier,

    )
}