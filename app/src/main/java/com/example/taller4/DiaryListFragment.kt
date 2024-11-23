package com.example.taller4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class DiaryListFragment : Fragment(), NewEntryDialogFragment.NewEntryListener {

    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_diary_list, container, false)
        val listView: ListView = view.findViewById(R.id.diaryListView)
        val diaryEntries = (activity as? DiaryActivity)?.diaryEntries ?: mutableListOf()
        adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, diaryEntries.map { "${it.title} - ${it.date}" })
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            (activity as? DiaryActivity)?.showDiaryDetail(position)
        }

        return view
    }

    override fun onNewEntry(entry: DiaryEntry) {
        (activity as? DiaryActivity)?.addDiaryEntry(entry)
        adapter.notifyDataSetChanged()
    }

    fun updateAdapter(entries: List<DiaryEntry>) {
        adapter.clear()
        adapter.addAll(entries.map { "${it.title} - ${it.date}" })
        adapter.notifyDataSetChanged()
    }
}