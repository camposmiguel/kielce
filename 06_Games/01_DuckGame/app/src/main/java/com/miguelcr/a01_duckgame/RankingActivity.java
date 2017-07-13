package com.miguelcr.a01_duckgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.Sort;

public class RankingActivity extends AppCompatActivity {
    ListView lista;
    List<User> userList;
    RankingAdapter adapter;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        lista = (ListView) findViewById(R.id.listView);

        initRealm();
        realm = Realm.getDefaultInstance();

        userList = realm.where(User.class).findAllSorted("points", Sort.DESCENDING);

        adapter = new RankingAdapter(
                this,
                R.layout.list_rank_item,
                userList
        );

        lista.setAdapter(adapter);

    }

    private void initRealm() {
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
