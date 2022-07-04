package com.moviles.marketplace

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.messaging.FirebaseMessaging
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.api.UserRepository
import com.moviles.marketplace.databinding.ActivityMainBinding
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.models.User
import retrofit2.http.GET

class MainActivity : AppCompatActivity(), ProductRepository.GetProductByUserListener, UserRepository.GetUserListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        sharedPref.setToken("52|DeXTcChmhXz2votQKqn4L9EIHZhwcim50BMvy2DU")

        setupFirebaseToken()
    }

    override fun onResume() {
        super.onResume()
        runAllFetch()
    }

    private fun setupFirebaseToken() { // TODO setupFirebaseToken() se tiene que poner en el Login
        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Error al obtener el token de registro de FCM", task.exception)
                return@addOnCompleteListener
            }

            val token = task.result

            Log.d("TOKEN_FIREBASE", token)
        }
    }

    fun runAllFetch(){
        fetchProduct()
        fetchUser()
    }
    //@GET("/api/me") fun getUser()
    private fun fetchUser() {
        UserRepository().getUser(this)
    }
    override fun getUserReady(user: User) {
        Log.d("response_api", user.toString())
    }

    override fun onUserGetError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

    //@GET("/api/products") fun getProductUserLoged()
    private fun fetchProduct() {
        ProductRepository().getProduct(this)
    }
    override fun getProductByUserReady(products: ArrayList<Product>) {
        Log.d("response_api", products.toString())
    }
    override fun onProductByUserGetError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }


}