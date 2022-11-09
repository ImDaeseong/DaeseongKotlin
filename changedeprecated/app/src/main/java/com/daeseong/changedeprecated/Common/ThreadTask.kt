package com.daeseong.changedeprecated.Common


abstract class ThreadTask<T1, T2> : Runnable {

    private var mParam: T1? = null
    private var mResult: T2? = null

    fun execute(Param: T1): T2? {
        mParam = Param
        val thread = Thread(this)
        thread.start()
        try {
            thread.join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        //onPostExecute(mResult)
        return mResult
    }

    override fun run() {

        //스레드 처리 내용 호출
        mResult = doInBackground(mParam)
    }

    //스레드 처리 내용 호출
    protected abstract fun doInBackground(Param: T1?): T2 //작업 완료후 최종 호출
    //protected abstract fun onPostExecute(Result: T2?)
}
