package com.example.custom_adapter

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class ProductRAdapter(val context: Context,val products: List<ProductItem>, val clicker:(index:Int)->Unit): RecyclerView.Adapter<ProductRAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val productView:View = LayoutInflater.from(context).inflate(R.layout.product_item, null)

        val viewHolder = ViewHolder(context, productView)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return products.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.productText.text = products[position].productText
        holder.productView.setOnClickListener{clicker(position)}
        val imageRes = context.resources.getIdentifier(products[position].productImage, "drawable", context.packageName)
        holder.productImage.setImageResource(imageRes)
    }


    inner class ViewHolder(context: Context,val productView:View):RecyclerView.ViewHolder(productView){

        val productText = productView.findViewById<TextView>(R.id.productText)
        val productImage = productView.findViewById<ImageView>(R.id.productImage)
    }

}


class ProductAdapter(val context: Context,val products: List<ProductItem>):BaseAdapter(){
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {

        var viewHolder:ViewHolder

        if( p1 != null){

            viewHolder = p1.tag as ViewHolder
        }
        else{
            viewHolder = ViewHolder()
            viewHolder.productView.tag = viewHolder
        }

        viewHolder.productText.text = products[p0].productText

        val imageRes = context.resources.getIdentifier(products[p0].productImage, "drawable", context.packageName)
        viewHolder.productImage.setImageResource(imageRes)

        return viewHolder.productView
    }

    override fun getItem(p0: Int): Any {
        return products[p0]
    }

    override fun getItemId(p0: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return products.count()
    }


    inner class ViewHolder(){

        val productView:View = LayoutInflater.from(context).inflate(R.layout.product_item, null)
        val productText = productView.findViewById<TextView>(R.id.productText)
        val productImage = productView.findViewById<ImageView>(R.id.productImage)

    }

}


class ProductItem(val productText:String, val productImage:String)




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ourList = listOf<ProductItem>(ProductItem("serega", "a"), ProductItem("danya", "b"), ProductItem("maxx", "c"),
            ProductItem("serega", "a"), ProductItem("danya", "b"), ProductItem("maxx", "c"))
        productsList.adapter = ProductRAdapter(this, ourList){
            println("clicked on $it")
        }
        productsList.layoutManager = LinearLayoutManager(this)

    }


}
