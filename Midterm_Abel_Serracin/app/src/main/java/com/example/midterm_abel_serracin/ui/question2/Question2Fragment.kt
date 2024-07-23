package com.example.midterm_abel_serracin.ui.question2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.midterm_abel_serracin.databinding.FragmentQuestion2Binding

class Question2Fragment : Fragment() {

    private var _binding: FragmentQuestion2Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentQuestion2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val gradeNumber: EditText = binding.num
        val btnCalculate: Button = binding.calculateGrade
        val gradeOutput: TextView = binding.result
        var flag = "Letter grade Calculator"
        val spinnerVal: Spinner = binding.pickGrading
        val options = arrayOf("Letter grade Calculator","Pass or Fail")

        spinnerVal.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerVal.adapter = adapter
            }

        spinnerVal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                flag = options[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }
        btnCalculate.setOnClickListener {
            val number = gradeNumber.text.toString().toIntOrNull()
            if (number != null) {
                if(flag=="Letter grade Calculator"){
                    val gradeLetter = getGradeLetter(number)
                    gradeOutput.text = "Grade: $gradeLetter"
                } else {
                    val result = passOrFail(number)
                    gradeOutput.text = "Result: $result"
                }
            } else {
                gradeOutput.text = "Please enter a valid number"
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun passOrFail(num: Int): String {
        return when {
            num >= 65 -> "Pass"
            else -> "Fail"
        }
    }
    private fun getGradeLetter(num: Int): String {
        return when {
            num >= 85 -> "A"
            num >= 75 -> "B"
            num >= 65 -> "C"
            num >= 50 -> "D"
            else -> "F"
        }
    }
}
