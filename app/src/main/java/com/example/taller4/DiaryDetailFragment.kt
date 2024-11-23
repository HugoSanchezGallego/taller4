package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DiaryDetailFragment : Fragment() {

    companion object {
        private const val ARG_ENTRY_ID = "entry_id"

        fun newInstance(entryId: Int): DiaryDetailFragment {
            val fragment = DiaryDetailFragment()
            val args = Bundle()
            args.putInt(ARG_ENTRY_ID, entryId)
            fragment.arguments = args
            return fragment
        }
    }

    private var entryId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_diary_detail, container, false)
        entryId = arguments?.getInt(ARG_ENTRY_ID) ?: 0
        val entry = (activity as? DiaryActivity)?.getDiaryEntry(entryId)
        val detailTextView: TextView = view.findViewById(R.id.detailTextView)
        detailTextView.text = "Título: ${entry?.title}\nFecha: ${entry?.date}\nDescripción: ${entry?.description}"

        val deleteButton: Button = view.findViewById(R.id.deleteButton)
        deleteButton.setOnClickListener {
            (activity as? DiaryActivity)?.deleteDiaryEntry(entryId)
        }

        return view
    }
}