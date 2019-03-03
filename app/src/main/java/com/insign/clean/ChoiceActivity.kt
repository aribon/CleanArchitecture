package com.insign.clean

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.util.TypedValue
import android.widget.Button

class ChoiceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)
        addButton("Simple List", View.OnClickListener {
            startActivity(Intent(baseContext, ListActivity::class.java))
        })
        addButton("Multiple Type List", View.OnClickListener {
            startActivity(Intent(baseContext, MultipleTypeListActivity::class.java))
        })
    }

    private fun addButton(label: String, action: View.OnClickListener) {
        val parent = findViewById<ViewGroup>(R.id.view_choice)
        val button = Button(baseContext)

        val layoutParams = ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

        val margin = dpToPx(8F)
        layoutParams.setMargins(margin, margin, margin, margin)

        button.layoutParams = layoutParams

        button.text = label
        button.setOnClickListener(action)

        parent.addView(button)
    }

    private fun dpToPx(dpValue: Float) : Int {
        val r = baseContext.resources
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                r.displayMetrics).toInt()
    }
}
