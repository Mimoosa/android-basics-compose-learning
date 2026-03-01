package fi.monami.number_guessing_game_ver2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

import fi.monami.number_guessing_game_ver2.ui.theme.AndroidoStudioPracticeTheme
import fi.monami.number_guessing_game_ver2.ui.theme.NumberGuessGameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                val viewModel: NumberGuessGameViewModel = viewModel()
                MainView(
                    viewModel,
                    {
                        viewModel.onValueChange(it)
                    },
                    {
                        viewModel.onClick()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    viewModel: NumberGuessGameViewModel,
    onValueChange: (String) -> Unit,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
){
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.game_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        modifier = modifier

    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Spacer(Modifier.height(50.dp))
                Text(
                    text = stringResource(R.string.game_instruction),
                    fontSize = 20.sp
                )
                Spacer(Modifier.height(50.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    GuessNumberField(
                        uiState.guessedNumber,
                        onValueChange,
                        Modifier.width(200.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    GuessButton(onClick)
                }
                Spacer(Modifier.height(50.dp))
                ResultFeedbackText(
                    uiState.resultText,
                    uiState.isResultCorrect,
                    uiState.totalGuessedNum
                )
            }
        }
    }
}

@Composable
fun GuessNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true
    )
}

@Composable
fun GuessButton(onClick:()-> Unit, modifier: Modifier = Modifier){
    Button(
        onClick = onClick,
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.guess_button_label))
    }
}

@Composable
fun ResultFeedbackText(
    feedbackText: Int,
    isCorrectResult: Boolean,
    totalGuessedNum: String,
    modifier: Modifier = Modifier
){

    Column(modifier) {
        Text(
            text = stringResource(feedbackText),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        if (isCorrectResult) {
            Text(
                text = stringResource(R.string.attempted_num, totalGuessedNum)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidoStudioPracticeTheme {

    }
}