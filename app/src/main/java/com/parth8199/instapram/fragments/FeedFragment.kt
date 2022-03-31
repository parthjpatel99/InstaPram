package com.parth8199.instapram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parth8199.instapram.MainActivity
import com.parth8199.instapram.Post
import com.parth8199.instapram.PostAdapter
import com.parth8199.instapram.R


open class FeedFragment : Fragment() {

    lateinit var rvPosts: RecyclerView
    lateinit var postAdapter: PostAdapter

    var allPosts : MutableList<Post> = mutableListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvPosts = view.findViewById(R.id.rvPosts)
        postAdapter = PostAdapter(requireContext(),allPosts)
        rvPosts.adapter = postAdapter
        rvPosts.layoutManager = LinearLayoutManager(requireContext())
        queryPosts()
    }
    open fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)

        query.include(Post.KEY_USER)
        query.addDescendingOrder("createdAt")
        query.findInBackground(object : FindCallback<Post> {
            override fun done(posts: MutableList<Post>?, e: ParseException?) {
                if (e != null) {
                    Log.e(TAG, "Error Fetching Posts")
                } else {
                    if (posts != null) {
                        for (post in posts) {
                            Log.i(
                                TAG,
                                "Post: " + post.getDescription() + ", username: " + post.getUser()?.username
                            )
                        }
                        allPosts.addAll(posts)
                        postAdapter.notifyDataSetChanged()
                    }
                }
            }

        })
    }
    companion object{
        const val TAG= "FeedFragment"
    }
}