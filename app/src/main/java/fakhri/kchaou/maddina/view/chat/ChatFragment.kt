package fakhri.kchaou.maddina.view.chat

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
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentChatBinding
import fakhri.kchaou.maddina.model.entity.User
import fakhri.kchaou.maddina.view.home.HomeAdapter
import fakhri.kchaou.maddina.viewmodel.ChatVM
import fakhri.kchaou.maddina.viewmodel.UserVM


class ChatFragment : Fragment() {

    private var _binding : FragmentChatBinding? =null
    private val binding get() = _binding!!
    lateinit var userVM : UserVM
    lateinit var chatVM: ChatVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChatBinding.inflate(inflater, container, false)
        userVM = UserVM()
        chatVM = ChatVM()
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("user", Context.MODE_PRIVATE)
        val id = sharedPreferences.getString("userId", "")!!

        userVM.getfriends(id).observe(viewLifecycleOwner, Observer {

            val adapterStory = UsersConnectedAdapter(requireContext(), it )

            binding.users.layoutManager = LinearLayoutManager(requireContext())
            binding.users.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                true
            )

            binding.users.adapter = adapterStory
        })


       chatVM.getChats(id).observe(viewLifecycleOwner, Observer {
        if (it != null){
            val adapter = ChatAdapter(requireContext(),it )
            binding.chats.layoutManager = LinearLayoutManager(requireContext())
            binding.chats.adapter = adapter
        }

        })


        return binding.root
    }


}