package uk.co.stellar.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomappbar.BottomAppBar
import dagger.hilt.android.AndroidEntryPoint
import uk.co.stellar.R

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val bottomAppBar: BottomAppBar
        get() = findViewById(R.id.bottom_app_bar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        bottomAppBar.setupWithNavController(navController)

        bottomAppBar.setOnMenuItemClickListener { menuItem ->
            menuItem.onNavDestinationSelected(navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.bottom_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.dashboard -> {
                Toast.makeText(this, "Search clicked!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> false
        }
    }
}