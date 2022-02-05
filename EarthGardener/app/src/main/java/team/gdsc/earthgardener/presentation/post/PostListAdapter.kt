package team.gdsc.earthgardener.presentation.post

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import team.gdsc.earthgardener.R
import team.gdsc.earthgardener.databinding.ItemPostDailyBinding
import team.gdsc.earthgardener.domain.post.PostListData

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.PostListViewHolder>() {

    private var _postList : MutableList<PostListData.PostList> = mutableListOf()
    var postList: List<PostListData.PostList> = _postList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostListViewHolder {
        val binding: ItemPostDailyBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_post_daily, parent,false
        )
        return PostListViewHolder(binding)
    }
    override fun onBindViewHolder(holder: PostListViewHolder, position: Int) {
        holder.onBind(postList[position], position)
    }

    override fun getItemCount(): Int = postList.size

    inner class PostListViewHolder(
        val binding: ItemPostDailyBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: PostListData.PostList, position: Int) {
            Log.d("adapter-onbind", "$data")
            binding.postList = data
            notifyDataSetChanged()
        }
    }
}