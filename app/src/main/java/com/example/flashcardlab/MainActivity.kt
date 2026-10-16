package com.example.flashcardlab

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.util.Log

class MainActivity : Activity() {
    private lateinit var question: TextView
    private lateinit var answer: TextView
    private lateinit var choices: LinearLayout
    private var answerShowing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        question = findViewById(R.id.flashcard_question)
        answer = findViewById(R.id.flashcard_answer)
        choices = findViewById(R.id.choices)
        val toggle: Button = findViewById(R.id.toggleChoices)

        question.setOnClickListener { showAnswer(true) }
        answer.setOnClickListener { showAnswer(false) }
        findViewById<TextView>(R.id.choice1).setOnClickListener { markChoice(it as TextView, false) }
        findViewById<TextView>(R.id.choice2).setOnClickListener { markChoice(it as TextView, false) }
        findViewById<TextView>(R.id.choice3).setOnClickListener { markChoice(it as TextView, true) }
        toggle.setOnClickListener {
            val visible = choices.visibility == View.VISIBLE
            choices.visibility = if (visible) View.GONE else View.VISIBLE
            toggle.text = if (visible) "◉ Show Choices" else "◉ Hide Choices"
            Log.d("FlashcardLab", "Choices visible: ${!visible}")
        }
        showAnswer(false)
    }

    private fun showAnswer(show: Boolean) {
        answerShowing = show
        question.visibility = if (show) View.INVISIBLE else View.VISIBLE
        answer.visibility = if (show) View.VISIBLE else View.INVISIBLE
        Log.d("FlashcardLab", "Answer showing: $answerShowing")
    }

    private fun markChoice(view: TextView, correct: Boolean) {
        view.setBackgroundColor(Color.parseColor(if (correct) "#A5E5B1" else "#FFB6B6"))
        Log.d("FlashcardLab", "Selected ${view.text}; correct=$correct")
    }
}
