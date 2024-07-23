package com.example.midterm_abel_serracin.ui.calculator


import android.content.Intent
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
import com.example.midterm_abel_serracin.R
import com.example.midterm_abel_serracin.databinding.FragmentCalculatorBinding


class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val num1: EditText = binding.num1
        val num2: EditText = binding.num2
        val btnCalculate: Button = binding.calculateGrade
        val result: TextView = binding.result
        var flag = "Sum"
        val spinnerVal: Spinner = binding.spinnerV
        val options = arrayOf("sum","subtract", "multiply", "divide")

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
            val x = num1.text.toString().toDoubleOrNull()
            val y = num2.text.toString().toDoubleOrNull()

            if (x != null && y != null) {
                when(flag) {
                    "sum" -> result.text = sum(x, y).toString()
                    "subtract" -> result.text = subtract(x, y).toString()
                    "multiply" -> result.text = multiply(x, y).toString()
                    "divide" -> result.text = divide(x, y).toString()
                }
            } else {
                result.text = "Please enter valid numbers"
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun sum(a: Double, b: Double): Double {
        return a + b
    }
    private fun subtract(a: Double, b: Double): Double {
        return a - b
    }
    private fun multiply(a: Double, b: Double): Double {
        return a * b
    }
    private fun divide(a: Double, b: Double): Double {
        return a / b
    }
}
