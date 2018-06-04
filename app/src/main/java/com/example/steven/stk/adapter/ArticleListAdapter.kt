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
class ArticleListAdapter(articleItemList: ArrayList<ArticleListModel.ArticleItem>) : RecyclerView.Adapter<ArticleListAdapter.ArticleListCellItemViewHolder>() {

    var articleItemList = articleItemList


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ArticleListCellItemViewHolder {
        return ArticleListCellItemViewHolder(LayoutInflater.from(parent?.context).inflate(R.layout.item_article_list_cell, parent, false))
    }

    override fun onBindViewHolder(holder: ArticleListCellItemViewHolder?, position: Int) {
        holder?.bind(articleItem = articleItemList.get(position))
    }

    override fun getItemCount(): Int {
        return articleItemList?.size ?: 0
    }


    class ArticleListCellItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(articleItem: ArticleListModel.ArticleItem) {
            itemView.title.text = articleItem.title
            itemView.label.text = articleItem.label
            itemView.viewCount.text = articleItem.social.viewCount

            for (meida in articleItem.mediaGroup) {
                if ("image" == meida.type) {
                    GlideApp.with(itemView.context)
                            .load(meida.largePath)
                            .centerCrop()
                            .into(itemView.image)


                    break
                }
            }
        }
    }
}