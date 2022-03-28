package fakhri.kchaou.maddina.view.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fakhri.kchaou.maddina.databinding.FragmentHomeBinding
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.viewmodel.PostVM


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? =null
    private val binding get() = _binding!!
    var posts : ArrayList<Post> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val postvm = PostVM()


        postvm.getPost().observe(viewLifecycleOwner, Observer {
           posts.addAll(it)
//            Log.println(Log.ASSERT, "-----", it.size.toString())
            val adapter = HomeAdapter(requireContext(),posts )
            binding.rvPostList.layoutManager = LinearLayoutManager(requireContext())
            binding.rvPostList.adapter = adapter

        })


            return binding.root
    }


}