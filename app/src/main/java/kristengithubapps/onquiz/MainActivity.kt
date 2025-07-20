package kristengithubapps.onquiz

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kristengithubapps.onquiz.ui.theme.ONQuizTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            ONQuizTheme {
                NavHost(
                    navController = navController,
                    startDestination = "home",
                ) {
                    composable("home") {
                        NotesHomeScreen(navController = navController)
                    }
                    composable(
                        route = "quizPage/{topic}",
                        arguments = listOf(navArgument("topic") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val topic = backStackEntry.arguments?.getString("topic") ?: "Mathematics"
                        QuizScreen(navController = navController, topic = topic)
                    }
                    composable("result"){
                        ResultScreen()
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesHomeScreen(modifier: Modifier = Modifier , navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("ON Quiz") },
            )
        }
    ) { innerPadding ->

        val quizTopics = listOf(
            QuizTopic(
                topic = "Mathematics",
                description = "Covers basic arithmetic, geometry, time, fractions, and problem-solving skills."
            ),
            QuizTopic(
                topic = "English Language",
                description = "Focuses on grammar, vocabulary, sentence structure, and communication skills."
            ),
            QuizTopic(
                topic = "Basic Science",
                description = "Introduces scientific concepts, living things, energy, weather, and the environment."
            ),
            QuizTopic(
                topic = "Social Studies",
                description = "Teaches culture, family, environment, government, and good citizenship."
            ),
            QuizTopic(
                topic = "Civic Education",
                description = "Promotes responsible citizenship, rights, duties, and national values."
            ),
            QuizTopic(
                topic = "Computer Studies",
                description = "Covers computer basics, parts of a computer, and how to use technology safely."
            ),
            QuizTopic(
                topic = "Health Education",
                description = "Focuses on personal hygiene, nutrition, safety, and healthy living habits."
            ),
            QuizTopic(
                topic = "Verbal Reasoning",
                description = "Enhances logical thinking using word patterns, analogies, and language puzzles."
            ),
            QuizTopic(
                topic = "Quantitative Reasoning",
                description = "Builds math logic using patterns, numbers, and basic calculations."
            ),
            QuizTopic(
                topic = "Reading Comprehension",
                description = "Improves understanding of passages, answering questions, and drawing conclusions."
            )
        )



        val colors = listOf(
            Color(0xFFE0F7FA), Color(0xFFFFF9C4), Color(0xFFE1BEE7),
            Color(0xFFD1C4E9), Color(0xFFFFCDD2), Color(0xFFC8E6C9)
        )




        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(quizTopics.size) { index ->
                val randomHeight = (100..200).random().dp
                val randomColor = colors.random()

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(randomHeight)
                        .clip(RoundedCornerShape(24.dp))
                        .background(randomColor)
                        .padding(16.dp)
                        .clickable {
                            navController.navigate("quizPage/${quizTopics[index].topic}")
                        }
                ) {
                    Column {
                        Text(
                            text = quizTopics[index].topic,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black,
                            fontWeight = FontWeight.Medium
                        )

                        Text(
                            modifier = Modifier.padding(top = 5.dp),
                            text = quizTopics[index].description,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            letterSpacing = (-0.9).sp,
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                    }

                }
            }
        }
    }
}

data class QuizTopic(
    val topic: String,
    val description: String
)