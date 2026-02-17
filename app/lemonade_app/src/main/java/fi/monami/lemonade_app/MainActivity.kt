package fi.monami.lemonade_app

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.monami.lemonade_app.ui.theme.AndroidoStudioPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainAppView()
                }
            }
        }
    }
}

@Composable
fun MainAppView(modifier: Modifier = Modifier){
    var phaseNum by remember { mutableStateOf(1) }
    var clickedNumber by remember { mutableStateOf(0) }
    var numOfSqueezing by remember { mutableStateOf((2..4).random()) }
    val imageSource = when(phaseNum){
        1 -> painterResource(R.drawable.lemon_tree)
        2 -> painterResource(R.drawable.lemon_squeeze)
        3 -> painterResource(R.drawable.lemon_drink)
        else -> painterResource(R.drawable.lemon_restart)
    }
    val imageDescriotion = when(phaseNum){
        1 -> "a lemon tree"
        2 -> "a lemon"
        3 -> "a lemonade"
        else -> "an empty glass"
    }
    val instructionText = when(phaseNum){
        1 -> stringResource(R.string.first_instruction)
        2 -> stringResource(R.string.second_instruction)
        3 -> stringResource(R.string.third_instruction)
        else -> stringResource(R.string.fourth_instruction)
    }

    val squeezeLemon = {
        clickedNumber++
        if(clickedNumber == numOfSqueezing) {
            phaseNum = 3
            numOfSqueezing = (2..4).random()
            clickedNumber = 0
        }
    }

    val nextPhase= {currentPhase: Int -> when(currentPhase){
            1 -> phaseNum = 2
            2 -> squeezeLemon()
            3 -> phaseNum = 4
            else -> phaseNum = 1
        }
    }


    MainLayout(
        imageSource,
        imageDescriotion,
        instructionText,
        phaseNum,
        modifier,
        nextPhase
    )
}

@Composable
fun MainLayout(
    imageSource: Painter,
    imageDescription: String,
    instructionText: String,
    currentPhase: Int,
    modifier: Modifier = Modifier,
    changePhase: (Int) -> Unit
){
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //A Box places its content at the top‑start by default.
        // Setting contentAlignment = Alignment.Center will place the Box's content at the horizontal center.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.2f)
                .background(Color(0xFFFFF44F))
        ) {
            Text(
                text = stringResource(R.string.page_title),
                //To move the content to the bottom inside a Box
                modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = Color(0x33ADFF2F),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .size(150.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = imageSource,
                    contentDescription = imageDescription,
                    modifier = Modifier
                        .size(80.dp)
                        .clickable{
                            changePhase(currentPhase)
                        }
                )
            }
            Spacer(Modifier.height(20.dp))
            Text(text = instructionText)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidoStudioPracticeTheme {
        MainAppView()
    }
}