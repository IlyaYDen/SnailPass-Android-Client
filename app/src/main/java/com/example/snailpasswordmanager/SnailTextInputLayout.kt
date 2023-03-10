package com.example.snailpasswordmanager
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.internal.CollapsingTextHelper
import com.google.android.material.textfield.TextInputLayout

//todo make SnailTextInputLayout 09
class SnailTextInputLayout (context: Context, attrs: AttributeSet?) : TextInputLayout(context, attrs) {
    private var passwordGeneratorButton: Button? = null
    private var attrs: AttributeSet? = null


    init {
        if (attrs != null) {

            val a = context.theme.obtainStyledAttributes(attrs, R.styleable.TextInputLayout, 0, 0)
            if (a.getBoolean(R.styleable.TextInputLayout_passwordGeneratorToggleEnabled, false)) {
                // create the new button and add it to the TextInputLayout
                passwordGeneratorButton = Button(context)


                passwordGeneratorButton?.text = "Generate Password"
                passwordGeneratorButton?.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.holo_green_light
                    )
                )
                passwordGeneratorButton?.setTextColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.white
                    )
                )

                //this.removeAllViews()

            }
            a.recycle()
        }
    }


    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        // create the ImageView and set its properties

        val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        lp.height =10
        passwordGeneratorButton?.layoutParams = lp
        //passwordGeneratorButton!!.gravity =
        //    Gravity.TOP or (passwordGeneratorButton!!.gravity and Gravity.VERTICAL_GRAVITY_MASK.inv())

        addView(passwordGeneratorButton)
    }
    // helper method to generate a new password
    private fun generatePassword(): String {
        // add your password generation logic here
        return "123456"
    }
}
