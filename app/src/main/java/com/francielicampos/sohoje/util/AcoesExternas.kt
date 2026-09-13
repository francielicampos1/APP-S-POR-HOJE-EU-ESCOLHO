package com.francielicampos.sohoje.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun abrirUrl(context: Context, url: String) {
    context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}

fun abrirBuscaGoogle(context: Context, termo: String) {
    val url = "https://www.google.com/search?q=" + Uri.encode(termo)
    abrirUrl(context, url)
}

fun abrirBuscaMaps(context: Context, termo: String) {
    val uri = Uri.parse("geo:0,0?q=" + Uri.encode(termo))
    val intent = Intent(Intent.ACTION_VIEW, uri)
    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        abrirBuscaGoogle(context, "$termo perto de mim")
    }
}

fun compartilharApp(context: Context) {
    val texto = "Conheça o app \"Só Por Hoje, Eu Não Aposto\" — um espaço de apoio pra quem quer parar de apostar, ou pra quem quer ajudar alguém que está passando por isso. 🌱"
    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_TEXT, texto)
    }
    context.startActivity(Intent.createChooser(intent, "Compartilhar app"))
}

fun abrirWhatsApp(context: Context, telefone: String, mensagem: String = "") {
    val numero = telefone.filter { it.isDigit() }
    val base = "https://wa.me/55$numero"
    val url = if (mensagem.isNotBlank()) "$base?text=" + Uri.encode(mensagem) else base
    abrirUrl(context, url)
}

fun abrirDiscador(context: Context, telefone: String) {
    val uri = Uri.parse("tel:" + telefone.filter { it.isDigit() || it == '+' })
    context.startActivity(Intent(Intent.ACTION_DIAL, uri))
}
