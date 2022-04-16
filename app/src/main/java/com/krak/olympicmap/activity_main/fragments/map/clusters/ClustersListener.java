package com.krak.olympicmap.activity_main.fragments.map.clusters;

import android.app.Activity;
import android.widget.Toast;

import com.krak.olympicmap.activity_main.fragments.map.MapFragment;
import com.yandex.mapkit.map.Cluster;
import com.yandex.mapkit.map.ClusterListener;
import com.yandex.mapkit.map.ClusterTapListener;

public class ClustersListener implements ClusterListener, ClusterTapListener {

    private final Activity activity;

    public ClustersListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClusterAdded(Cluster cluster) {
        cluster.getAppearance().setIcon(
                new TextImageProvider(Integer.toString(cluster.getSize()), activity));
        cluster.addClusterTapListener(this);
    }

    @Override
    public boolean onClusterTap(Cluster cluster) {
        Toast.makeText(activity,
                "Вы нажали на кластер с " + cluster.getSize() + " элементами",
                Toast.LENGTH_SHORT);
        return true;
    }
}
