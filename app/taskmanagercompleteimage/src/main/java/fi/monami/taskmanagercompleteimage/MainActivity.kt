package fi.monami.taskmanagercompleteimage


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.monami.taskmanagercompleteimage.ui.theme.AndroidoStudioPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    CompleteImage(
                        painterResource(R.drawable.ic_task_completed),
                        stringResource(R.string.completed_message),
                        stringResource(R.string.feedback_message)
                    )
                }
            }
        }
    }
}

@Composable
fun CompleteImage(image: Painter, completedMessage: String, feedbackMessage: String, modifier: Modifier = Modifier){
    Column(
        modifier=modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = image, contentDescription = "Completed icon")
        Text(
            text = completedMessage,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp),
            fontWeight = FontWeight.Bold
            )
        Text(
            text = feedbackMessage,
            fontSize = 16.sp
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CompleteImagePreview() {
    AndroidoStudioPracticeTheme {
        CompleteImage(
            painterResource(R.drawable.ic_task_completed),
            stringResource(R.string.completed_message),
            stringResource(R.string.feedback_message)
            )
    }
}