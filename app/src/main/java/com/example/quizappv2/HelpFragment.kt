package com.example.quizappv2

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class HelpFragment : Fragment() {

    private lateinit var nameEditText: TextInputEditText
    private lateinit var surnameEditText: TextInputEditText
    private lateinit var emailOrMobileSpinner: Spinner
    private lateinit var contactInfoEditText: TextInputEditText
    private lateinit var contactInfoLayout: TextInputLayout
    private lateinit var problemDescriptionEditText: TextInputEditText
    private lateinit var submitButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_help, container, false)

        // Initialize views
        nameEditText = view.findViewById(R.id.editTextName)
        surnameEditText = view.findViewById(R.id.editTextSurname)
        emailOrMobileSpinner = view.findViewById(R.id.spinnerEmailOrMobile)
        contactInfoEditText = view.findViewById(R.id.editTextContactInfo)
        contactInfoLayout = view.findViewById(R.id.contactInfoLayout)
        problemDescriptionEditText = view.findViewById(R.id.editTextProblemDescription)
        submitButton = view.findViewById(R.id.buttonSubmit)

        // Set up the spinner for email or mobile
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.email_mobile_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            emailOrMobileSpinner.adapter = adapter
        }


        emailOrMobileSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> { // Email selected
                        contactInfoEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                        contactInfoEditText.hint = "Email"
                    }
                    1 -> { // Mobile selected
                        contactInfoEditText.inputType = InputType.TYPE_CLASS_PHONE
                        contactInfoEditText.hint = "Mobile Number"
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        submitButton.setOnClickListener {
            val name = nameEditText.text.toString().trim()
            val surname = surnameEditText.text.toString().trim()
            val contactInfo = contactInfoEditText.text.toString().trim()
            val problemDescription = problemDescriptionEditText.text.toString().trim()

            if (name.isEmpty() || surname.isEmpty() || contactInfo.isEmpty() || problemDescription.isEmpty()) {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(requireContext(), "Form submitted successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}