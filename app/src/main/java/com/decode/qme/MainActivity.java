package com.decode.qme;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BeaconConsumer {

    private static String TAG = "MyActivity";
    private BeaconManager mBeaconManager;
    public HashMap<String,Region> ssnRegionMap;
    private static final Identifier nameSpaceId = Identifier.parse("0x5dc33487f02e477d4058");
    public ArrayList<String> regionNames = new ArrayList<>();
    public ArrayList<String> regions= new ArrayList<>();

    @Override
    public void onResume() {
        super.onResume();

        mBeaconManager.bind(this);
    }

    public void onBeaconServiceConnect() {

        mBeaconManager.addMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {

            }

            @Override
            public void didExitRegion(Region region) {

            }

            @Override
            public void didDetermineStateForRegion(int i, Region region) {
                String regionName = region.getUniqueId();
                String beaconSSN = region.getId2().toHexString();
                Log.d(TAG, "didDetermineStateForRegion: "+i);
                switch (i){
                    case INSIDE:
                        Log.i("TAG","Enter " + regionName);
                        if(!regionNames.contains(regionName))
                        {
                            regionNames.add(regionName);
                            regions.add(beaconSSN);
                        }

                        break;
                    case OUTSIDE:
                        Log.i("TAG","Outside " + regionName);
                        if(regionNames.contains(regionName))
                        {
                            regionNames.remove(regionName);
                            regions.remove(beaconSSN);
                        }

                        break;
                }
            }

        });
        try {
            for(String key:ssnRegionMap.keySet()) {
                Region region = ssnRegionMap.get(key);
                mBeaconManager.startMonitoringBeaconsInRegion(region);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void onPause() {
        super.onPause();
        mBeaconManager.unbind(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ssnRegionMap = new HashMap<>();


        ssnRegionMap.put("0x0117c59825E9",new Region("Test Room",nameSpaceId, Identifier.parse("0x0117c59825E9"),null));
        ssnRegionMap.put("0x0117c55be3a8",new Region("Git Room",nameSpaceId,Identifier.parse("0x0117c55be3a8"),null));
        ssnRegionMap.put("0x0117c552c493",new Region("Android Room",nameSpaceId,Identifier.parse("0x0117c552c493"),null));
        ssnRegionMap.put("0x0117c55fc452",new Region("iOS Room",nameSpaceId,Identifier.parse("0x0117c55fc452"),null));
        ssnRegionMap.put("0x0117c555c65f",new Region("Withdraw counter",nameSpaceId,Identifier.parse("0x0117c555c65f"),null));
        ssnRegionMap.put("0x0117c55d6660",new Region("Deposit counter",nameSpaceId,Identifier.parse("0x0117c55d6660"),null));
        ssnRegionMap.put("0x0117c55ec086",new Region("Ruby Room",nameSpaceId,Identifier.parse("0x0117c55ec086"),null));

        mBeaconManager = BeaconManager.getInstanceForApplication(this.getApplicationContext());

        mBeaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));
        new BackgroundPowerSaver(this);

    }
}
