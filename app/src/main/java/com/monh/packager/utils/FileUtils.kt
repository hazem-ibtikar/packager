package com.monh.packager.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Base64
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


fun storeToPdfAndOpen(context: Context, base: String?) {
    val root: String = Environment.getExternalStorageDirectory().toString()
    Log.d("ResponseEnv", root)
    val myDir = File("$root/WorkBox")
    if (!myDir.exists()) {
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
        val pdfAsBytes: ByteArray = Base64.decode(base, Base64.NO_WRAP)
        out.write(pdfAsBytes)
        out.flush()
        out.close()
    } catch (e: Exception) {
        e.printStackTrace()
    }

    val dir = File(Environment.getExternalStorageDirectory(), "WorkBox")
    val imgFile = File(dir, fname)

    // create cashed file
    createCachedFile(context, fname, imgFile)


    val sendIntent = Intent(Intent.ACTION_VIEW)
    val uri: Uri
    uri = Uri.parse(
        "content://" + CachedFileProvider.AUTHORITY + "/"
                + fname
    )
    sendIntent.setDataAndType(uri, "application/pdf")
    sendIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    context.startActivity(sendIntent)
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