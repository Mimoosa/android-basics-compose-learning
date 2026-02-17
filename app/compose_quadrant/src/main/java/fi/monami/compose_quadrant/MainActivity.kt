package fi.monami.compose_quadrant

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fi.monami.compose_quadrant.ui.theme.AndroidoStudioPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    val textDescriptionPairs = mapOf(
                        stringResource(R.string.text_composable_title) to stringResource(R.string.text_composable_description),
                        stringResource(R.string.image_composable_title) to stringResource(R.string.image_composable_description),
                        stringResource(R.string.row_composable_title) to stringResource(R.string.row_composable_description),
                        stringResource(R.string.column_composable_title) to stringResource(R.string.column_composable_description)
                    )
                    MainView(textDescriptionPairs)
                }
            }
        }
    }
}

@Composable
fun MainView(textMap: Map<String, String>, modifier: Modifier = Modifier){
    val keys = textMap.keys.toList()
    val values = textMap.values.toList()
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
           DescriptionCard(keys, values, Modifier.weight(1f), 0, 0xFFEADDFF)
            DescriptionCard(keys, values, Modifier.weight(1f), 1, 0xFFD0BCFF)
        }
        Row(
            modifier = Modifier
                .weight(1f)
        ){
            DescriptionCard(keys, values, Modifier.weight(1f), 2, 0xFFB69DF8)
            DescriptionCard(keys, values, Modifier.weight(1f), 3, 0xFFF6EDFF)
        }
    }
}

@Composable
fun DescriptionCard(keys: List<String>, values: List<String>, modifier: Modifier, index: Int, color: Long){
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(color))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )  {
        Text(
            text = keys[index],
            fontWeight = FontWeight.Bold
        )
        Text(
            text = values[index],
            textAlign = TextAlign.Justify
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}