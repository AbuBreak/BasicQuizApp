package com.example.quizapp

import java.util.ArrayList

object Constant {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTION: String = "total_question"
    const val CORRECT_ANSWER: String = "correct_answer"

    fun getQuestion(): ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val que1 = Question(
            1,
            "Which planet is the closest to the sun?",
            R.drawable.solar_system,
            "Earth",
            "Mars",
            "Mercury",
            "Uranus",
            3
        )

        val que2 = Question(
            2,
            "What is the biggest animal in the world?",
            R.drawable.zoo,
            "Lion",
            "Elephant",
            "Giraffe",
            "Blue Whale",
            4
        )

        val que3 = Question(
            3,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait,
            "Jordan",
            "Palestine",
            "Kuwait",
            "Qatar",
            3
        )

        val que4 = Question(
            4,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany,
            "Belgium",
            "Germany",
            "USA",
            "France",
            2
        )

        val que5 = Question(
            5,
            "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil,
            "Brazil",
            "South Africa",
            "USA",
            "France",
            1
        )
        questionsList.add(que1)
        questionsList.add(que2)
        questionsList.add(que3)
        questionsList.add(que4)
        questionsList.add(que5)
        return questionsList
    }
}