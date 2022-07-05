package fakhri.kchaou.maddina.view.friend

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fakhri.kchaou.maddina.databinding.FragmentListFriendBinding
import fakhri.kchaou.maddina.viewmodel.UserVM


class ListFriendFragment : Fragment() {
    private var _binding : FragmentListFriendBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListFriendBinding.inflate(inflater, container, false)

        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("userId", "")!!


        val userVM = UserVM()
        userVM.getfriends(id).observe(viewLifecycleOwner, Observer {

            val adapter = ListFriendAdapter(requireContext(), it)
            binding.listFriend.layoutManager = LinearLayoutManager(requireContext())
            binding.listFriend.adapter = adapter

        })

        binding.btnRequest.setOnClickListener {
            userVM.getfriends(id).observe(viewLifecycleOwner, Observer {

                val adapter = ListFriendAdapter(requireContext(), it)
                binding.listFriend.layoutManager = LinearLayoutManager(requireContext())
                binding.listFriend.adapter = adapter
                adapter.notifyDataSetChanged()
                Log.println(Log.ASSERT, "-----------", "request -----------" + it)


            })
        }
        binding.btnAll.setOnClickListener {
            userVM.getUsers().observe(viewLifecycleOwner, Observer {

                val adapter = ListFriendAdapter(requireContext(), it)
                binding.listFriend.layoutManager = LinearLayoutManager(requireContext())

                binding.listFriend.adapter = adapter
                adapter.notifyDataSetChanged()
                Log.println(Log.ASSERT, "-----------", "all -----------" + it)


            })
        }


        return binding.root
    }

}