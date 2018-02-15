package com.artur.sms.Utility

import android.os.AsyncTask

/**
 * Created by Artur on 2/2/2018.
 */
class AsyncTask<Result>(private val backgroundTask:()->Result, private val onComplete: ((result: Result) -> Unit)? = null): AsyncTask<Unit, Unit, Result>(){
    override fun doInBackground(vararg p0: Unit?): Result {
       return backgroundTask.invoke()
    }
    override fun onPostExecute(result: Result) {
        super.onPostExecute(result)
        onComplete?.invoke(result)
    }
}