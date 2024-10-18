package com.monosoft.myprofile.domain.models

// To parse the JSON, install kotlin's serialization plugin and do:
//
// val json  = Json { allowStructuredMapKeys = true }
// val login = json.parse(Login.serializer(), jsonString)


import com.google.gson.annotations.SerializedName


data class LoginModel (
    var result: List<Result>
)


data class Result (
    @SerializedName("Respuesta")
    val respuesta: String,

    val id: String,
    val usuario: String,
    val apellidop: String,
    val apellidom: String,
    val telefono: String,
    val correo: String,
    val password: String,
    val direccion: String,

    @SerializedName("id_rol")
    val idRol: String,

    @SerializedName("id_jefe_calle")
    val idJefeCalle: String,

    val calles: List<Calle>,
    val imagen: String,

    @SerializedName("numero_casa")
    val numeroCasa: String,

    val roles: List<Role>,
    val colonia: String,

    @SerializedName("id_colonia")
    val idColonia: String,

    @SerializedName("id_calle")
    val idCalle: String,

    @SerializedName("nombre_colonia")
    val nombreColonia: String,

    @SerializedName("token_uuid")
    val tokenUUID: String,

    val version: String,

//    @SerializedName("num_logueado")
//    val numLogueado: JsonElement? = null,

    @SerializedName("cofecha_creacion")
    val cofechaCreacion: String,

    @SerializedName("api_stripe")
    val apiStripe: String,

    val titular: String,

    @SerializedName("is_available")
    val isAvailable: String,

    @SerializedName("politica_aceptada")
    val politicaAceptada: String,

    @SerializedName("terminos_aceptada")
    val terminosAceptada: String,

    @SerializedName("representantes_calles")
    val representantesCalles: List<RepresentantesCalle>,

    @SerializedName("id_representante_calle")
    val idRepresentanteCalle: String,

    @SerializedName("Msg")
     val Msg: String,
)


data class Calle (
    val id: String,
    val colonia: String,
    val calle: String
)


data class RepresentantesCalle (
    @SerializedName("id_usuario")
    val idUsuario: String,
    val id: String,
    val nombre: String,
    val calle: String
)

data class Role (
    val id: String,
    val rol: String
)
