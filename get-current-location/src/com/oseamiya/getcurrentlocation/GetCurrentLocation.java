package com.oseamiya.getcurrentlocation;

import android.content.Context;
import android.Manifest;
import android.content.pm.PackageManager;
import android.app.Activity;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;

import androidx.core.app.ActivityCompat;

public class GetCurrentLocation extends AndroidNonvisibleComponent {
  private Activity activity;
  private Context context;
  private LocationInformation locationInformation;
  public GetCurrentLocation(ComponentContainer container) {
    super(container.$form());
    this.context = container.$context();
    this.activity = (Activity) container.$context();
    this.locationInformation = new LocationInformation(container.$context());
  }
  @SimpleFunction(description ="To check if Location Services is enabled or not")
  public boolean IsLocationServicesEnabled() {
    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
      return locationManager.isLocationEnabled();
    } else {
      int mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE, Settings.Secure.LOCATION_MODE_OFF);
      return mode == Settings.Secure.LOCATION_MODE_OFF ? false : true;
    }
  }
  @SimpleFunction
  public void AskRequiredPermissions() {
    if (ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
      ActivityCompat.requestPermissions(this.activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
  }
  }
  @SimpleFunction
  public boolean IsAllPermissionAccepted(){
    return ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
  }
  @SimpleFunction
  public void GetCurrentLatitude(){
    GotCurrentLatitude(String.valueOf(this.locationInformation.getCurrentLatitude()));
  }
  @SimpleEvent
  public void GotCurrentLatitude(String latitude){
    EventDispatcher.dispatchEvent(this, "GotCurrentLatitude", latitude);
  }
  @SimpleFunction
  public void GetCurrentLongitude(){
    GotCurrentLongitude(String.valueOf(this.locationInformation.getCurrentLongitude()));
  }
  @SimpleEvent
  public void GotCurrentLongitude(String longitude){
    EventDispatcher.dispatchEvent(this, "GotCurrentLongitude", longitude);
  }
  @SimpleFunction
  public String GetStreetAddress(String latitude, String longitude){
    return locationInformation.getStreetAddress(Double.parseDouble(latitude) , Double.parseDouble(longitude));
  }
  @SimpleFunction
  public String GetCity(String latitude, String longitude){
    return locationInformation.getCity(Double.parseDouble(latitude) , Double.parseDouble(longitude));
  }
  @SimpleFunction
  public String GetPostalCode(String latitude, String longitude){
    return locationInformation.getPostalCode(Double.parseDouble(latitude) , Double.parseDouble(longitude));
  }
  @SimpleFunction
  public String GetCountryName(String latitude , String longitude){
    return locationInformation.getCountryName(Double.parseDouble(latitude) , Double.parseDouble(longitude));
  }
}
