package com.dicoding.capstone.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capstone.core.R
import com.dicoding.capstone.core.domain.model.Game
import com.dicoding.capstone.core.databinding.ItemListGameBinding

class GameAdapter : RecyclerView.Adapter<GameAdapter.ListViewHolder>() {
    var onItemClick: ((Game) -> Unit)? = null

    private var diffCallback = object : DiffUtil.ItemCallback<Game>() {
        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.gameId == newItem.gameId
        }

        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun setData(gameList: List<Game>) = differ.submitList(gameList)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.ListViewHolder =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_game, parent, false))

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: GameAdapter.ListViewHolder, position: Int) {
        val data = differ.currentList[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListGameBinding.bind(itemView)
        fun bind(data: Game) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.imageUrl)
                    .into(ivItemImage)
                tvItemTitle.text = data.name
                Log.i("GameAdapter", data.name)
                "Rating: ${data.rating}".also { tvItemSubtitle.text = it }
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(differ.currentList[adapterPosition])
            }
        }
    }
}