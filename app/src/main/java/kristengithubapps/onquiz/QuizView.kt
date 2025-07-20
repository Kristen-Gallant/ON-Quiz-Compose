package kristengithubapps.onquiz

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class QuizViewModel : ViewModel() {

    val quizData: Map<String, List<QuizQuestion>> = mapOf(

        "Mathematics" to listOf(
            QuizQuestion("math1", "What is 8 + 5?", listOf("11", "12", "13", "14"), "13"),
            QuizQuestion("math2", "What is half of 20?", listOf("5", "10", "15", "20"), "10"),
            QuizQuestion("math3", "Which shape has 3 sides?", listOf("Square", "Triangle", "Circle", "Rectangle"), "Triangle"),
            QuizQuestion("math4", "What is 7 x 6?", listOf("42", "36", "48", "56"), "42"),
            QuizQuestion("math5", "What comes after 99?", listOf("100", "101", "98", "90"), "100"),
            QuizQuestion("math6", "What is 30 divided by 5?", listOf("5", "6", "7", "8"), "6"),
            QuizQuestion("math7", "What shape is a football?", listOf("Circle", "Oval", "Rectangle", "Square"), "Oval"),
            QuizQuestion("math8", "What is the sum of angles in a triangle?", listOf("180°", "360°", "90°", "270°"), "180°"),
            QuizQuestion("math9", "What is 10% of 200?", listOf("10", "20", "25", "30"), "20"),
            QuizQuestion("math10", "Which number is a multiple of 4?", listOf("9", "12", "15", "17"), "12"),
            QuizQuestion("math11", "What is 3 squared?", listOf("6", "9", "12", "27"), "9"),
            QuizQuestion("math12", "What is the place value of 5 in 258?", listOf("Units", "Tens", "Hundreds", "Thousands"), "Tens"),
            QuizQuestion("math13", "Which is larger: 3/4 or 2/3?", listOf("3/4", "2/3", "Equal", "Can't tell"), "3/4"),
            QuizQuestion("math14", "How many sides does a hexagon have?", listOf("5", "6", "7", "8"), "6"),
            QuizQuestion("math15", "What is 1 more than a dozen?", listOf("11", "12", "13", "14"), "13")
        ),

        "English Language" to listOf(
            QuizQuestion("eng1", "What is the opposite of 'happy'?", listOf("sad", "angry", "glad", "excited"), "sad"),
            QuizQuestion("eng2", "Choose the correctly spelled word.", listOf("definately", "definitely", "defanitely", "definately"), "definitely"),
            QuizQuestion("eng3", "What type of word is 'quickly'?", listOf("Noun", "Adjective", "Adverb", "Verb"), "Adverb"),
            QuizQuestion("eng4", "Which word is a noun?", listOf("run", "blue", "happiness", "quickly"), "happiness"),
            QuizQuestion("eng5", "What is a sentence?", listOf("A letter", "A full stop", "A complete thought", "A noun"), "A complete thought"),
            QuizQuestion("eng6", "Plural of 'child' is?", listOf("childs", "children", "childes", "childer"), "children"),
            QuizQuestion("eng7", "Which is a verb?", listOf("eat", "table", "slow", "yellow"), "eat"),
            QuizQuestion("eng8", "Synonym of 'big'?", listOf("small", "tiny", "huge", "short"), "huge"),
            QuizQuestion("eng9", "What punctuation ends a question?", listOf(".", "!", ",", "?"), "?"),
            QuizQuestion("eng10", "Choose the adjective.", listOf("beautiful", "beauty", "beautify", "be"), "beautiful"),
            QuizQuestion("eng11", "Antonym of 'strong'?", listOf("powerful", "brave", "weak", "hard"), "weak"),
            QuizQuestion("eng12", "What is a pronoun?", listOf("Replaces noun", "Describes noun", "Action word", "Joining word"), "Replaces noun"),
            QuizQuestion("eng13", "Which is a compound word?", listOf("sunshine", "sun", "shine", "bright"), "sunshine"),
            QuizQuestion("eng14", "Which is a question word?", listOf("Blue", "When", "Quickly", "Cake"), "When"),
            QuizQuestion("eng15", "Choose the correct sentence.", listOf("He are a boy", "He is a boy", "He be a boy", "He am a boy"), "He is a boy")
        ),

        "Basic Science" to listOf(
            QuizQuestion("sci1", "Which part of the plant makes food?", listOf("Root", "Stem", "Flower", "Leaf"), "Leaf"),
            QuizQuestion("sci2", "Humans breathe using?", listOf("Heart", "Lungs", "Kidney", "Liver"), "Lungs"),
            QuizQuestion("sci3", "Water turns into what when it boils?", listOf("Ice", "Smoke", "Steam", "Cloud"), "Steam"),
            QuizQuestion("sci4", "Which sense organ do we use to see?", listOf("Ear", "Eye", "Nose", "Skin"), "Eye"),
            QuizQuestion("sci5", "Which is a source of light?", listOf("Moon", "Sun", "Mirror", "Cloud"), "Sun"),
            QuizQuestion("sci6", "What do plants need to grow?", listOf("Sand", "Light", "Plastic", "Stone"), "Light"),
            QuizQuestion("sci7", "What is a solid?", listOf("Water", "Air", "Stone", "Steam"), "Stone"),
            QuizQuestion("sci8", "What causes rain?", listOf("Condensation", "Evaporation", "Photosynthesis", "Friction"), "Condensation"),
            QuizQuestion("sci9", "Which is a gas?", listOf("Ice", "Water", "Air", "Stone"), "Air"),
            QuizQuestion("sci10", "Which is a living thing?", listOf("Book", "Dog", "Spoon", "Chair"), "Dog"),
            QuizQuestion("sci11", "Which is used to measure temperature?", listOf("Thermometer", "Barometer", "Scale", "Microscope"), "Thermometer"),
            QuizQuestion("sci12", "Which part of the body pumps blood?", listOf("Lungs", "Heart", "Brain", "Liver"), "Heart"),
            QuizQuestion("sci13", "What do we use to hear?", listOf("Mouth", "Eyes", "Ears", "Nose"), "Ears"),
            QuizQuestion("sci14", "Which planet do we live on?", listOf("Mars", "Venus", "Earth", "Jupiter"), "Earth"),
            QuizQuestion("sci15", "Which is a type of energy?", listOf("Sound", "Leaf", "Salt", "Chair"), "Sound")
        ),

        "Social Studies" to listOf(
            QuizQuestion("soc1", "What is the smallest unit of society?", listOf("Village", "Family", "Tribe", "Community"), "Family"),
            QuizQuestion("soc2", "What do we call a group of people living together?", listOf("Country", "Community", "Union", "Party"), "Community"),
            QuizQuestion("soc3", "What is a good citizen?", listOf("Lazy", "Honest", "Rude", "Liar"), "Honest"),
            QuizQuestion("soc4", "Which is a means of transportation?", listOf("Tree", "Car", "Pen", "Shoe"), "Car"),
            QuizQuestion("soc5", "Nigeria is located in which continent?", listOf("Asia", "Europe", "Africa", "Australia"), "Africa"),
            QuizQuestion("soc6", "What do we use to vote?", listOf("Stick", "Ballot paper", "Broom", "Stone"), "Ballot paper"),
            QuizQuestion("soc7", "Leader of a school is called?", listOf("Manager", "Principal", "Captain", "Coach"), "Principal"),
            QuizQuestion("soc8", "Which is a natural resource?", listOf("Gold", "Plastic", "Paper", "Chair"), "Gold"),
            QuizQuestion("soc9", "National flag has how many colors?", listOf("1", "2", "3", "4"), "2"), // Green and white
            QuizQuestion("soc10", "What is culture?", listOf("Food", "Way of life", "Dance", "Language"), "Way of life"),
            QuizQuestion("soc11", "What is traffic light used for?", listOf("Eating", "Singing", "Road control", "Dancing"), "Road control"),
            QuizQuestion("soc12", "What color means 'Stop'?", listOf("Green", "Yellow", "Blue", "Red"), "Red"),
            QuizQuestion("soc13", "What is the capital of Nigeria?", listOf("Lagos", "Abuja", "Kano", "Enugu"), "Abuja"),
            QuizQuestion("soc14", "What is the color of the Nigerian flag?", listOf("Green and white", "Blue and white", "Red and white", "Yellow and green"), "Green and white"),
            QuizQuestion("soc15", "How many local governments in Nigeria?", listOf("36", "774", "100", "200"), "774")
        ),

        "Computer Studies" to listOf(
            QuizQuestion("cs1", "Which device is used to type text?", listOf("Monitor", "Mouse", "Keyboard", "Printer"), "Keyboard"),
            QuizQuestion("cs2", "What does CPU stand for?", listOf("Control Unit", "Core Unit", "Central Processing Unit", "Central Program Utility"), "Central Processing Unit"),
            QuizQuestion("cs3", "Which device displays output?", listOf("Monitor", "Mouse", "Keyboard", "Speaker"), "Monitor"),
            QuizQuestion("cs4", "Which stores long-term data?", listOf("RAM", "ROM", "Hard Disk", "Cache"), "Hard Disk"),
            QuizQuestion("cs5", "What is used to move the cursor?", listOf("Keyboard", "Mouse", "Monitor", "Speaker"), "Mouse"),
            QuizQuestion("cs6", "Which is a programming language?", listOf("Java", "Email", "Google", "Wi-Fi"), "Java"),
            QuizQuestion("cs7", "What is the internet?", listOf("Website", "Cable", "Global network", "App"), "Global network"),
            QuizQuestion("cs8", "Which is an input device?", listOf("Printer", "Scanner", "Speaker", "Monitor"), "Scanner"),
            QuizQuestion("cs9", "Which is an operating system?", listOf("Google", "Facebook", "Windows", "YouTube"), "Windows"),
            QuizQuestion("cs10", "What does a browser do?", listOf("Plays music", "Surfs internet", "Prints document", "Scans photo"), "Surfs internet"),
            QuizQuestion("cs11", "Which part holds all components?", listOf("CPU", "Motherboard", "Mouse", "Drive"), "Motherboard"),
            QuizQuestion("cs12", "What is used to store data?", listOf("Pen", "CD", "Paper", "Screen"), "CD"),
            QuizQuestion("cs13", "Full meaning of ICT?", listOf("Information and Communication Technology", "Internet Communication Tool", "Input Control Tab", "Information Cable Transfer"), "Information and Communication Technology"),
            QuizQuestion("cs14", "What is a file?", listOf("Paper sheet", "Program", "Document saved in storage", "Icon"), "Document saved in storage"),
            QuizQuestion("cs15", "Which one is NOT a computer?", listOf("Laptop", "Smartphone", "Microwave", "Tablet"), "Microwave")
        )
    )

    val pass = mutableStateOf("Mathematics")

    private var _questions = mutableStateListOf<QuizQuestion>()
    val questions: List<QuizQuestion> get() = _questions

    var currentIndex by mutableIntStateOf(0)
    var selectedAnswers = mutableStateMapOf<String, String?>()
    var timeLeft by mutableIntStateOf(15 * 60) // 15 minutes

    init {
        setCategory(pass.value)
        startTimer()
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }
        }
    }

    fun setCategory(category: String) {
        pass.value = category
        _questions.clear()
        _questions.addAll(quizData[category]?.shuffled() ?: emptyList())
        currentIndex = 0
        selectedAnswers.clear()
    }

    fun getCurrentQuestion(): QuizQuestion? = _questions.getOrNull(currentIndex)

    fun isLastQuestion(): Boolean = currentIndex == _questions.lastIndex

    fun goToNext() {
        if (!isLastQuestion()) currentIndex++
    }

    fun recordAnswer(questionId: String, answer: String) {
        selectedAnswers[questionId] = answer
    }

    fun getResults(): List<ResultItem> {
        return _questions.map {
            ResultItem(
                question = it.question,
                selected = selectedAnswers[it.id],
                correct = it.correctAnswer
            )
        }
    }
}




@Composable
fun QuizScreen(
    navController: NavController,
    topic: String,
    viewModel: QuizViewModel = viewModel()
) {
    LaunchedEffect(topic) {
        viewModel.setCategory(topic)
    }

    val question = viewModel.getCurrentQuestion()
    val selectedOption = viewModel.selectedAnswers[question?.id]
    val minutes = viewModel.timeLeft / 60
    val seconds = viewModel.timeLeft % 60



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Time Remaining: %02d:%02d".format(minutes, seconds),
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text("Question ${viewModel.currentIndex + 1} of 15", fontWeight = FontWeight.Bold)

        Spacer(Modifier.height(16.dp))

        question?.question?.let {
            Text(
                it,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        }

        question?.options?.forEach { option ->
            Button(
                onClick = { viewModel.recordAnswer(question.id, option) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == option) Color(0xFFB9F6CA) else Color(0xFFDEECFF),
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(option)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (viewModel.isLastQuestion()) {
                    navController.navigate("result")
                } else {
                    viewModel.goToNext()
                }
            },
            enabled = selectedOption != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(30.dp)
        ) {
            Text(if (viewModel.isLastQuestion()) "Submit Quiz" else "Next Question")
        }
    }
}



@Composable
fun ResultScreen(viewModel: QuizViewModel = viewModel()) {
    val results = viewModel.getResults()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text("Quiz Results", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))

        results?.forEachIndexed { index, result ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = if (result.selected == result.correct) Color(0xFFD0FFD6) else Color(0xFFFFCDD2)
                )
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Q${index + 1}: ${result.question}", fontWeight = FontWeight.Bold)
                    Text("Your Answer: ${result.selected ?: "No Answer"}")
                    if (result.selected != result.correct) {
                        Text("Correct Answer: ${result.correct}", color = Color.Red)
                    }
                }
            }
        }
    }
}



data class ResultItem(
    val question: String,
    val selected: String?,
    val correct: String
)

data class QuizQuestion(
    val id: String = "",
    val question: String,
    val options: List<String>,
    val correctAnswer: String
)
