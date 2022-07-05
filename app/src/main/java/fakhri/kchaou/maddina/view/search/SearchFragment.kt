package fakhri.kchaou.maddina.view.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fakhri.kchaou.maddina.databinding.FragmentSearchBinding
import fakhri.kchaou.maddina.viewmodel.UserVM


class SearchFragment : Fragment() {
    private var _binding : FragmentSearchBinding? =null
    private val binding get() = _binding!!
    lateinit var userVM: UserVM



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        userVM = UserVM()


        binding.search.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                userVM.searchByName(binding.search.text.toString()).observe(viewLifecycleOwner, Observer {
                    Log.println(Log.ASSERT, "///////////-------", it.toString())
                    val adapter = SearchAdapter(requireContext(),it )
                    binding.listUsers.layoutManager = LinearLayoutManager(requireContext())
                    binding.listUsers.adapter = adapter
                })

            }
            false
        })







        return  binding.root
    }


}