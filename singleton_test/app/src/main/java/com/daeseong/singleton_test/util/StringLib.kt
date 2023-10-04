class StringLib private constructor() {

    companion object {
        private var instance: StringLib? = null

        fun getInstance(): StringLib {
            if (instance == null) {
                instance = StringLib()
            }
            return instance!!
        }
    }

    fun isBlank(str: String?): Boolean {
        return str.isNullOrBlank()
    }

    fun getSubString(str: String?, max: Int): String? {
        return str?.take(max)
    }
}
