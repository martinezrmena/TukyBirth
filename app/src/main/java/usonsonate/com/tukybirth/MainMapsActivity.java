package usonsonate.com.tukybirth;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import usonsonate.com.tukybirth.Maps.GetNearbyPlaces;

public class MainMapsActivity extends AppCompatActivity implements
        GoogleMap.OnMarkerClickListener,GoogleMap.OnMarkerDragListener,OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener  {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private Location lastLocation;
    private Marker currentUserLocationMarker;
    private static final int Request_User_Location_Code = 99;
    private double latitide, longitude;
    private int ProximityRadius = 10000;
    CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_maps);

        //Aplicando formato a los botones circulares
        circleMenu = findViewById(R.id.circle_menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            checkUserLocationPermission();
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setTitle(R.string.mapname);


        circleMenu.setMainMenu(Color.parseColor("#370A54"), R.drawable.add, R.drawable.cross)
                .addSubMenu(Color.parseColor("#00171F"), R.drawable.hospital)
                .addSubMenu(Color.parseColor("#ffffff"), R.drawable.map_hibrid)
                .addSubMenu(Color.parseColor("#f44336"), R.drawable.map_normal)
                .addSubMenu(Color.parseColor("#7c4dff"), R.drawable.terrain)
                .addSubMenu(Color.parseColor("#B1740F"), R.drawable.satellite)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        OpcionSeleccionada(index);
                    }

                });


    }

    public void OpcionSeleccionada(int i){

        String hospital = "hospital";
        Object transferData[] = new Object[2];
        GetNearbyPlaces getNearbyPlaces = new GetNearbyPlaces();

        switch (i){
            case 0:
                if(isOnlineNet()){
                    mMap.clear();
                    String url = getUrl(latitide, longitude, hospital);
                    transferData[0] = mMap;
                    transferData[1] = url;

                    getNearbyPlaces.execute(transferData);
                    Toast.makeText(this, "Buscando hospitales cercanos...", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "Mostrando hospitales cercanos...", Toast.LENGTH_SHORT).show();
                }
                break;


            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                Toast.makeText(this, "Selecciono el estilo de mapa hibrido.", Toast.LENGTH_SHORT).show();
                break;


            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                Toast.makeText(this, "Selecciono el estilo de mapa normal.", Toast.LENGTH_SHORT).show();
                break;

            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                Toast.makeText(this, "Selecciono el estilo de mapa terrestre.", Toast.LENGTH_SHORT).show();
                break;


            case 4:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                Toast.makeText(this, "Selecciono el estilo de mapa satelital.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.search_address:
                if (isOnlineNet()){

                    EditText addressField = findViewById(R.id.location_search);
                    String address = addressField.getText().toString();
                    addressField.setText("");

                    List<Address> addressList = null;
                    MarkerOptions userMarkerOptions = new MarkerOptions();

                    if (!TextUtils.isEmpty(address))
                    {
                        Geocoder geocoder = new Geocoder(this);

                        try
                        {
                            addressList = geocoder.getFromLocationName(address, 6);

                            if (addressList != null)
                            {
                                for (int i=0; i<addressList.size(); i++)
                                {
                                    Address userAddress = addressList.get(i);
                                    LatLng latLng = new LatLng(userAddress.getLatitude(), userAddress.getLongitude());

                                    userMarkerOptions.position(latLng);
                                    userMarkerOptions.title(address);
                                    userMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                                    mMap.addMarker(userMarkerOptions);
                                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                                    mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                                }
                            }
                            else
                            {
                                Toast.makeText(this, "Locación no encontrada...", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                    }
                    else
                    {
                        Toast.makeText(this, "Por favor escribe el nombre de una locación...", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }

    private String getUrl(double latitide, double longitude, String nearbyPlace)
    {
        StringBuilder googleURL = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googleURL.append("location=" + latitide + "," + longitude);
        googleURL.append("&radius=" + ProximityRadius);
        googleURL.append("&type=" + nearbyPlace);
        googleURL.append("&sensor=true");
        googleURL.append("&key=" + "AIzaSyDFVQTj5gvRK9iBgsSJi5EWwb_8EZgm5Ng");

        Log.d("GoogleMapsActivity", "url = " + googleURL.toString());

        return googleURL.toString();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            buildGoogleApiClient();

            mMap.setMyLocationEnabled(true);
        }


        //Evento para arrastrar las señalizaciones
        googleMap.setOnMarkerDragListener(this);

        //Evento para pulsar las señalizaciones
        googleMap.setOnMarkerClickListener(this);

    }

    public boolean checkUserLocationPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, Request_User_Location_Code);
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        switch (requestCode)
        {
            case Request_User_Location_Code:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                    {
                        if (googleApiClient == null)
                        {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                }
                else
                {
                    Toast.makeText(this, "Permission Denied...", Toast.LENGTH_SHORT).show();
                }
                return;
        }
    }

    protected synchronized void buildGoogleApiClient()
    {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location)
    {
        latitide = location.getLatitude();
        longitude = location.getLongitude();

        lastLocation = location;

        if (currentUserLocationMarker != null)
        {
            currentUserLocationMarker.remove();
        }

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Locación actual");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        markerOptions.draggable(true);

        currentUserLocationMarker = mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(12));

        if (googleApiClient != null)
        {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle)
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1100);
        locationRequest.setFastestInterval(1100);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        }


    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    //EVENTOS PARA EL ARRASTRE DE LA LOCACIÓN ACTUAL
    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {
        String newTitle = String.format(Locale.getDefault(),
                getString(R.string.markerdetaillatlng),
                this.currentUserLocationMarker.getPosition().longitude,
                this.currentUserLocationMarker.getPosition().latitude
                );

        setTitle("Locación: " + newTitle);

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        setTitle(R.string.mapname);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        String newTitle = String.format(Locale.getDefault(),
                getString(R.string.markerdetaillatlng),
                marker.getPosition().longitude,
                marker.getPosition().latitude
        );
        Toast.makeText(this, newTitle, Toast.LENGTH_SHORT).show();
        return true;
    }

    public Boolean isOnlineNet() {

        try {
            Process p = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.es");

            int val = p.waitFor();
            boolean reachable = (val == 0);
            return reachable;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Toast.makeText(this, "Revise su conexión a internet.", Toast.LENGTH_SHORT).show();
        return false;
    }

}
