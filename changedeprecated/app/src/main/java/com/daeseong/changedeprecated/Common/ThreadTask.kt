import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

abstract class ThreadTask<T1, T2> {

    private var param: T1? = null

    fun execute(Param: T1): Future<T2?> {
        param = Param
        val executor = Executors.newSingleThreadExecutor()
        return executor.submit(Callable<T2?> {
            doInBackground(param)
        })
    }

    // 스레드 처리 내용 호출
    protected abstract fun doInBackground(Param: T1?): T2 // 작업 완료 후 최종 호출
}
