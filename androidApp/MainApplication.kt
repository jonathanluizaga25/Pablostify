class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
    }
}
