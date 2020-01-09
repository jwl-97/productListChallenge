package com.jiwoolee.productlistchallenge

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.view.setMargins
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.jiwoolee.productlistchallenge.retrofit.ProductData
import kotlinx.android.synthetic.main.recyclerview_item.view.*
import java.util.*

const val TYPE_HEADER = 0
const val TYPE_ITEM = 1
const val TYPE_FOOTER = 2

class RecyclerviewAdapter(private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ViewHolder>() {
    private val listData = ArrayList<ProductData>()

    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            TYPE_ITEM -> {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
                MyViewHolder(view)
            }
            TYPE_HEADER -> {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_header, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_footer, parent, false)
                FooterViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_HEADER else if (position == listData.size + 1) TYPE_FOOTER else TYPE_ITEM
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is MyViewHolder) {
            val itemViewHolder: MyViewHolder = holder
            itemViewHolder.onBind(listData[position - 1], itemClickListener)
        }
    }

    fun addItem(productData: ProductData) {
        listData.add(productData)
    }

    override fun getItemCount(): Int {
        return listData.size + 2
    }

    internal class HeaderViewHolder(headerView: View?) : ViewHolder(headerView!!)

    internal class FooterViewHolder(footerView: View?) : ViewHolder(footerView!!)

    class MyViewHolder internal constructor(itemView: View) : ViewHolder(itemView) {
        internal fun onBind(productData: ProductData, clickListener: OnItemClickListener) {
            itemView.tv_product_title.text = productData.productTitle
            itemView.tv_product_price.text = productData.productPrice
            Glide.with(itemView.context)
                .load(productData.thumbnailImage)
                .fitCenter()
                .into(itemView.iv_product_thumnail)

            itemView.setOnClickListener { clickListener.onItemClicked(productData) }
        }
    }
}

interface OnItemClickListener{
    fun onItemClicked(productData: ProductData)
}