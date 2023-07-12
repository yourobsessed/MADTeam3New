package sg.edu.np.mad;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;

public class MapPage extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private MapboxMap mapboxMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "sk.eyJ1IjoiY29saW5jaGFuZzEiLCJhIjoiY2xqMmJtM2lvMTI5NjNncDk1ZmowcTNpOSJ9.KEOtKofPYsAoJ6_G4OZkbg");
        setContentView(R.layout.activity_map_page);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        ImageView BackButton = findViewById(R.id.imageView);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;

        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);
                mapboxMap.setCameraPosition(
                        new CameraPosition.Builder()
                                .target(new LatLng(1.3330002217556243, 103.77539301191698))
                                .zoom(16)
                                .build()
                );

                MarkerOptions mpmarker = new MarkerOptions()
                        .position(new LatLng(1.3319990257787873, 103.77473837340155))
                        .icon(IconFactory.getInstance(MapPage.this).fromResource(R.drawable.mpmarker));
                mapboxMap.addMarker(mpmarker);
                MarkerOptions mmarker = new MarkerOptions()
                        .position(new LatLng(1.3319319886684464, 103.77688414058471))
                        .icon(IconFactory.getInstance(MapPage.this).fromResource(R.drawable.mmarker));
                mapboxMap.addMarker(mmarker);
                MarkerOptions fcmarker = new MarkerOptions()
                        .position(new LatLng(1.3343284438398109, 103.77561425385555))
                        .icon(IconFactory.getInstance(MapPage.this).fromResource(R.drawable.fcmarker));
                mapboxMap.addMarker(fcmarker);

            }
        });
    }

    private void enableLocationComponent(@NonNull Style style) {
        // Create an instance of LocationComponentActivationOptions.Builder
        LocationComponentActivationOptions.Builder locationComponentActivationOptionsBuilder =
                LocationComponentActivationOptions.builder(this, style);

        // Build the LocationComponentActivationOptions
        LocationComponentActivationOptions locationComponentActivationOptions =
                locationComponentActivationOptionsBuilder.build();

        // Activate the LocationComponent with the activation options
        LocationComponent locationComponent = mapboxMap.getLocationComponent();
        locationComponent.activateLocationComponent(locationComponentActivationOptions);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationComponent.setLocationComponentEnabled(true);
        locationComponent.setCameraMode(CameraMode.TRACKING);
        locationComponent.setRenderMode(RenderMode.COMPASS);
    }

    // Other lifecycle methods for the MapView

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}