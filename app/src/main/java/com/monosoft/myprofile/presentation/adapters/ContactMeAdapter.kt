package com.monosoft.myprofile.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.monosoft.myprofile.R
import com.monosoft.myprofile.domain.models.ContactMe
import com.monosoft.myprofile.domain.models.ContactMeModel

class ContactMeAdapter (context: Context,
                        private  val contactMeList: List<ContactMe>) : RecyclerView.Adapter<ContactMeAdapter.MyViewHolder>(){

    private var onClickListener_: OnClickListener? = null
    private val context_ = context
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val  text= view.findViewById<TextView>(R.id.lblText)
        val  img= view.findViewById<com.airbnb.lottie.LottieAnimationView>(R.id.imageViewR)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactMeAdapter.MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_contact_me, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactMeAdapter.MyViewHolder, position: Int) {
        val contectMe: ContactMe =contactMeList[position]
        holder.text.setText(contectMe.tecnology)
       // holder.img.setImageURI(contectMe.image.toUri())
        Glide.with(context_).load(contectMe.image).into(holder.img)
    }

    override fun getItemCount(): Int {
        return  contactMeList.size
    }

    // Setter for the click listener
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener_ = onClickListener
    }

    // Interface for the click listener
    interface OnClickListener {
        fun onClick(position: Int, model: ContactMeModel?)
    }

}