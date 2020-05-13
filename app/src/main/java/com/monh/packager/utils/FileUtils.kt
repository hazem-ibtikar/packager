package com.monh.packager.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.monh.packager.R
import com.monh.packager.ui.home.HomeActivity
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*

const val APPLICATION_FOLDER_NAME = "/monh packager/"
// The path of the media directory relative to the external directory.
fun getMediaFilePath() = Environment.DIRECTORY_DCIM + APPLICATION_FOLDER_NAME

fun storeToPdfAndOpen(context: Context, base: String?) {

    val myDir = context.getExternalFilesDir(getMediaFilePath())
    if (!myDir?.exists()!!) {
        myDir.mkdirs()
    }
    val generator = Random()
    var n = 10000
    n = generator.nextInt(n)
    val fname = "Attachments-$n.pdf"
    val file = File(myDir, fname)
    if (file.exists()) file.delete()
    try {
        val out = FileOutputStream(file)
        val pdfAsBytes: ByteArray = Base64.decode(base, Base64.DEFAULT)
        out.write(pdfAsBytes)
        out.flush()
        out.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    // create cashed file
    createCachedFile(context, fname, file)


    val sendIntent = Intent(Intent.ACTION_VIEW)
    val uri: Uri
    uri = Uri.parse(
        "content://" + CachedFileProvider.AUTHORITY + "/"
                + fname
    )
    sendIntent.setDataAndType(uri, "application/pdf")
    sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    if (sendIntent.resolveActivity(context.packageManager) != null){
        context.startActivity(sendIntent)
    } else {
        Toast.makeText(context, context.getString(R.string.no_pdf_app_installed), Toast.LENGTH_SHORT).show()
    }
}

@Throws(IOException::class)
fun createCachedFile(
    context: Context, fileName: String,
    content: File?
) {
    val cacheFile = File(
        context.cacheDir.toString() + File.separator
                + fileName
    )
    content?.copyTo(cacheFile, true)
}