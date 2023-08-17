//package com.utlis
//
//import android.content.Context
//import android.graphics.Bitmap
//import android.graphics.BitmapFactory
//import com.example.myapplication.R
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.async
//
//class CorutineTest {
//
//    suspend fun convertResToBitmap(application: Context): ArrayList<Bitmap> {
//        var arraylist = ArrayList<Bitmap>()
//
//        val bitmap = CoroutineScope(Dispatchers.IO).async {
//
//            val icon = BitmapFactory.decodeResource(
//                application.resources,
//                R.drawable.test2
//            )
//            val icon2 = BitmapFactory.decodeResource(
//                application.resources,
//                R.drawable.test_3
//            )
//            val icon3 = BitmapFactory.decodeResource(
//                application.resources,
//                R.drawable.appbg
//            )
//            val icon4 = BitmapFactory.decodeResource(
//                application.resources,
//                R.drawable.test_im
//            )
////            val icon5 = BitmapFactory.decodeResource(
////                application.resources,
////                R.drawable.test_im
////            )
////            val icon6 = BitmapFactory.decodeResource(
////                application.resources,
////                R.drawable.test_im
////            )
//
//            arraylist.add(icon)
//            arraylist.add(icon2)
//            arraylist.add(icon3)
//            arraylist.add(icon4)
//
//
//        }
//        bitmap.await()
//        return arraylist
//
//
//    }
//
//
//}