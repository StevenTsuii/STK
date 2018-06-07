package com.example.steven.stk.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.steven.stk.GlideApp
import com.example.steven.stk.R
import com.example.steven.stk.data.model.ArticleListModel
import kotlinx.android.synthetic.main.item_article_list_cell.view.*







/**
 * Created by steven on 23/5/2018.
 */
class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ArticleListCellItemViewHolder>() {

     var articleItemList : ArrayList<ArticleListModel.ArticleItem> = ArrayList()

        var currentMediaPosition = -1

    fun addArticleItemList(list : ArrayList<ArticleListModel.ArticleItem>){
        articleItemList.addAll(list)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleListCellItemViewHolder {
        return ArticleListCellItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_article_list_cell, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleListCellItemViewHolder?, position: Int) {
        holder?.bind(articleItem = articleItemList.get(position))
    }

    override fun getItemCount(): Int {
        return articleItemList.size
    }


    class ArticleListCellItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(articleItem: ArticleListModel.ArticleItem) {
            itemView.title.text = articleItem.title
            itemView.label.text = articleItem.label
            itemView.viewCount.text = articleItem.social.viewCount

            for (media in articleItem.mediaGroup) {
                if ("image" == media.type) {
                    GlideApp.with(itemView.context)
                            .load(media.largePath)
                            .centerCrop()
                            .into(itemView.image)
                    break
                }
            }

            var hasMediaUrl = false

            for (media in articleItem.mediaGroup) {
                if("videos" == media.type && "480p" == media.quality){
                    hasMediaUrl = true
                    itemView.playerView.visibility = View.VISIBLE
                    itemView.play_icon.visibility = View.VISIBLE
                    break
                }
            }
            if(!hasMediaUrl){
                itemView.playerView.player = null
                itemView.playerView.visibility = View.GONE
                itemView.play_icon.visibility = View.GONE
            }




        }


    }


}