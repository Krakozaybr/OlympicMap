package com.krak.olympicmap.activity_main.fragments.map.clusters;

import android.app.Activity;

import com.krak.olympicmap.R;
import com.krak.olympicmap.entities.Country;
import com.krak.olympicmap.utils.PointsStorage;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.ClusterizedPlacemarkCollection;
import com.yandex.mapkit.map.IconStyle;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

import java.util.ArrayList;
import java.util.List;

public class ClustersManager {

    private final ClustersListener clustersListener;
    private final Activity activity;
    private final MapView mapView;
    private List<Point> cached = null;
    private ClusterizedPlacemarkCollection collection;
    private List<PlacemarkMapObject> placemarks;

    public ClustersManager(Activity activity, MapView mapView) {
        this.activity = activity;
        this.mapView = mapView;
        this.clustersListener = new ClustersListener(activity);
        this.collection = mapView.getMap().getMapObjects().addClusterizedPlacemarkCollection(clustersListener);
        this.placemarks = new ArrayList<>();
    }

    public void show(){
        ImageProvider imageProvider = ImageProvider.fromResource(activity, R.drawable.cluster_icon);
        // Сохраняем placemarks, чтобы мы могли их удалить при скрытии кластеризации
        placemarks = collection.addPlacemarks(getPoints(), imageProvider, new IconStyle());
        collection.clusterPlacemarks(60, 15);
    }

    public void hide(){
        for (PlacemarkMapObject placemark : placemarks){
            collection.remove(placemark);
        }
        collection.clusterPlacemarks(60, 15);
    }

    private List<Point> getPoints(){
        // Кеш нужен для того, чтобы каждый раз при включении кластеризации
        // не читать информацию из файла
        if (cached == null){
            cached = createPoints();
        }
        return cached;
    }

    private List<Point> createPoints() {
        ArrayList<Point> points = new ArrayList<Point>();
        for (Country country : PointsStorage.getCountries()){
            points.add(new Point(country.getLatitude(), country.getLongitude()));
        }
        return points;
    }
}
