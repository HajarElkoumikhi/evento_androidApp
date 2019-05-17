package com.example.evento.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

		import com.android.volley.Request;
		import com.android.volley.Response;
		import com.android.volley.VolleyError;
		import com.android.volley.toolbox.JsonArrayRequest;
		import com.android.volley.toolbox.Volley;
		import com.example.evento.R;
import com.example.evento.adapter.EventAdapter;
import com.example.evento.models.Event;

		import org.json.JSONArray;
		import org.json.JSONException;
		import org.json.JSONObject;

		import java.util.ArrayList;

public class EventsListActivity extends AppCompatActivity {

	private EventAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events_list);

		String url = "http://10.0.2.2:8080/allEvents";
		final ArrayList<Event> eventsList = new ArrayList<>();

		final JsonArrayRequest getEvents = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>(){
			@Override
			public void onResponse(JSONArray response){
				System.out.println(response.toString());

				try {
					JSONArray events = response;
					for (int x=0;x<events.length(); x++){
						JSONObject event = events.getJSONObject(x);

						String title = event.getString("title");
						String description = event.getString("description");
						String city = event.getString("city");
						String country = event.getString("country");

						Event newEvent = new Event(title,description,city,country);
						eventsList.add(newEvent);

					}
				}catch (JSONException e){
					Log.v("JSON", "EXC"+e.getLocalizedMessage());

				}

				System.out.println("First event name : "+eventsList.get(0).getTitle());

				RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_event);
				recyclerView.setHasFixedSize(true);
				adapter = new EventAdapter(eventsList);
				recyclerView.setAdapter(adapter);

				LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
				layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
				recyclerView.setLayoutManager(layoutManager);

			}
		}, new Response.ErrorListener(){
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.v("API", "Err" + error.getLocalizedMessage());
			}
		});

		Volley.newRequestQueue(this).add(getEvents);
	}
}

