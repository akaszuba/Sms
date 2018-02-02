package com.artur.sms

import android.os.AsyncTask

/**
 * Created by Artur on 2/2/2018.
 */
class MyAsync<Result>(val backgroundTask:()->Result, val onComplete: ((result: Result) -> Unit)? = null): AsyncTask<Unit, Unit, Result>(){
    override fun doInBackground(vararg p0: Unit?): Result {
       return backgroundTask.invoke()
    }

    override fun onPostExecute(result: Result) {
        super.onPostExecute(result)
        if(onComplete != null) {
            onComplete.invoke(result);
        }
    }

}