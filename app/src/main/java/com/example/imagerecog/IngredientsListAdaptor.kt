package com.example.imagerecog

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imagerecog.R.layout.item_ingredient

class IngredientsListAdaptor(private val items: ArrayList<String>, private val listener:IngredientItemClicked): RecyclerView.Adapter<IngredientsViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(item_ingredient, parent, false)
        val viewHolder = IngredientsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])

        }
        return IngredientsViewHolder(view)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        val currentItem=items[position]
        holder.titleView.text=currentItem
        val currentItem2=currentItem.replace("\\s".toRegex(), "")
        val rating:Int= com.example.ingredientanalyzer.Ingredients.value(currentItem2)
        if (rating>5){
          //  holder.titleView.setBackgroundColor(Color.parseColor("#00FF00"))
            holder.titleView.setTextColor(Color.parseColor("#00FF00"))
        }
        else if(rating<5){
           // holder.titleView.setBackgroundColor(Color.parseColor("#DC143C"))
            holder.titleView.setTextColor(Color.parseColor("#DC143C"))

        }
        else {
          //  holder.titleView.setBackgroundColor(Color.parseColor("#E9967A"))
         holder.titleView.setTextColor(Color.parseColor("white"))
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById<TextView>(R.id.title)

}

interface IngredientItemClicked{
    fun onItemClicked(item: String)
}