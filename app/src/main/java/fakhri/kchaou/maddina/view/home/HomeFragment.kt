package fakhri.kchaou.maddina.view.home

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import fakhri.kchaou.maddina.R
import fakhri.kchaou.maddina.databinding.FragmentHomeBinding
import fakhri.kchaou.maddina.model.entity.Post
import fakhri.kchaou.maddina.viewmodel.PostVM


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? =null
    private val binding get() = _binding!!
    lateinit var alertDialog : AlertDialog
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


        var dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.loading_alert, null)
        val builder    = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("")
            .setCancelable(true)


        alertDialog = builder.show()

        postvm.getPost().observe(viewLifecycleOwner, Observer {
//           posts.addAll(it)


            val adapter = HomeAdapter(requireContext(),it )
            binding.rvPostList.layoutManager = LinearLayoutManager(requireContext())
            binding.rvPostList.adapter = adapter

            for (item in it){
                if (item.media_url == ""){
                    it.remove(item)
                }
            }
            posts.clear()
            posts.add(Post())


            /*********** remove the post contains only text ************/
            for (post in it){

                if (post.media_url != null){
                    posts.add(post)

                }
            }


            val adapterStory = StoriesAdapter(requireContext(), posts)
            binding.stories.layoutManager = LinearLayoutManager(requireContext())
            binding.stories.layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.HORIZONTAL,
                true
            )

            binding.stories.adapter = adapterStory

            alertDialog.dismiss()
        })


            return binding.root
    }


}