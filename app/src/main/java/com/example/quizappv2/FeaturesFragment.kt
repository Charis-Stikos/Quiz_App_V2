package com.example.quizappv2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FeaturesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_process, container, false)


        val textView = view.findViewById<TextView>(R.id.tiganelaio_text)
        textView.text = getString(R.string.tiganelaio_info)

        return view
    }
}
