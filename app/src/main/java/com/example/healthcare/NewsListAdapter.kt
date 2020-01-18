package com.example.healthcare

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter internal constructor(context: Context): RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>(){

    var images = arrayOf<Int> (R.drawable.news1, R.drawable.news2, R.drawable.news3, R.drawable.news4, R.drawable.news5, R.drawable.news6,R.drawable.news7, R.drawable.news8)
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var news = emptyList<News>() // Cached copy of news

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val newsImageView: ImageView =itemView.findViewById(R.id.News_Image_1)
        val newsTitleTextView: TextView =itemView.findViewById(R.id.News_Title_1)
        val newsDescTextView: TextView =itemView.findViewById(R.id.News_Desc_1)
        val newsAuthorTextView: TextView =itemView.findViewById(R.id.News_Author_1)
        val newsTimeView: TextView =itemView.findViewById(R.id.News_Time_1)

    }
    override fun onCreateViewHolder(parent:ViewGroup, viewType: Int): NewsViewHolder{
        val itemView = inflater.inflate(R.layout.list_row,parent, false)
        return NewsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val current = news[position]
        //currentImage = current.id-1
        holder.newsImageView.setImageResource(images[position])
        holder.newsTitleTextView.text = current.title
        holder.newsDescTextView.text = current.description
        holder.newsAuthorTextView.text = current.author
        holder.newsTimeView.text = current.publishedTime.toString()
    }
    internal fun setNews(news: List<News>){
        this.news = news
        notifyDataSetChanged()
    }

    override fun getItemCount() =news.size
}