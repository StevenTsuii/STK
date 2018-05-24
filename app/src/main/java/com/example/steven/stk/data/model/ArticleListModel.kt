package com.example.steven.stk.data.model

/**
 * Created by steven on 23/5/2018.
 */
object ArticleListModel {
    data class BaseResponse(val resTime: Int, val state: String, val content: ArrayList<ArticleItem>)

    data class ArticleItem(
            val order: String,
            val highlight: String,
            val brandId: String,
            val brandName: String,
            val brandArticleId: String,
            val brandCategoryId: String,
            val mlCategoryId: String,
            val mlArticleId: String,
            val issueId: String,
            val pubDate: String,
            val updateDate: String,
            val displayLayoutPreset: String,
            val displayTime: String,
            val forceToShowDate: String,
            val title: String,
            val label: String,
            val mediaGroup: ArrayList<MediaItem>,
            val sharing: Sharing,
            val social: Social
    )


    data class Sharing(val image: String, val url: String)

    data class Social(val likeCount: String, val commentCount: String, val viewCount: String, val videoViewCount: String)

    data class MediaItem(
            val type: String,
            val smallPath: String,
            val largePath: String,
            val width: String,
            val height: String
    )
}