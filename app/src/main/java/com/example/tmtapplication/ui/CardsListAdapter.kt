package com.example.tmtapplication.ui

import com.example.tmtapplication.R
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tmtapplication.model.TMTCard
import com.example.tmtapplication.utils.Constants
import com.squareup.picasso.Picasso

/**
 * <h1>CardsListAdapter</h1>
 * CardsListAdapter is used to render the cards list
 */
class CardsListAdapter(private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TEXT_TYPE = 0
    private val VIEW_TITLE_TYPE = 1
    private val IMAGE_TYPE = 2

    var cardListItems: List<TMTCard> = mutableListOf()
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == VIEW_TEXT_TYPE) { // for TEXT layout
            var view =
                LayoutInflater.from(context).inflate(R.layout.textcard_view, viewGroup, false);
            return TextViewHolder(view);

        } else if (viewType == VIEW_TITLE_TYPE) { // for TITLE layout
            var view =
                LayoutInflater.from(context).inflate(R.layout.titlecrd_view, viewGroup, false);
            return TitleViewHolder(view);

        } else { // for IMAGE layout
            var view =
                LayoutInflater.from(context).inflate(R.layout.imagecard_view, viewGroup, false);
            return ImageTitleViewHolder(context, view);
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (getItemViewType(position) == VIEW_TEXT_TYPE) {
            (holder as TextViewHolder).setDetails(cardListItems[position])
        } else if (getItemViewType(position) == VIEW_TITLE_TYPE) {
            (holder as TitleViewHolder).setDetails(cardListItems[position])
        } else {
            (holder as ImageTitleViewHolder).setDetails(cardListItems[position])
        }

    }

    override fun getItemCount(): Int {
        return cardListItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (cardListItems[position].card_type) {
            Constants.TEXT -> VIEW_TEXT_TYPE
            Constants.TITLE -> VIEW_TITLE_TYPE
            Constants.IMAGE -> IMAGE_TYPE
            else -> VIEW_TEXT_TYPE
        }
    }

    /**
     * this functions is used for update the list
     * and refresh the list
     */
    fun setList(cardsList: List<TMTCard>) {
        cardListItems = cardsList
        notifyDataSetChanged()
    }


    /**
     *  This ViewHolder class for TEXT Card type
     *   @itemView view
     */
    internal class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val txtValue: TextView

        init {
            txtValue = itemView.findViewById(R.id.card_text_view)
        }

        fun setDetails(card: TMTCard) {
            txtValue.text = card.card.value
            txtValue.setTextColor(Color.parseColor(card.card.attributes.text_color))
            txtValue.textSize = card.card.attributes.font.size.toFloat()
        }
    }

    /**
     *  This ViewHolder class for TITLE Card type
     *   @itemView view
     */
    internal class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleValue: TextView
        private val txtDescription: TextView

        init {
            titleValue = itemView.findViewById(R.id.title_view)
            txtDescription = itemView.findViewById(R.id.description_view)
        }

        fun setDetails(card: TMTCard) {
            titleValue.text = card.card.title.value
            titleValue.setTextColor(Color.parseColor(card.card.title.attributes.text_color))
            titleValue.textSize = card.card.title.attributes.font.size.toFloat()
            txtDescription.text = card.card.description.value
            txtDescription.setTextColor(Color.parseColor(card.card.description.attributes.text_color))
            txtDescription.textSize = card.card.description.attributes.font.size.toFloat()

        }
    }
    /**
     *  This ViewHolder class for IMAGE TITLE Card type
     *  @param context
     *  @itemView view
     */

    internal class ImageTitleViewHolder(val context: Context, itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private val titleValue: TextView
        private val txtDescription: TextView
        private val imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.image_view)
            titleValue = itemView.findViewById(R.id.title_view)
            txtDescription = itemView.findViewById(R.id.description_view)
        }

        fun setDetails(card: TMTCard) {
            titleValue.text = card.card.title.value
            titleValue.setTextColor(Color.parseColor(card.card.title.attributes.text_color))
            titleValue.textSize = card.card.title.attributes.font.size.toFloat()
            txtDescription.text = card.card.description.value
            txtDescription.setTextColor(Color.parseColor(card.card.description.attributes.text_color))
            txtDescription.textSize = card.card.description.attributes.font.size.toFloat()
            imageView.layoutParams.width = card.card.image.size.width
            imageView.layoutParams.height = card.card.image.size.height
            imageView.requestLayout()
            Picasso.with(context).load(card.card.image.url).into(imageView);
        }
    }

}
