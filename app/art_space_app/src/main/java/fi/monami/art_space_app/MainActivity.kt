package fi.monami.art_space_app

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.monami.art_space_app.ui.theme.AndroidoStudioPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                MainLayout()
            }
        }
    }
}

@Composable
fun MainLayout(modifier: Modifier = Modifier){
    var currentPageNum by remember { mutableStateOf(0) }

    val artworks = listOf(
        Triple("Lion Photo",  R.drawable.lion_photo, R.string.xandro_vandewalle),
        Triple("Goose Photo", R.drawable.goose_photo, R.string.adam_bouse),
        Triple("Giraph Photo",R.drawable.giraph_photo, R.string.xandro_vandewalle),
        Triple("Reindeer Photo", R.drawable.raindeer_photo,R.string.wolfgang_hasselmann)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .safeDrawingPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier.weight(2f),
            verticalArrangement = Arrangement.Center
        ) {
            PhotoImage(
                artworks[currentPageNum].second,
                artworks[currentPageNum].first
            )
        }

        Column(
            modifier = Modifier.weight(1f).widthIn(max = 400.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DescriptionCard(
                artworks[currentPageNum].first,
                artworks[currentPageNum].third
            )
            Spacer(Modifier.height(20.dp))
            Buttons(
                {
                    if(currentPageNum == 0)
                        currentPageNum = artworks.size - 1
                    else
                        currentPageNum = (currentPageNum - 1)
                },
                {
                    currentPageNum = (currentPageNum+1) % artworks.size
                }
            )
        }
    }
}

@Composable
fun PhotoImage(
    @DrawableRes photo: Int,
    title: String,
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier.heightIn(max = 500.dp),
        shadowElevation = 20.dp,
        color = Color(0xFFEEEEEE)
    ) {
        Image(
            painter = painterResource(photo),
            contentDescription = title,
            modifier = Modifier.padding(20.dp)
        )

    }
}

@Composable
fun DescriptionCard(
    title: String,
    @StringRes photographer: Int,
    modifier: Modifier = Modifier
){
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .safeDrawingPadding(),
        color = Color(0xFFE0E0E0)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                fontSize = 30.sp
            )
            Spacer(Modifier.height(30.dp))
            Text(
                text = buildAnnotatedString {
                    append("Photo taken by \n")

                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )) {
                        append(stringResource(photographer))
                    }
                },
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun Buttons(
    previousOnClick: ()-> Unit,
    nextOnClick: ()-> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Button(
            previousOnClick
        ) {
            Text(
                text = stringResource(R.string.previous_button_label),
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.Center
            )
        }
        Button(
            nextOnClick
            ) {
            Text(
                text = stringResource(R.string.next_button_label),
                modifier = Modifier.width(80.dp),
                textAlign = TextAlign.Center
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