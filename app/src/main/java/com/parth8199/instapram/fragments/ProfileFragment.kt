package com.parth8199.instapram.fragments

import android.util.Log
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery
import com.parse.ParseUser
import com.parth8199.instapram.Post

class ProfileFragment : FeedFragment() {
    override fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)

        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_USER,ParseUser.getCurrentUser())
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
}