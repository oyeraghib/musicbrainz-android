package org.metabrainz.mobile.presentation.features.tagger

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.list_item_document.*
import org.metabrainz.mobile.R
import org.metabrainz.mobile.databinding.FragmentTaggerBinding

class TaggerFragment : Fragment() {


    internal var expandableListView: ExpandableListView?=null
    internal var adapter:ExpandableListAdapter?= null
    internal var titleList: List<String>?= null
    private lateinit var binding: FragmentTaggerBinding
    private lateinit var viewmodel:KotlinTaggerViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentTaggerBinding.inflate(inflater)
        viewmodel = activity?.run {
            ViewModelProvider(this).get(KotlinTaggerViewModel::class.java)
        }!!

        expandableListView = binding.expandableList
        if (expandableListView != null) {
            val listData = ExpandableListData()
            titleList = ArrayList(listData?.data?.keys)
            adapter = ExpandableListAdapter(requireContext(), titleList as ArrayList<String>, listData?.data)
            expandableListView!!.setAdapter(adapter)
            expandableListView!!.setOnGroupExpandListener { groupPosition -> Toast.makeText(requireContext(), (titleList as ArrayList<String>)[groupPosition] + " List Expanded.", Toast.LENGTH_SHORT).show() }
            expandableListView!!.setOnGroupCollapseListener { groupPosition -> Toast.makeText(requireContext(), (titleList as ArrayList<String>)[groupPosition] + " List Collapsed.", Toast.LENGTH_SHORT).show() }
        }

        viewmodel.TaglibFetchedMetadata.observe(viewLifecycleOwner, Observer { TFM ->
            Toast.makeText(requireContext(), "${TFM?.title} received in TaggerFragment", Toast.LENGTH_SHORT).show()
        })
        return binding.root
    }
}