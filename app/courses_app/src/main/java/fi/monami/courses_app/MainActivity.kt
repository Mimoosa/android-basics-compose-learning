package fi.monami.courses_app

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.aspectRatio

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.width

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fi.monami.cources_app.R
import fi.monami.courses_app.data.DataSource
import fi.monami.courses_app.model.Topic
import fi.monami.courses_app.ui.theme.AndroidoStudioPracticeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidoStudioPracticeTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            modifier = Modifier.height(48.dp),
                            title={},
                            colors = TopAppBarDefaults.largeTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }

                ) { innerPadding->
                    CoursesApp(
                        Modifier
                            .padding(innerPadding)
                            .padding(
                                start = dimensionResource(R.dimen.padding_small),
                                top = dimensionResource(R.dimen.padding_small),
                                end = dimensionResource(R.dimen.padding_small),
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun CoursesApp(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
    ) {
        CourseGrid(DataSource.topics)
    }
}

@Composable
fun CourseGrid(topics: List<Topic>, modifier: Modifier = Modifier){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(topics){ topic ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                CourseCard(
                    topic
                )
            }

        }
    }
}

@Composable
fun CourseCard(topic: Topic, modifier: Modifier = Modifier){
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF2F2F2)
        )
        ) {
        Row(modifier=Modifier) {
            Image(
                painter = painterResource(topic.image),
                contentDescription = stringResource(topic.name),
                modifier = Modifier
                    .size(68.dp)
                    .aspectRatio(1f)
            )
            Column(
                modifier=Modifier
                    .padding(horizontal = dimensionResource(R.dimen.padding_medium))
                    .height(68.dp),
                ) {
                Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Text(
                    text = stringResource(topic.name),
                    style = MaterialTheme.typography.bodyMedium
                    )
                Spacer(Modifier.height(dimensionResource(R.dimen.padding_small)))
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_grain_24),
                        contentDescription = null
                    )
                    Spacer(Modifier.width(dimensionResource(R.dimen.padding_small)))
                    Text(
                        text = topic.associatedNum.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidoStudioPracticeTheme {
        CourseCard(DataSource.topics[0])
    }
}