package fakhri.kchaou.maddina.view.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentHomeBinding
import fakhri.kchaou.maddina.databinding.FragmentSearchBinding
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.view.home.HomeAdapter


class SearchFragment : Fragment() {
    private var _binding : FragmentSearchBinding? =null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val array = arrayListOf<User>(User("","------"), User("","------"), User("","------"),
            User("","------"), User("","------"), User("","------"), User("","------"), User("","------"), User("","------"), )
        val adapter = SearchAdapter(requireContext(),array )
        binding.listUsers.layoutManager = LinearLayoutManager(requireContext())
        binding.listUsers.adapter = adapter


        return  binding.root
    }


}