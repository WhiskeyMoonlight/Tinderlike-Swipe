package com.dimas.tinderlike_swipe

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView


class CurrentArticleFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = layoutInflater.inflate(R.layout.fragment_current_article, container, false)

        val currId = CurrentArticleFragmentArgs.fromBundle(requireArguments()).articleId
        val current: ArticleResponse = Controller.getDataById(currId)

        // TitleTV
        view.findViewById<TextView>(R.id.current_article_title_tv)
            .apply { text = current.title }

        // DescriptionTV
        view.findViewById<TextView>(R.id.current_article_description_tv)
            .apply { text = current.description }

        //CaptionTV
        view.findViewById<TextView>(R.id.current_article_caption_tv)
            .apply { text = current.caption }

        // ContentsTV
        view.findViewById<TextView>(R.id.current_article_contents_tv)
            .apply { text = current.contents }

        // ImageView
        view.findViewById<ImageView>(R.id.article_image_iv)
            .apply { setImageResource(current.imageId) }

        val shareButton: Button = view.findViewById(R.id.current_article_share_btn)
        shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, current.title)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
        return view
    }
}