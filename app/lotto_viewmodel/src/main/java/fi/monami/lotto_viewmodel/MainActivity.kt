package fi.monami.lotto_viewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import fi.monami.lotto_viewmodel.ui.LottoUiState
import fi.monami.lotto_viewmodel.ui.LottoViewModel
import fi.monami.lotto_viewmodel.ui.theme.AndroidoStudioPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                LottoApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("ResourceType")
@Composable
fun LottoApp(modifier: Modifier = Modifier){
    val viewModel: LottoViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier.padding(innerPadding).padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.padding(8.dp))

            Text(text = stringResource(R.string.instruction))

            Spacer(Modifier.padding(8.dp))

            SelectedNumbers(uiState, viewModel)

            Spacer(Modifier.padding(8.dp))

            PlayButton(uiState, viewModel)

            Spacer(Modifier.padding(8.dp))

            NumbersToBeSelected(uiState, viewModel)

            Spacer(Modifier.padding(8.dp))

            MatchedResult(uiState, viewModel)
        }

    }

}


@Composable
fun SelectedNumbers(uiState: LottoUiState, viewModel: LottoViewModel, modifier: Modifier = Modifier){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
    ) {
        items(uiState.selectedNumbers){
            Text(
                it.toString(),
                modifier = Modifier
                    .clickable{
                        viewModel.removeNumFromSelectedList(it)
                    },
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun PlayButton(uiState: LottoUiState, viewModel: LottoViewModel, modifier: Modifier = Modifier){
    Button(
        enabled = uiState.selectedNumbers.size == 7,
        onClick = {viewModel.checkHowManyNumbersMatched()}) {
        Text("Play")
    }
}

@Composable
fun NumbersToBeSelected(uiState: LottoUiState, viewModel: LottoViewModel, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(uiState.numbersToBeSelected){
            Text(
                it.toString(),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .clickable{
                        viewModel.addNumToSelectedList(it)
                    }
            )
        }
    }
}

@Composable
fun MatchedResult(uiState: LottoUiState, viewModel: LottoViewModel, modifier:Modifier = Modifier){
    if (uiState.guessResult != null) {
        val count = uiState.guessResult
        val text = if(count == 0){
            "No numbers matched"
        } else {
            pluralStringResource(
                id = R.plurals.number_matched,
                count = count,
                count
            )
        }
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Spacer(Modifier.padding(8.dp))
        Button(
            onClick = {
                viewModel.reset()
            }
        ) {
            Text("Reset")
        }
    }
}


