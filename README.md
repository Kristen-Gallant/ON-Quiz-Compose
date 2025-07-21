# ON Quiz - A Modern Jetpack Compose Quiz App

## ANDROID APP MADE IN COMPOSE

# ON Quiz is a sleek, modern quiz application built entirely with Jetpack Compose and Android's MVVM architecture. It features a category-based structure, timer-bound questions, a result summary, and stateful management using ViewModel, StateFlow, and LaunchedEffect.

# Features
* 15 Quiz Topics — Ranging from Math to Tech.
* 15-minute Timer — Countdowns per quiz session.
* Randomized Questions — Each playthrough is fresh.
* Answer Review — Know what you got right or wrong.
* MVVM Architecture — Clean and scalable.
* Jetpack Compose — 100% UI built using Compose.
* Navigation Component — Smooth screen transitions.

# Tech Stack
* UI: Jetpack Compose, Material 3
* State management : ViewModel, MutableState, StateFlow
* Navigation: AndroidX Navigation-Compose
* Timing : 	Kotlin CountDownTimer
* Build Tools: Gradle (Kotlin DSL + TOML catalog)
* Language : Kotlin

* ## Demo Video

  # APP STRUCTURE
  kristengithubapps.onquiz/
├── MainActivity.kt
├── ui/
│   ├── HomeScreen.kt
│   ├── QuizScreen.kt
│   ├── ResultScreen.kt
├── viewmodel/
│   └── QuizViewModel.kt
├── model/
│   ├── QuizQuestion.kt
│   └── ResultItem.kt
└── data/
    └── QuizDataProvider.kt

# How It Works
* # Home Screen
* Lists 15 quiz categories.

* On selection, navigates to quiz with shuffled questions.

# Quiz Screen
* Displays one question at a time.

* Tracks selected answers and time left.

* Auto-submits when timer runs out.

# Result Screen
* Shows correct vs selected answers.

* Lets user restart or go home.

# 💡 My Notes
* Built with scalability in mind: easy to plug in a backend, Firebase, or Room.

* The questions are hardcoded for now but can be swapped with dynamic sources.

* Fully modular: you can break it into features if needed.


<a href="https://youtu.be/7AA6ojcTGls">
  <img src="https://img.youtube.com/vi/7AA6ojcTGls/maxresdefault.jpg" alt="Video Title" width="500" />
</a>

<img src="https://github.com/Kristen-Gallant/ON-Quiz-Compose/blob/master/quizfirst.png" alt="Alt Text" width="300" />







