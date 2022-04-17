package org.metabrainz.android.presentation.features.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thefinestartist.finestwebview.FinestWebView
import org.metabrainz.android.R
import org.metabrainz.android.databinding.ActivityDashboardBinding
import org.metabrainz.android.presentation.features.about.AboutActivity
import org.metabrainz.android.presentation.features.collection.CollectionActivity
import org.metabrainz.android.presentation.features.login.LoginActivity
import org.metabrainz.android.presentation.features.navigation.NavigationItem
import org.metabrainz.android.presentation.features.newsbrainz.NewsBrainzActivity
import org.metabrainz.android.presentation.features.search.SearchActivity
import org.metabrainz.android.presentation.features.settings.SettingsActivity
import org.metabrainz.android.presentation.features.tagger.TaggerActivity
import org.metabrainz.android.ui.component.BottomBar

class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.bottomNav.setContent {
            BottomBar(this)
        }
        binding.topAppBar.setContent {
            TopAppBar()
        }

        //Navigation
        binding.dashboardTagId.setOnClickListener {
            startActivity(Intent(this, TaggerActivity::class.java))
        }
        binding.dashboardSearchId.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
        binding.dashboardCollectionId.setOnClickListener {
            startActivity(Intent(this, CollectionActivity::class.java))
        }

        //CardView animation
        val leftItemAnimation = AnimationUtils.loadAnimation(this, R.anim.left_dashboard_item_animation)
        val rightItemAnimation = AnimationUtils.loadAnimation(this, R.anim.right_dashboard_item_animation)
        binding.dashboardTagId.animation = leftItemAnimation
        binding.dashboardSearchId.animation = rightItemAnimation
        binding.dashboardCollectionId.animation = leftItemAnimation
    }

    @Composable
    fun TopAppBar() {
        TopAppBar(
            title = {
                Text(text = "MusicBrainz")
            },
            backgroundColor = colorResource(id = R.color.app_bg),
            contentColor = colorResource(id = R.color.white),
            elevation = 2.dp,
            actions = {
                IconButton(onClick = {
                    startActivity(Intent(applicationContext, AboutActivity::class.java))
                }) {
                    Icon(painterResource(id = R.drawable.ic_information), "About", tint = Color.Unspecified)
                }
                IconButton(onClick = {
                    startActivity(Intent(applicationContext, DonateActivity::class.java))
                }) {
                    Icon(painterResource(id = R.drawable.ic_donate), "Donate", tint = Color.Unspecified)
                }
                IconButton(onClick = {
                    startActivity(Intent(applicationContext, SettingsActivity::class.java))
                }) {
                    Icon(painterResource(id = R.drawable.action_settings), "Settings", tint = Color.Unspecified)
                }
            }
        )
    }



    @Preview(showBackground = true)
    @Composable
    fun TopAppBarPreview() {
        TopAppBar()
    }
}