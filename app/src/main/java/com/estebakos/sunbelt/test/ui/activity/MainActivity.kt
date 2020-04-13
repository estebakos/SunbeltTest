package com.estebakos.sunbelt.test.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.estebakos.sunbelt.test.R
import com.estebakos.sunbelt.test.ui.viewmodel.AnimeViewModel
import com.estebakos.sunbelt.test.ui.viewmodel.AnimeViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    @Inject
    lateinit var animeViewModelFactory: AnimeViewModelFactory
    private lateinit var animeViewModel: AnimeViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        animeViewModel = ViewModelProvider(
            this, animeViewModelFactory
        ).get(AnimeViewModel::class.java)

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.navigation_anime_list))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchItem: MenuItem? = menu.findItem(R.id.search)
        searchView = searchItem?.actionView as SearchView
        defineSearchListener()
        return true
    }

    private fun defineSearchListener() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty() && query.length >= 3) {
                    animeViewModel.searchAnime(query)
                }

                return false
            }


            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    animeViewModel.loadItems()
                }
                return false
            }

        })
    }
}
