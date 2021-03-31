package com.techs.mapapp.map

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.Observer
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.PropertyFactory
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.mapbox.mapboxsdk.utils.BitmapUtils
import com.techs.mapapp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import org.koin.android.viewmodel.ext.android.viewModel


class MapActivity : AppCompatActivity(), MapboxMap.OnMapClickListener {
    private val viewModel: MapViewModel by viewModel()

    private val ICON_SOURCE_ID = "ICON_SOURCE_ID"
    private val ICON_ID = "ICON_ID"
    private val ICON_LAYER_ID = "ICON_LAYER_ID"
    private var mapBoxMap: MapboxMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
        setContentView(R.layout.activity_main)
        pbMap.indeterminateDrawable.setColorFilter(
            Color.BLACK,
            android.graphics.PorterDuff.Mode.MULTIPLY
        )
        initViews()
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { mapBoxMap ->
            mapBoxMap.addOnMapClickListener(this@MapActivity)
            this.mapBoxMap = mapBoxMap
            observeViewState()
            viewModel.getPinsDetails()
        }
    }

    fun initViews() {
        bsContent.movementMethod = ScrollingMovementMethod()
    }

    fun observeViewState() {
        viewModel.viewState.observe(this, Observer {

            if (it != null) {
                initMapBox(it.pins)
                handleViewState(it)
            }
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                Toast.makeText(this, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun initMapBox(pins: List<Feature>?) {
        mapBoxMap?.setStyle(
            Style.Builder()
                .fromUri(Style.MAPBOX_STREETS) // Add the SymbolLayer icon image to the map style
                .withImage(
                    ICON_ID, BitmapUtils.getBitmapFromDrawable(
                        ResourcesCompat.getDrawable(
                            resources,
                            R.drawable.ic_taxi,
                            null
                        )
                    )!!
                ) // Adding a GeoJson source for the SymbolLayer icons.
                .withSource(
                    GeoJsonSource(
                        ICON_SOURCE_ID,
                        pins?.let { FeatureCollection.fromFeatures(it) }

                    )
                )
                .withLayer(
                    SymbolLayer(ICON_LAYER_ID, ICON_SOURCE_ID)
                        .withProperties(
                            PropertyFactory.iconImage(ICON_ID),
                            iconOffset(arrayOf(0f, -9f))
                        )
                )
        )
    }

    private fun handleViewState(state: MapViewState) {
        pbMap.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        state.pin?.let {
            if (bottomSheet.visibility == View.GONE) bottomSheet.visibility = View.VISIBLE
            bsTitle.text = it.title
            bsContent.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(it.description, Html.FROM_HTML_MODE_COMPACT)
            } else {
                Html.fromHtml(it.description)
            }
        }
    }


    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onMapClick(point: LatLng): Boolean {
        val screenPoint = mapBoxMap!!.projection.toScreenLocation(point)
        val features: List<Feature> = mapBoxMap!!.queryRenderedFeatures(screenPoint, ICON_LAYER_ID)
        if (features.isNotEmpty()) {
            val feature = features[0]
            viewModel.getPinInfo(feature)
        }
        return true
    }
}