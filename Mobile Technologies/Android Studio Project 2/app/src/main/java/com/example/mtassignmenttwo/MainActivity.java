package com.example.mtassignmenttwo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.action_normal) {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            return true;
        }

        if (id == R.id.action_satellite) {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng UC = new LatLng(-35.238455, 149.084446);
        LatLng library = new LatLng(-35.238030, 149.083405);
        LatLng lodge = new LatLng(-35.238849, 149.082214);
        LatLng resource = new LatLng(-35.236428, 149.084288);
        LatLng hub = new LatLng(-35.238440,149.084520);
        LatLng gym = new LatLng(-35.238318, 149.088285);
        LatLng streetTop = new LatLng(-35.233653, 149.087192);
        LatLng streetRight = new LatLng(-35.238516, 149.089560);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UC, 13));

        final Marker libraryMarker = googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_library)).position(library)
                .flat(true).title("UC Library").snippet("24 hour access for all students and staff")
        );

        final Marker lodgeMarker = googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_coffee)).position(lodge)
                .flat(true).title("UC Cooper Lodge").snippet("The best coffee on campus, " +
                        "underneath Cooper Lodge.")
        );

        final Marker studentCentreMarker = googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_std_centre))
                .position(resource).flat(true).title("UC Student Centre")
                .snippet("Your gateway to access support and advice.")
        );

        final Marker hubMarker = googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_thehub)).position(hub)
                .flat(true).title("The Hub").snippet("Below Concourse level between Building 1 " +
                        "and Building 8.")
        );

        final Marker gymMarker = googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_gym)).position(gym)
                .flat(true).title("UC Gym").snippet("Open to students, staff, and the general " +
                        "public.")
        );

        final Marker streetTopMarker = googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_street_view))
                .position(streetTop).flat(true).title("Ginninderra Drive Entry")
        );

        final Marker streetRightMarker = googleMap.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_street_view_person))
                .position(streetRight).flat(true).title("University Drive Entry")
        );

        CameraPosition cameraPosition = CameraPosition.builder().target(UC).zoom(15).bearing(90)
                .build();

        LatLng p1 = new LatLng(-35.231031, 149.080491);
        LatLng p2 = new LatLng(-35.231809, 149.083662);
        LatLng p3 = new LatLng(-35.232422, 149.085175);
        LatLng p4 = new LatLng(-35.233548, 149.086859);
        LatLng p5 = new LatLng(-35.234350, 149.089529);
        LatLng p6 = new LatLng(-35.234817, 149.091831);
        LatLng p7 = new LatLng(-35.238299, 149.090672);
        LatLng p8 = new LatLng(-35.239797, 149.090136);
        LatLng p9 = new LatLng(-35.240892, 149.090158);
        LatLng p10 = new LatLng(-35.242049, 149.090233);
        LatLng p11 = new LatLng(-35.242325, 149.088719);
        LatLng p12 = new LatLng(-35.242421, 149.086659);
        LatLng p13 = new LatLng(-35.242412, 149.083086);
        LatLng p14 = new LatLng(-35.242429, 149.077850);
        LatLng p15 = new LatLng(-35.240799, 149.074985);
        LatLng p16 = new LatLng(-35.238337, 149.076777);
        LatLng p17 = new LatLng(-35.234271, 149.078376);
        LatLng p18 = new LatLng(-35.232413, 149.079771);
        LatLng p19 = new LatLng(-35.231031, 149.080491);

        Polygon ucOutline = googleMap.addPolygon(new PolygonOptions().geodesic(true)
                .fillColor(Color.argb(50,100,205,255))
                .strokeColor(Color.argb(80, 100, 205, 255)).add(p1).add(p2)
                .add(p3).add(p4).add(p5).add(p6).add(p7).add(p8).add(p9).add(p10).add(p11).add(p12)
                .add(p13).add(p14).add(p15).add(p16).add(p17).add(p18).add(p19)
        );

        ucOutline.setClickable(true);

        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(p1).include(p2).include(p3).include(p4).include(p5).include(p7).include(p8)
                .include(p9).include(p10).include(p11).include(p12).include(p13).include(p14)
                .include(p15).include(p16).include(p17).include(p18).include(p19);
        final LatLngBounds bounds = builder.build();

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int)(width * 0.08);

        googleMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener(){
            @Override
            public void onPolygonClick(Polygon polygon) {
                polygon.setStrokeColor(R.color.colorPrimaryDark);
                Toast.makeText(getApplicationContext(), "University of Canberra",
                        Toast.LENGTH_LONG).show();
            }
        });

        googleMap.addPolyline(new PolylineOptions()
                .geodesic(true).color(Color.argb(100, 250, 0, 0))
                .add(new LatLng(-35.238885, 149.084595))
                .add(new LatLng(-35.238760, 149.085330))
                .add(new LatLng(-35.238320, 149.085330))
                .add(new LatLng(-35.238320, 149.088285))
        );

        googleMap.addPolyline(new PolylineOptions().geodesic(true)
                .color(Color.argb(100, 250, 0, 0))
                .add(new LatLng(-35.238885, 149.084595))
                .add(new LatLng(-35.238850, 149.084300))
                .add(new LatLng(-35.238810, 149.084270))
                .add(new LatLng(-35.238810, 149.084190))
                .add(new LatLng(-35.238780, 149.083970))
                .add(new LatLng(-35.238800, 149.083710))
                .add(new LatLng(-35.238650, 149.083700))
                .add(new LatLng(-35.238640, 149.082990))
                .add(new LatLng(-35.238610, 149.082390))
                .add(new LatLng(-35.238890, 149.082390))
                .add(new LatLng(-35.238850, 149.082215))
        );

        googleMap.addPolyline(new PolylineOptions().geodesic(true)
                .color(Color.argb(100, 250, 0, 0))
                .add(new LatLng(-35.238810, 149.084190))
                .add(new LatLng(-35.238500, 149.084170))
                .add(new LatLng(-35.238440, 149.084520))
        );

        googleMap.addPolyline(new PolylineOptions().geodesic(true)
                .color(Color.argb(100, 250, 0, 0))
                .add(new LatLng(-35.238810, 149.084190))
                .add(new LatLng(-35.238290, 149.084150))
                .add(new LatLng(-35.238220, 149.083970))
                .add(new LatLng(-35.238030, 149.083405))
        );

        googleMap.addPolyline(new PolylineOptions().geodesic(true)
                .color(Color.argb(100, 250, 0, 0))
                .add(new LatLng(-35.238290, 149.084150))
                .add(new LatLng(-35.236770, 149.084150))
                .add(new LatLng(-35.236690, 149.084300))
                .add(new LatLng(-35.236490, 149.084370))
                .add(new LatLng(-35.236430, 149.084290))
        );

        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height,
                padding), 2000, null);

        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View infoWindow = getLayoutInflater().inflate(R.layout.infowindow_with_image,
                        null);
                TextView title = infoWindow.findViewById(R.id.textViewTitle);
                TextView snippet = infoWindow.findViewById(R.id.textViewSnippet);
                ImageView image = infoWindow.findViewById(R.id.imageView);

                if (marker.getId().equals(libraryMarker.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_library, getTheme()));
                }

                if (marker.getId().equals(lodgeMarker.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_coffee, getTheme()));
                }

                if (marker.getId().equals(studentCentreMarker.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_std_centre, getTheme()));
                }

                if (marker.getId().equals(hubMarker.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources()
                            .getDrawable(R.mipmap.ic_thehub, getTheme()));
                }

                if (marker.getId().equals(gymMarker.getId())) {
                    title.setText(marker.getTitle());
                    snippet.setText(marker.getSnippet());
                    image.setImageDrawable(getResources().getDrawable(R.mipmap.ic_gym, getTheme()));
                }

                return infoWindow;
            }
        });

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker) {
                double x;
                double y;
                String title;
                if (marker.getId().equals(streetTopMarker.getId())) {
                    Intent intent = new Intent(getApplicationContext(), StreetViewActivity.class);
                    title = marker.getTitle();
                    x = -35.233653;
                    y = 149.087192;
                    intent.putExtra("title", title);
                    intent.putExtra("x", x);
                    intent.putExtra("y", y);
                    startActivity(intent);
                    return true;
                } else if (marker.getId().equals(streetRightMarker.getId())) {
                    Intent intent = new Intent(getApplicationContext(), StreetViewActivity.class);
                    title = marker.getTitle();
                    x = -35.238516;
                    y = 149.089560;
                    intent.putExtra("title", title);
                    intent.putExtra("x", x);
                    intent.putExtra("y", y);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            }
        });

        googleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener(){
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                String url = "";
                String title;

                if (marker.getId().equals(studentCentreMarker.getId())) {
                    url = "http://www.canberra.edu.au/current-students/canberra-students/" +
                            "student-centre";
                }

                if (marker.getId().equals(lodgeMarker.getId())) {
                    url = "https://www.unilodge.com.au/unilodge-uc-lodge-cooper-lodge";
                }

                if (marker.getId().equals(libraryMarker.getId())) {
                    url = "http://www.canberra.edu.au/library";
                }

                if (marker.getId().equals(hubMarker.getId())) {
                    url = "http://www.canberra.edu.au/maps/buildings-directory/the-hub";
                }

                if (marker.getId().equals(gymMarker.getId())) {
                    url = "http://www.ucunion.com.au/gym";
                }

                title = marker.getTitle();

                intent.putExtra("url", url);
                intent.putExtra("title", title);
                startActivity(intent);
            }
        });
    }
}