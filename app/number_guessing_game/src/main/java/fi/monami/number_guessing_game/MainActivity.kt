package fi.monami.number_guessing_game

import UpDownGame
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.monami.number_guessing_game.ui.theme.AndroidoStudioPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                var game by remember {mutableStateOf(UpDownGame())}
                var guessedNum by remember { mutableStateOf("") }
                var result by remember { mutableStateOf(R.string.initial_feedback) }
                var isCorrectResult by remember{mutableStateOf(false)}
                MainView(
                    game,
                    guessedNum,
                    {
                        guessedNum = it
                    },
                    {
                        val comparedResult = game.compareNumbers(guessedNum.toInt())
                        when(comparedResult){
                            -1 -> result = R.string.too_low
                            0 -> {
                                isCorrectResult = true
                                result = R.string.correct_num

                            }
                            1 -> result = R.string.too_high
                        }
                    },
                    result,
                    isCorrectResult
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(game: UpDownGame, guessedNum: String, onValueChange: (String) -> Unit, onClick: ()-> Unit, @StringRes result: Int, isCorrectResult: Boolean, modifier: Modifier = Modifier){

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
        }

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
                        guessedNum,
                        onValueChange,
                        Modifier.width(200.dp)
                    )
                    Spacer(Modifier.width(20.dp))
                    GuessButton(onClick)
                }
                Spacer(Modifier.height(50.dp))
                ResultFeedbackText(result, isCorrectResult, game)
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
    Button(onClick) {
        Text(text = stringResource(R.string.guess_button_label))
    }
}

@Composable
fun ResultFeedbackText(@StringRes feedbackText: Int, isCorrectResult: Boolean, game: UpDownGame, modifier: Modifier = Modifier){
    Text(
        text = stringResource(feedbackText),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    if(isCorrectResult) {
        Text(
            text = stringResource(R.string.attempted_num, game.guessedNumCounter)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidoStudioPracticeTheme {

    }
}