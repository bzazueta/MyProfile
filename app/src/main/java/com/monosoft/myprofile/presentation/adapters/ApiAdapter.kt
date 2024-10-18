package com.monosoft.myprofile.presentation.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.monosoft.myprofile.R
import com.monosoft.myprofile.domain.models.ContactMeModel
import com.monosoft.myprofile.domain.models.CrudApiList
import com.monosoft.myprofile.presentation.viewmodels.CrudApiViewModel


class ApiAdapter (context: Context,
                  private  val contactMeList: List<CrudApiList>,apiViewModel: CrudApiViewModel) : RecyclerView.Adapter<ApiAdapter.MyViewHolder>(){

    private var onClickListener_: OnClickListener? = null
    private val context_ = context
    private val apiViewModel_ = apiViewModel

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val  text= view.findViewById<TextView>(R.id.lblNameApi)
        val  img= view.findViewById<ImageView>(R.id.imageViewApi)
        val  btnEdit= view.findViewById<ImageButton>(R.id.imageButtonEditApi)
        val  btnDelete = view.findViewById<ImageButton>(R.id.imageButtonDeleteApi)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.api_list_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val api: CrudApiList =contactMeList[position]
        holder.text.setText(api.name.toString())
        // holder.img.setImageURI(contectMe.image.toUri())
        //Glide.with(context_).load(api.image).into(holder.img)

        holder.btnEdit.setOnClickListener {
            showAlertEditDialog(api.id.toString())
        }

        holder.btnDelete.setOnClickListener {
            showAlertDeleteDialog(api.id.toString())
        }

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

    fun showAlertEditDialog(id_api:String) {
        // Create an alert builder
        val builder: AlertDialog.Builder = AlertDialog.Builder(context_)
        //builder.setTitle("Name")

        // set the custom layout
        val customLayout: View = LayoutInflater.from(context_).inflate(com.monosoft.myprofile.R.layout.api_dialog, null)
        builder.setView(customLayout)
        val editText = customLayout.findViewById<EditText>(com.monosoft.myprofile.R.id.txtApiName)
        val buton = customLayout.findViewById<Button>(com.monosoft.myprofile.R.id.btnAdd)

        // create and show the alert dialog
        val dialog: AlertDialog = builder.create()

        buton.setOnClickListener{
            dialog.dismiss()
            apiViewModel_.editNameApi(id_api,editText.text.toString(),"")
        }

        dialog.show()
    }

    fun showAlertDeleteDialog(id_api:String) {
        // Create an alert builder
        val builder: AlertDialog.Builder = AlertDialog.Builder(context_)
        builder.setTitle("Mensaje")
        builder.setCancelable(false)
        builder.setMessage("¿Deseas eliminar este usuario?");

        // set the custom layout

        builder.setPositiveButton(
            "Eliminar",
            DialogInterface.OnClickListener { dialog, id ->
                dialog.dismiss()
                apiViewModel_.deleteNameApi(id_api.toString())
            })

        builder.setNegativeButton(
            "Cancelar",
            DialogInterface.OnClickListener { dialog, id ->

            })
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}