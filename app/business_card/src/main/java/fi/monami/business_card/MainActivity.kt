package fi.monami.business_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fi.monami.business_card.ui.theme.AndroidoStudioPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    fi.monami.business_card.MainView(
                        painterResource(R.drawable.android_logo),
                        stringResource(R.string.full_name),
                        stringResource(R.string.job_title),
                        stringResource(R.string.phone_number),
                        stringResource(R.string.user_name),
                        stringResource(R.string.email_address)
                    )
                }
            }
        }
    }
}

@Composable
fun MainView(
    logoImage: Painter,
    name: String,
    jobTitle: String,
    phoneNumber: String,
    userName: String,
    email: String,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier.background(Color(0x33ADFF2F)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier.weight(2f),
            verticalArrangement = Arrangement.Center
        ) {
            Box (
                modifier = Modifier
                    .background(Color(0xFF001F3F))
                    .align(Alignment.CenterHorizontally)
            ){
                Image(
                    painter = logoImage,
                    contentDescription = "android logo image",
                    modifier = Modifier
                        .size(100.dp, 100.dp)
                )
            }
            Text(
                text = name,
                fontSize = 50.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)
            )
            Text(
                text = jobTitle,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF006400),
                modifier = Modifier.align(Alignment.CenterHorizontally)
                )
        }
        Column(
            modifier = Modifier.weight(0.5f).padding(bottom = 50.dp),
            verticalArrangement = Arrangement.Bottom
        ){
            Row() {
                Icon(
                    Icons.Filled.Phone,
                    contentDescription = "Phone",
                    tint = Color(0xFF006400)
                )
                Text(
                    text = phoneNumber,
                    modifier = Modifier.padding(start = 10.dp)
                    )
            }
            Row(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
                Icon(
                    Icons.Filled.Share,
                    contentDescription = "Share",
                    tint = Color(0xFF006400)
                )
                Text(
                    text = userName,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Row() {
                Icon(
                    Icons.Filled.Email,
                    contentDescription = "Email",
                    tint = Color(0xFF006400)
                )
                Text(
                    text = email,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidoStudioPracticeTheme {

    }
}