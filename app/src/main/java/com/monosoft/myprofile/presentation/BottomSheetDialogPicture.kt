package com.monosoft.myprofile.presentation


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.ext.SdkExtensions
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.LottieAnimationView
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.onNegative
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.position
import com.example.awesomedialog.title
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.storage.FirebaseStorage
import com.monosoft.myprofile.R
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.presentation.screens.images.FireBaseImageActivity
import com.monosoft.myprofile.presentation.util.Technology
import com.monosoft.myprofile.presentation.viewmodels.FirebaseViewModel
import com.monosoft.myprofile.presentation.viewmodels.TelegramViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import javax.annotation.Nullable


@AndroidEntryPoint
class BottomSheetDialogPicture(text_:String, technology_:String, firebaseViewModel_: FirebaseViewModel,activity_: MainActivity) : BottomSheetDialogFragment() {

    val technology = technology_
    val text = text_
    var identificacion: File? = null
    var selectedImageURI: Uri? = null
    var flujo: String = ""
    private val SELECT_PICTURE = 1
    lateinit var imageViewPhoto : ImageView
    lateinit var imageViewGallery : ImageView
    lateinit var buttonCancel : Button
    val activity = activity_
    private val ANDROID_R_REQUIRED_EXTENSION_VERSION = 2
    private lateinit var filePath: Uri
    private lateinit var storage: FirebaseStorage
    private val REQUEST_PERMISSION = 100
    val firebaseViewModel = firebaseViewModel_

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {

        setStyle(DialogFragment.STYLE_NORMAL, R.style.TodoInputDialogFragment)

            val v: View = inflater.inflate(
            R.layout.bottom_sheet_select_image,
            container, false
        )
        firebaseViewModel.loading()
        storage = FirebaseStorage.getInstance()
        checkCameraPermission()

         imageViewPhoto = v.findViewById<ImageView>(R.id.imageViewPhoto)
         imageViewGallery = v.findViewById<ImageView>(R.id.imageViewGallery)
         buttonCancel = v.findViewById<Button>(R.id.btnCancelar)

        imageViewPhoto.setOnClickListener {

            openCamera()
        }

        imageViewGallery.setOnClickListener {

            openGallery()
        }

       buttonCancel.setOnClickListener {
            Toast.makeText(
                activity,
                "Course Shared", Toast.LENGTH_SHORT
            ).show()
            dismiss()
        }



        return v
    }


    private fun openCamera() {
        // Toast.makeText(this,"entro openGallery",Toast.LENGTH_LONG).show()
        try {
            flujo = "camara"
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        // Asegurarse de que hay una actividad de cámara disponible
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            // Crear el archivo donde se guardará la foto
            val photoFile: File? = createImageFile(requireActivity())

            // Continuar solo si el archivo fue creado correctamente
            photoFile?.also {
                filePath = getUriFromFile(requireActivity(), it)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, filePath)
                startActivityForResult(takePictureIntent, SELECT_PICTURE)
            }
        }
        }catch(e : Exception)
        {
            e.toString()
        }
//        try {
//            flujo = "camara"
//            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            // Start the activity with camera_intent, and request pic id
//            // Start the activity with camera_intent, and request pic id
//            startActivityForResult(camera_intent, 1)
////           Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { intent ->
////               intent.resolveActivity(packageManager)?.also {
////                   startActivityForResult(intent, 123)
////               }
////           }
//        }catch(e : Exception)
//        {
//            e.toString()
//        }
    }

    fun createImageFile(context: Context): File {
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir?.exists()!! == true) {
            storageDir.mkdirs()
        }
        return File.createTempFile(
            "JPEG_${System.currentTimeMillis()}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        )
    }

    fun getUriFromFile(context: Context, file: File): Uri {
        return FileProvider.getUriForFile(
            context,
            "${context.packageName}",
            file
        )
    }

    private fun openGallery() {
        try
        {

            flujo="galeria"
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if(isPhotoPickerAvailable()==true)
                {
                    val intent = Intent(MediaStore.ACTION_PICK_IMAGES)
                    startActivityForResult(intent, 1)
                }
            } else {

                val intent = Intent()
                intent.type = "image/*"
                intent.action = Intent.ACTION_GET_CONTENT
                startActivityForResult(
                    Intent.createChooser(intent, "Select Picture"),
                    SELECT_PICTURE
                )
            }

        }catch (e :Exception)
        {
            imageViewGallery.isEnabled = true
            Toast.makeText(requireContext(),"error entro a exception " +e.toString(),Toast.LENGTH_LONG).show()
            e.toString()
        }
    }

    private fun checkCameraPermission() {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES)
//                != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
//                    REQUEST_PERMISSION
//                )
//            }
//
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_AUDIO)
//                != PackageManager.PERMISSION_GRANTED
//            ) {
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(Manifest.permission.READ_MEDIA_AUDIO),
//                    REQUEST_PERMISSION
//                )
//            }
//        }
//        else
//        {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_PERMISSION)
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                REQUEST_PERMISSION)
        }
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }
        // }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        imageViewGallery.isEnabled = true
        dismiss()
        if (resultCode == AppCompatActivity.RESULT_OK) {

            if(flujo.equals("camara"))
            {
                if (requestCode == 1 )
                {
                        activity.binding.profileImage.setImageURI(null)
                        activity.binding.profileImage.visibility = View.VISIBLE


                        AwesomeDialog.build(activity)
                            .title("Mensaje")
                            .body("Deseas guardar esta imagen")
                            .body("DESEAS GUARDAR ESTA IMAGEN. ESTA IMAGEN SERA GUARDADA EN FIREBASE STORAGE TE MOSTRARA LA URL PARA VISUALIZARLA")
                            .position(AwesomeDialog.POSITIONS.CENTER)
                            .onPositive("ACEPTAR") {
                                Log.d("TAG", "positive ")
                                uploadImageToFirebase()
                            }
                            .onNegative("CANCELAR"){

                            }
                }
            }
            if(flujo.equals("galeria"))
            {
               try
               {
                   /*** mostramos el mensaje despues de cargar la foto **/
                   val uri = data?.getData()
                   activity.binding.profileImage.setImageURI(uri)
                   filePath = uri!!

                   /*** mostramos el mensaje despues de cargar la foto **/
                   AwesomeDialog.build(activity)
                       .title("Mensaje")
                       .body("Deseas guardar esta imagen")
                       .body("DESEAS GUARDAR ESTA IMAGEN. ESTA IMAGEN SERA GUARDADA EN FIREBASE STORAGE TE MOSTRARA LA URL PARA VISUALIZARLA")
                       .position(AwesomeDialog.POSITIONS.CENTER)
                       .onPositive("ACEPTAR") {
                           Log.d("TAG", "positive ")
                           uploadImageToFirebase()
                       }
                       .onNegative("CANCELAR"){

                       }
               }catch (e:Exception){

               }

            }
        }
        else
        {
            Toast.makeText(activity,"ERROR AL CARGAR LA IMAGEN",Toast.LENGTH_LONG).show()
        }
    }

    // Subir imagen a Firebase Storage
    private fun uploadImageToFirebase() {
        if (::filePath.isInitialized) {
            val storageReference = storage.reference.child("images/${System.currentTimeMillis()}.jpg")

            storageReference.putFile(filePath)
                .addOnSuccessListener {
                    // La imagen se ha subido correctamente
                    storageReference.downloadUrl.addOnSuccessListener { uri ->
                        // Aquí puedes obtener la URL de descarga de la imagen subida
                        println("Imagen subida: $uri")
                        firebaseViewModel.uploadImageProfile("1",uri.toString())
                        AwesomeDialog.build(activity)
                            .title("Mensaje")
                            .body("Imagen subida: $uri")
                            .position(AwesomeDialog.POSITIONS.CENTER)
                            .onPositive("VER IMAGEN") {
                                Log.d("TAG", "positive ")
                                val intent = Intent(activity,FireBaseImageActivity::class.java)
                                intent.putExtra("url",uri.toString())
                                activity.startActivity(intent)
                            }
                            .onNegative("CANCELAR"){

                            }
                    }
                }
                .addOnFailureListener {
                    // Manejar errores de subida
                    println("Error al subir la imagen: ${it.message}")
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun isPhotoPickerAvailable(): Boolean {
        return when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> true
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> {
                SdkExtensions.getExtensionVersion(Build.VERSION_CODES.R) >= ANDROID_R_REQUIRED_EXTENSION_VERSION
            }
            else -> false
        }
    }

    fun getPath(context: Context, uri: Uri): String? {

        // check here to KITKAT or new version
        val isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

        // DocumentProvider

        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {

            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                val type = split[0]
                if ("primary".equals(type, ignoreCase = true)) {
                    return (Environment.getExternalStorageDirectory().toString() + "/"
                            + split[1])
                }
            } else if (isDownloadsDocument(uri)) {
                val id = DocumentsContract.getDocumentId(uri)
                val contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"),
                    java.lang.Long.valueOf(id)
                )
                return getDataColumn(context, contentUri, null, null)
            } else if (isMediaDocument(uri)) {
                val docId = DocumentsContract.getDocumentId(uri)
                val split = docId.split(":".toRegex()).dropLastWhile { it.isEmpty() }
                    .toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if ("video" == type) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if ("audio" == type) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }
                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(
                    context, contentUri, selection,
                    selectionArgs
                )
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {

            // Return the remote address
            return if (isGooglePhotosUri(uri)) uri.lastPathSegment else getDataColumn(
                context,
                uri,
                null,
                null
            )
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            return uri.path
        }
        return null
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context
     * The context.
     * @param uri
     * The Uri to query.
     * @param selection
     * (Optional) Filter used in the query.
     * @param selectionArgs
     * (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    fun getDataColumn(
        context: Context, uri: Uri?,
        selection: String?, selectionArgs: Array<String>?
    ): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(
                uri!!, projection,
                selection, selectionArgs, null
            )
            if (cursor != null && cursor.moveToFirst()) {
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            cursor?.close()
        }
        return null
    }

    /**
     * @param uri
     * The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    fun isExternalStorageDocument(uri: Uri): Boolean {
        return "com.android.externalstorage.documents" == uri
            .authority
    }

    /**
     * @param uri
     * The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    fun isDownloadsDocument(uri: Uri): Boolean {
        return "com.android.providers.downloads.documents" == uri
            .authority
    }

    /**
     * @param uri
     * The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    fun isMediaDocument(uri: Uri): Boolean {
        return "com.android.providers.media.documents" == uri
            .authority
    }

    /**
     * @param uri
     * The Uri to check.
     * @return Whether the Uri authority is Google Photos.
     */
    fun isGooglePhotosUri(uri: Uri): Boolean {
        return "com.google.android.apps.photos.content" == uri
            .authority
    }

}