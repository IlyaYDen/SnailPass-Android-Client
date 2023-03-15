package com.example.snailpasswordmanager.presentation.accountInfo

import android.graphics.Typeface
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.snailpasswordmanager.R
import com.example.snailpasswordmanager.domain.model.RecordAddFieldEntity
import com.example.snailpasswordmanager.domain.model.RecordEntity
import kotlin.random.Random

class AccountInfoAdapter : RecyclerView.Adapter<AccountInfoAdapter.AccountItemViewHolder>() {


    //var list = listOf<RecordInfoEntity>()
    var textboxEditable: Boolean = true
    var list = mutableListOf<RecordAddFieldEntity>()
        set(value) {
            textboxEditable = false
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
            textboxEditable = true
        }

    fun addList(value: RecordAddFieldEntity){
        textboxEditable = false
        list.add(value)
        notifyDataSetChanged()
        textboxEditable = true
    }
    fun addListItemPos(position: Int, value: RecordAddFieldEntity){
        textboxEditable = false
        list.add(position,value)
        notifyDataSetChanged()
        textboxEditable = true
    }
    fun addList(value: List<RecordAddFieldEntity>){
        textboxEditable = false
        list.addAll(value)
        notifyDataSetChanged()
        textboxEditable = true
    }

    private var lastDeletedItem: Pair<RecordAddFieldEntity, Int>? = null
    fun deleteItem(position: Int) {
        lastDeletedItem = Pair(list[position], position)
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    fun undoDelete() {
        lastDeletedItem?.let {
            list.add(it.second, it.first)
            notifyItemInserted(it.second)
            lastDeletedItem = null
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountItemViewHolder {
        val view =
        if(viewType==0)
            LayoutInflater.from(parent.context).inflate(R.layout.edit_record_info_list_item, parent, false)
        else if(viewType==1)
            LayoutInflater.from(parent.context).inflate(R.layout.record_info_list_item, parent, false)
        else {
            LayoutInflater.from(parent.context)
                .inflate(R.layout.record_info_list_item_password, parent, false)

        }


        return AccountItemViewHolder(view)
    }
/*
    override fun onBindViewHolder(holder: AccountItemViewHolder, position: Int) {

        val accountItem = list[position]
        holder.key.text = accountItem.name
        holder.value.text = accountItem.value
    }*/

    private var showPasswordEnable : Boolean = false
    private var showGenerator : Boolean = false


    override fun onBindViewHolder(holder: AccountItemViewHolder, position: Int) {
        val accountItem = list[position]

        if(position==2) {
            val generatorOpen: ImageButton
            val passwordLength: EditText
            val checkBoxLettersSmall: CheckBox
            val checkBoxLettersCap: CheckBox
            val checkBoxNumbers: CheckBox
            val checkBoxSpec: CheckBox
            val generateButton: Button
            val genLayout: LinearLayout = holder.view.findViewById(R.id.generation_space)
            genLayout.visibility = GONE

            generatorOpen = holder.view.findViewById(R.id.generatorOpen)
            passwordLength= holder.view.findViewById(R.id.editTextPasswordLength)
            checkBoxLettersSmall = holder.view.findViewById(R.id.checkBoxa_z)
            checkBoxLettersCap = holder.view.findViewById(R.id.checkBoxA_Z)
            checkBoxNumbers = holder.view.findViewById(R.id.checkBoxNumbers)
            checkBoxSpec = holder.view.findViewById(R.id.checkSpecificSymbols)
            generateButton = holder.view.findViewById(R.id.generate_password)
            val showPassword : ImageButton = holder.view.findViewById(R.id.showPassword)

            generateButton.setOnClickListener {
                //val s = generatePassword(passwordLength,checkBoxLettersSmall,checkBoxLettersCap,
                //    checkBoxNumbers,checkBoxSpec)
                if (passwordLength.text.toString() != "" && passwordLength.text.toString().toInt()>4) {
                    passwordLength.setText(
                        if(passwordLength.text.toString().toInt() > 4) passwordLength.text.toString()
                        else "4")
                    val s = generate(passwordLength.text.toString().toInt(),
                        checkBoxLettersSmall.isChecked,
                        checkBoxLettersCap.isChecked,
                        checkBoxNumbers.isChecked,
                        checkBoxSpec.isChecked)
                    if (s != "")
                        holder.value.text = s
                }
            }

            showPassword.setOnClickListener {
                if(showPasswordEnable) {
                    showPassword.setImageResource(R.drawable.baseline_remove_red_eye_24)
                    holder.value?.inputType = 0x00000081
                }
                else{
                    showPassword.setImageResource(R.drawable.baseline_visibility_off_24)
                    holder.value?.inputType = InputType.TYPE_CLASS_TEXT
                }
                showPasswordEnable = !showPasswordEnable
                holder.value?.typeface = Typeface.DEFAULT
            }

            generatorOpen.setOnClickListener {
                showGenerator = !showGenerator
                if (showGenerator) {
                    generatorOpen.setImageResource(R.drawable.baseline_keyboard_arrow_up_24)
                    genLayout.visibility = VISIBLE

                } else {
                    generatorOpen.setImageResource(R.drawable.baseline_keyboard_arrow_down_24)
                    genLayout.visibility = GONE

                }

            }
        }

        holder.key.text = accountItem.name
        holder.key.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                accountItem.name = s.toString()
            }
        })
        holder.value.text = accountItem.value
        holder.value.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                accountItem.value = s.toString()
            }
        })
    }
        private val lowercaseAlphabet = "abcdefghijklmnopqrstuvwxyz"
        private val uppercaseAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        private val digitAlphabet = "0123456789"
        private val specialAlphabet = "~!@#$%^&*()_-+={}[]\\|:;\"'<>,.?/"

        fun generate(length: Int, isLowercase: Boolean, isUppercase: Boolean, isDigits: Boolean, isSpecials: Boolean): String {
            if ((!isLowercase && !isUppercase && !isDigits && !isSpecials) || length == 0) {
                return ""
            }

            val composedAlphabet = StringBuilder()
            val password = CharArray(length)
            val filledPositions = mutableListOf<Int>()

            if (isLowercase) {
                setRandomChar(filledPositions, password, lowercaseAlphabet)
                composedAlphabet.append(lowercaseAlphabet)
            }

            if (isUppercase) {
                setRandomChar(filledPositions, password, uppercaseAlphabet)
                composedAlphabet.append(uppercaseAlphabet)
            }

            if (isDigits) {
                setRandomChar(filledPositions, password, digitAlphabet)
                composedAlphabet.append(digitAlphabet)
            }

            if (isSpecials) {
                setRandomChar(filledPositions, password, specialAlphabet)
                composedAlphabet.append(specialAlphabet)
            }

            while (filledPositions.size < length) {
                setRandomChar(filledPositions, password, composedAlphabet.toString())
            }

            return String(password)
        }

        private fun setRandomChar(filledPositions: MutableList<Int>, password: CharArray, alphabet: String) {
            val passwordLength = password.size
            val alphabetLength = alphabet.length
            val symbol = alphabet[Random.nextInt(alphabetLength)]
            var position = Random.nextInt(passwordLength)

            while (filledPositions.contains(position)) {
                position++
                if (position >= passwordLength) {
                    position = 0
                }
            }

            filledPositions.add(position)
            password[position] = symbol
        }



    override fun getItemCount(): Int {
        return list.size
    }


    override fun getItemViewType(position: Int): Int {

        return if (position>2) 0 else if(position == 2) 2 else 1//super.getItemViewType(position)
    }
    class AccountItemViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val key = view.findViewById<TextView>(R.id.textView1)
        val value = view.findViewById<TextView>(R.id.textView2)
        //val valueView = view.findViewById<TextInputLayout>(R.id.username_text_input_layout)
    }



}



