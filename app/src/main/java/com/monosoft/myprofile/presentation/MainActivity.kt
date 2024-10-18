package com.monosoft.myprofile.presentation

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.ClipData
import android.content.ClipboardManager
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.onNegative
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.position
import com.example.awesomedialog.title
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import com.monosoft.myprofile.R
import com.monosoft.myprofile.databinding.ActivityMainBinding
import com.monosoft.myprofile.domain.models.ContactMe
import com.monosoft.myprofile.domain.models.FirebaseNotificationModel
import com.monosoft.myprofile.domain.models.FrameworksAndTecnology
import com.monosoft.myprofile.domain.models.ResponseModel
import com.monosoft.myprofile.domain.models.SuccesModel
import com.monosoft.myprofile.domain.models.WorkExperience
import com.monosoft.myprofile.domain.utils.Resource
import com.monosoft.myprofile.presentation.adapters.ContactMeAdapter
import com.monosoft.myprofile.presentation.adapters.ExperienceAdapter
import com.monosoft.myprofile.presentation.adapters.SkilsAdapter
import com.monosoft.myprofile.presentation.screens.api.ApiActivity
import com.monosoft.myprofile.presentation.screens.firebase.FirebaseActivity
import com.monosoft.myprofile.presentation.screens.technology.AndroidActivity
import com.monosoft.myprofile.presentation.screens.technology.TechnologyActivity
import com.monosoft.myprofile.presentation.util.RecyclerTouchListener
import com.monosoft.myprofile.presentation.viewmodels.CopyTextViewModel
import com.monosoft.myprofile.presentation.viewmodels.FirebaseViewModel
import com.monosoft.myprofile.presentation.viewmodels.NetworkCheckedViewModel
import com.monosoft.myprofile.presentation.viewmodels.TelegramViewModel
import com.optic.ecommerceappmvvm.core.Config
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val mainViewModel: MainViewModel by viewModels()
    val telegramViewModel: TelegramViewModel by viewModels()
    val firebaseViewModel: FirebaseViewModel by viewModels()
    private val copyViewModel: CopyTextViewModel by viewModels()
    private val networkCheckedViewModel: NetworkCheckedViewModel by viewModels()
    var contactAdappter: ContactMeAdapter? = null
    var experienceAdappter: ExperienceAdapter? = null
    var skillAdappter: SkilsAdapter? = null
    var listContact: List<ContactMe> = listOf()
    var listExperience: List<WorkExperience> = listOf()
    var listFrameworksLanguage: List<FrameworksAndTecnology> = listOf()
    var tknFrb: String = ""
    var tkn : String = ""
    private lateinit var shimmerFrameLayout: ShimmerFrameLayout

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Logger.addLogAdapter(AndroidLogAdapter())
        Logger.d("LOG","onCreate");
        networkCheckedViewModel.networkChecked(this)


        networkCheckedViewModel.networkCheck.observe(this, Observer {
               if(it)
               {
                   loadingState()
                   mainViewModel.login()
                  //networkCheckedViewModel.exitInternet(this)

               }else{
                   closeLoadingState()
                   showSnackBar("Revisa tu conexión a internet e intenta de nuevo.")
                   /*** mostramos el mensaje despues de cargar la foto **/
                   AwesomeDialog.build(this)
                       .title("Mensaje")
                       .body("Sin conexión")
                       .body("REVISA TU CONEXION A INTERNET E INTENTA DE NUEVO")
                       .position(AwesomeDialog.POSITIONS.CENTER)
                       .onPositive("ACEPTAR") {
                           Log.d("TAG", "positive ")

                       }
                       .onNegative("CERRAR APP"){
                           finishAffinity()
                       }
               }
        })

//        lifecycleScope.launch {
//           val result = networkCheckedViewModel.networkConnected.value
//            if(result == true){
//                mainViewModel.login()
//            }else{
//                closeLoadingState()
//                showSnackBar("Revisa tu conexión a internet e intenta de nuevo.")
//                /*** mostramos el mensaje despues de cargar la foto **/
//                AwesomeDialog.build(this@MainActivity)
//                    .title("Mensaje")
//                    .body("Sin conexión")
//                    .body("REVISA TU CONEXION A INTERNET E INTENTA DE NUEVO")
//                    .position(AwesomeDialog.POSITIONS.CENTER)
//                    .onPositive("ACEPTAR") {
//                        Log.d("TAG", "positive ")
//                        closeLoadingState()
//                    }
//                    .onNegative("CERRAR APP"){
//                        finishAffinity()
//                    }
//            }
//        }

        askNotificationPermission()

        binding.shimmerLayout.startShimmer()
        binding.shimmerLayoutName.startShimmer()
        binding.shimmerLayoutPhone.startShimmer()
        binding.shimmerLayoutEmail.startShimmer()
        binding.shimmerLayoutYearsExperience.startShimmer()
        binding.shimmerLayoutrecyclerContactMe.startShimmer()
        binding.shimmerLayoutCard1.startShimmer()

        listContact = arrayListOf()
        listExperience = arrayListOf()
        binding.lblAboutMe.setMovementMethod(ScrollingMovementMethod())

        binding.recyclerContactMe.setHasFixedSize(true)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(
            this@MainActivity, LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.recyclerContactMe.layoutManager = mLayoutManager
        binding.recyclerContactMe.itemAnimator = DefaultItemAnimator()
        binding.recyclerContactMe.setLayoutManager(mLayoutManager)
        //binding.recyclerContactMe.addItemDecoration(MyDividerItemDecoration(this@MorososActivity, LinearLayoutManager.VERTICAL, 0))


        binding.recyclerContactMe!!.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                binding.recyclerContactMe,
                object : RecyclerTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        val selected = listContact[position]
                        Toast.makeText(
                            this@MainActivity,
                            "posicion" + selected.tecnology,
                            Toast.LENGTH_SHORT
                        ).show()

                        if(selected.tecnology.equals("email"))
                        {
                            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                                type = "application/pdf"  // MIME type for attachments
                                putExtra(Intent.EXTRA_EMAIL, arrayOf("bzazuetar@gmail.com"))
                                putExtra(Intent.EXTRA_SUBJECT, "contacto por app")
                                putExtra(Intent.EXTRA_TEXT, "contacto por app")
                                //putExtra(Intent.EXTRA_STREAM, attachmentUri)  // Attach the file URI
                            }

                            if (emailIntent.resolveActivity(packageManager) != null) {
                                startActivity(Intent.createChooser(emailIntent, "Choose an email client"))
                            } else {
                                println("No email app found")
                            }
                        }else{
                            val text =
                                "Puedes enviarme un mensaje por ${selected.tecnology}. Utilizando las aplicaciones de mensajeria y redes sociales instaladas en el dispositivo."
                            CoroutineScope(Dispatchers.IO).launch {
                                hideSoftKeyboard(this@MainActivity)
                                val bottomSheet =
                                    BottomSheetDialog(text, selected.tecnology, tknFrb, telegramViewModel,firebaseViewModel)
                                bottomSheet.show(
                                    supportFragmentManager,
                                    "ModalBottomSheet"
                                )
                            }
                        }



                    }

                    override fun onLongClick(view: View, position: Int) {}
                })
        )

        binding.profileImage.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                hideSoftKeyboard(this@MainActivity)
                val bottomSheet =
                    BottomSheetDialogPicture("fdgf", "fgfhg", firebaseViewModel,this@MainActivity)
                bottomSheet.show(
                    supportFragmentManager,
                    "ModalBottomSheet"
                )
            }
        }



        /**experience **/
        binding.recyclerExperience.setHasFixedSize(true)
        val mLayoutManager2: RecyclerView.LayoutManager = LinearLayoutManager(
            this@MainActivity, LinearLayoutManager.VERTICAL,
            false
        )
        binding.recyclerExperience.layoutManager = mLayoutManager2
        binding.recyclerExperience.itemAnimator = DefaultItemAnimator()
        binding.recyclerExperience.setLayoutManager(mLayoutManager2)

        binding.recyclerSkills.setHasFixedSize(true)
        val mLayoutManager3: RecyclerView.LayoutManager = LinearLayoutManager(
            this@MainActivity, LinearLayoutManager.VERTICAL,
            false
        )
        val layoutManagerG = GridLayoutManager(this, 2)
        binding.recyclerSkills.layoutManager = mLayoutManager3
        binding.recyclerSkills.itemAnimator = DefaultItemAnimator()
        binding.recyclerSkills.setLayoutManager(mLayoutManager3)
        binding.recyclerSkills.setLayoutManager(layoutManagerG)

        binding.recyclerSkills?.addOnItemTouchListener(
            RecyclerTouchListener(
                this,
                binding.recyclerSkills,
                object : RecyclerTouchListener.ClickListener {
                    override fun onClick(view: View, position: Int) {
                        val selected = listFrameworksLanguage[position]
                        Toast.makeText(
                            this@MainActivity,
                            "posicion" + selected.technology,
                            Toast.LENGTH_SHORT
                        ).show()
                        if(selected.technology.equals("Api Backend"))
                        {
                            val intent = Intent(this@MainActivity, ApiActivity::class.java)
                            intent.putExtra("text1",selected.text1)
                            intent.putExtra("text2",selected.text2)
                            intent.putExtra("text3",selected.text3)
                            intent.putExtra("text4",selected.text4)
                            intent.putExtra("text5",selected.text5)
                            intent.putExtra("text6",selected.text6)
                            intent.putExtra("text7",selected.text7)
                            intent.putExtra("image",selected.image)
                            intent.putExtra("technology",selected.technology)
                            startActivity(intent)
                        }
                        else if(selected.technology.equals("Android"))
                        {
                            val intent = Intent(this@MainActivity, AndroidActivity::class.java)
                            intent.putExtra("text1",selected.text1)
                            intent.putExtra("text2",selected.text2)
                            intent.putExtra("text3",selected.text3)
                            intent.putExtra("text4",selected.text4)
                            intent.putExtra("text5",selected.text5)
                            intent.putExtra("text6",selected.text6)
                            intent.putExtra("text7",selected.text7)
                            intent.putExtra("image",selected.image)
                            intent.putExtra("technology",selected.technology)
                            startActivity(intent)
                        }
                        else if(selected.technology.equals("Firebase"))
                        {
                            val intent = Intent(this@MainActivity, FirebaseActivity::class.java)
                            intent.putExtra("text1",selected.text1)
                            intent.putExtra("text2",selected.text2)
                            intent.putExtra("text3",selected.text3)
                            intent.putExtra("text4",selected.text4)
                            intent.putExtra("text5",selected.text5)
                            intent.putExtra("text6",selected.text6)
                            intent.putExtra("text7",selected.text7)
                            intent.putExtra("image",selected.image)
                            intent.putExtra("technology",selected.technology)
                            startActivity(intent)
                        }
                        else{
                            val intent = Intent(this@MainActivity, TechnologyActivity::class.java)
                            intent.putExtra("text1",selected.text1)
                            intent.putExtra("text2",selected.text2)
                            intent.putExtra("text3",selected.text3)
                            intent.putExtra("text4",selected.text4)
                            intent.putExtra("text5",selected.text5)
                            intent.putExtra("text6",selected.text6)
                            intent.putExtra("text7",selected.text7)
                            intent.putExtra("image",selected.image)
                            intent.putExtra("technology",selected.technology)
                            startActivity(intent)
                        }

                    }

                    override fun onLongClick(view: View, position: Int) {}
                })
        )

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.userResponse.collect {
                    when (it) {
                        Resource.Loading -> {
                            //loadingState()
                        }

                        is Resource.Success -> {
                            if (it.data.respuesta.equals("Error")) {
                                Logger.d("LOG","userResponse");
                                Toast.makeText(
                                    this@MainActivity,
                                    it.data.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
                                closeLoadingState()
                            } else {
                                Logger.d("RESPONSE USER",it.data);
                                binding.shimmerLayout.stopShimmer()
                                binding.shimmerLayout.hideShimmer()
                                Glide.with(this@MainActivity).load(it.data.image)
                                    .into(binding.profileImage);
                                binding.profileImage.visibility = View.VISIBLE
                                binding.lblName.text = it.data.name + " " + it.data.lastName
                                binding.shimmerLayoutName.stopShimmer()
                                binding.shimmerLayoutName.hideShimmer()
                                binding.lblPhone.text = it.data.phone
                                binding.shimmerLayoutPhone.stopShimmer()
                                binding.shimmerLayoutPhone.hideShimmer()
                                binding.lblEmail.text = it.data.email
                                binding.shimmerLayoutEmail.stopShimmer()
                                binding.shimmerLayoutEmail.hideShimmer()
                                binding.lblYearsExperience.text = it.data.yearsExperience.toString()+ " Años de experiencia"
                                binding.shimmerLayoutYearsExperience.stopShimmer()
                                binding.shimmerLayoutYearsExperience.hideShimmer()
                                listContact = it.data.contactMe
                                tkn = it.data.token
                                contactAdappter = ContactMeAdapter(
                                    this@MainActivity, listContact
                                )
                                binding.recyclerContactMe.adapter = contactAdappter
                                contactAdappter!!.notifyDataSetChanged()
                                binding.shimmerLayoutrecyclerContactMe.stopShimmer()
                                binding.shimmerLayoutrecyclerContactMe.hideShimmer()
                                binding.shimmerLayoutrecyclerContactMe.visibility = View.GONE
                                binding.recyclerContactMe.visibility = View.VISIBLE
                                binding.lblAboutMe.text = it.data.aboutMe //+ "\n${it.data.text2} \n${it.data.text3}"
                                binding.shimmerLayoutCard1.stopShimmer()
                                binding.shimmerLayoutCard1.hideShimmer()
                                binding.shimmerLayoutCard1.visibility = View.GONE
                                binding.cardView.visibility = View.VISIBLE
                                listExperience = it.data.workExperience
                                experienceAdappter = ExperienceAdapter(
                                    this@MainActivity, listExperience,tkn
                                )
                                binding.recyclerExperience.adapter = experienceAdappter
                                experienceAdappter!!.notifyDataSetChanged()

                                listFrameworksLanguage = it.data.frameworksAndTecnology
                                skillAdappter = SkilsAdapter(
                                    this@MainActivity, listFrameworksLanguage
                                )
                                binding.recyclerSkills.adapter = skillAdappter
                                skillAdappter!!.notifyDataSetChanged()

                                closeLoadingState()
                            }

                        }

                        is Resource.Failure -> {
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                            closeLoadingState()
                        }

                        else -> {
                            if (it != null) {
                                Toast.makeText(
                                    this@MainActivity,
                                    "Hubo error desconocido",
                                    Toast.LENGTH_SHORT
                                ).show()
                                closeLoadingState()
                            }
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            telegramViewModel.telegramResponse.collect {
                when (it) {
                    Resource.Loading ->{

                    }
                    is Resource.Success->{
                        showSnackBar(it.data.msg)
                    }
                    is Resource.Failure->{
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                        closeLoadingState()
                    }
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            firebaseViewModel.firebaseResponse.collect {
                when (it) {
                    Resource.Loading ->{
                      loadingState()
                    }
                    is Resource.Success->{
                        showSnackBarFirebase(it.data)
                    }
                    is Resource.Failure->{
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                        closeLoadingState()
                    }
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            firebaseViewModel.firebaseResponseImage.collect {
                when (it) {
                    Resource.Loading ->{
                        loadingState()
                    }
                    is Resource.Success->{
                        showSnackBarGeneric(it.data)
                    }
                    is Resource.Failure->{
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                        closeLoadingState()
                    }
                    else -> {}
                }
            }
        }

        lifecycleScope.launch {
            firebaseViewModel.firebaseResponseImage.collect {
                when (it) {
                    Resource.Loading ->{
                        loadingState()
                    }
                    is Resource.Success->{
                        showSnackBarGeneric(it.data)
                    }
                    is Resource.Failure->{
                        Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                        closeLoadingState()
                    }
                    else -> {}
                }
            }
        }

        copyViewModel.textToCopy.observe(this, Observer { text ->
            copyTextToClipboard(text)
        })

        binding.imageDownloadCv.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this@MainActivity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this@MainActivity, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)

            }else{
                CoroutineScope(Dispatchers.IO).launch {

                    // do your Coroutine Stuff here, i.e. call a suspend fun:
                    //val url ="https://sycnos.com/heybanco_qa/public/storage/fmensajes/yOYWhlub9AqCsCFiD4c0bSfWBmuOVfNSKG8SIuDz.png"
                    //val downloadedFile = downloadFile(this@SpinnerActivity,url,"image.png")
                    val downloadedFile = downloadImage(this@MainActivity,Config.URL_FILE_CV_PDF,"curriculumbzr.pdf")

                    if (downloadedFile != null) {
                        //Toast.makeText(this@SpinnerActivity, "File downloaded to: ${downloadedFile.absolutePath}", Toast.LENGTH_LONG).show()
                        runOnUiThread {
                            toast()
                        }

                    } else {
                        runOnUiThread {
                            toastFailed()
                        }
                    }

                }
            }

        }

        binding.imageCopy.setOnClickListener {
            copyViewModel.setTextToCopy(binding.lblName.text.toString())
        }
        binding.imageCopy2.setOnClickListener {
            copyViewModel.setTextToCopy(binding.lblEmail.text.toString())
        }

        binding.imageSettings.setOnClickListener {
            val intent = Intent(this@MainActivity,SettingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result
            tknFrb = token
            // Log and toast
            val msg = token
            Log.d(TAG, msg)
            //Toast.makeText(baseContext, token.toString(), Toast.LENGTH_SHORT).show()
        })
        super.onResume()

    }

    // Declare the launcher at the top of your Activity/Fragment:
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission(),
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.


        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }


    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

    }

    private fun loadingState() {
        binding.pb.isVisible = true
    }

    private fun closeLoadingState() {
        binding.pb.isVisible = false
    }

    fun showSnackBar(msg: String) {
        Snackbar.make(binding.constraint, msg, Snackbar.LENGTH_SHORT).show()
    }

    fun showSnackBarFirebase(msg: FirebaseNotificationModel) {
        if(msg.respuesta.equals("Ok"))
        {
            Snackbar.make(binding.constraint, R.string.notification_success, Snackbar.LENGTH_SHORT).show()
        }
        else
        {
            Snackbar.make(binding.constraint, R.string.notification_failure, Snackbar.LENGTH_SHORT).show()
        }
    }


    fun showSnackBarGeneric(msg: ResponseModel) {
        if(msg.respuesta.equals("Ok"))
        {
            Snackbar.make(binding.constraint, msg.msg, Snackbar.LENGTH_SHORT).show()
        }
        else
        {
            Snackbar.make(binding.constraint, msg.msg, Snackbar.LENGTH_SHORT).show()
        }
    }

    fun hideSoftKeyboard(activity: Activity?) {
        activity?.let {
            val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            imm.let {
                val currentFocus = activity.currentFocus
                currentFocus?.let {
                    imm.hideSoftInputFromWindow(it.windowToken, 0)
                }
            }
        }
    }

    fun downloadImage(context: Context, url: String, fileName: String) {
        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(fileName)
            .setDescription("Downloading image...")
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)

        val downloadManager = context.getSystemService(AppCompatActivity.DOWNLOAD_SERVICE) as DownloadManager

        downloadManager.enqueue(request)
        runOnUiThread {
            Toast.makeText(context, "Download started...", Toast.LENGTH_SHORT).show()
        }
    }

    fun toast(){
        //Toast.makeText(this@SpinnerActivity, "File downloaded to: ${downloadedFile.absolutePath}", Toast.LENGTH_LONG).show()
        Toast.makeText(this@MainActivity, "Se ha descargado el archivo...", Toast.LENGTH_LONG).show()

    }

    fun toastFailed(){
        Toast.makeText(this@MainActivity, "Descarga fallida...", Toast.LENGTH_LONG).show()

    }

    private fun copyTextToClipboard(text: String) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Texto Copiado", text)
        clipboard.setPrimaryClip(clip)

        // Notify the user with a Toast
        Toast.makeText(this, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
        showSnackBarTextCopy(text)
    }

    fun showSnackBarTextCopy(msg: String) {
        Snackbar.make(binding.constraint, msg, Snackbar.LENGTH_SHORT).show()
    }
}