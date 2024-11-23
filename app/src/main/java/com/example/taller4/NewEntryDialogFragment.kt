package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class NewEntryDialogFragment : DialogFragment() {

    interface NewEntryListener {
        fun onNewEntry(entry: DiaryEntry)
    }

    private var listener: NewEntryListener? = null

    fun setNewEntryListener(listener: NewEntryListener) {
        this.listener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_new_entry_dialog, container, false)
        val titleEditText: EditText = view.findViewById(R.id.titleEditText)
        val dateEditText: EditText = view.findViewById(R.id.dateEditText)
        val descriptionEditText: EditText = view.findViewById(R.id.descriptionEditText)
        val addButton: Button = view.findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val date = dateEditText.text.toString()
            val description = descriptionEditText.text.toString()
            try {
                validateDateFormat(date)
                val entry = DiaryEntry(title, date, description)
                listener?.onNewEntry(entry)
                dismiss()
            } catch (e: ParseException) {
                Toast.makeText(context, "Formato de fecha incorrecto. Use dd/MM/yyyy", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    @Throws(ParseException::class)
    private fun validateDateFormat(date: String) {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        dateFormat.isLenient = false
        dateFormat.parse(date)
    }
}