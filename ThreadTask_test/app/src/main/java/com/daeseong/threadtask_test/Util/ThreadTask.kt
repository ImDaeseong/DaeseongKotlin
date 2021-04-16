package com.daeseong.threadtask_test.Util

abstract class ThreadTask<T1, T2> : Runnable {

    private var mParam: T1? = null
    private var mResult: T2? = null

    fun execute(Param: T1) {
        mParam = Param
        val thread = Thread(this)
        thread.start()
        try {
            thread.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
            onPostExecute(null)
            return
        }
        onPostExecute(mResult)
    }

    override fun run() {
        mResult = doInBackground(mParam)
    }

    protected abstract fun doInBackground(Param: T1?): T2
    protected abstract fun onPostExecute(Result: T2?)
}