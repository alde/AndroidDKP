package com.unknown.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unknown.entity.json.Items;
import com.unknown.entity.json.Raid;
import com.unknown.entity.json.User;

public class JsonHandler {
	private String TAG = JsonHandler.class.getSimpleName();
	private final int CONNECTION_TIMEOUT = 120000;
	private final int SOCKET_TIMEOUT = 120000;

	public HttpParams getTimeoutHttpParams() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, CONNECTION_TIMEOUT);
		HttpConnectionParams.setSoTimeout(params, SOCKET_TIMEOUT);
		return params;
	}

	public InputStream getInputStreamFromUrl(String url) throws ClientProtocolException, IOException {
		HttpGet request = new HttpGet(url);
		DefaultHttpClient client = new DefaultHttpClient(getTimeoutHttpParams());
		HttpResponse response = client.execute(request);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			Log.e(TAG, "Failed to get stream for: " + url);
		}
		return response.getEntity().getContent();
	}

	public Collection<User> parseJSONUsers() throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		String url = "http://dkp.unknown-entity.com/GetUsersJSON";
		Reader r = new InputStreamReader(getInputStreamFromUrl(url));
		Type collectionType = new TypeToken<Collection<User>>() {}.getType();
		Collection<User> target = gson.fromJson(r, collectionType);
		return target;
	}
	
	public Collection<Items> parseJSONItems() throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		String url = "http://dkp.unknown-entity.com/GetItemsJSON";
		Reader r = new InputStreamReader(getInputStreamFromUrl(url));
		Type collectionType = new TypeToken<Collection<Items>>() {}.getType();
		Collection<Items> target = gson.fromJson(r, collectionType);
		return target;
	}
	
	public Collection<Raid> parseJSONRaids() throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		String url = "http://dkp.unknown-entity.com/GetRaidsJSON";
		Reader r = new InputStreamReader(getInputStreamFromUrl(url));
		Type collectionType = new TypeToken<Collection<Raid>>() {}.getType();
		Collection<Raid> target = gson.fromJson(r, collectionType);
		return target;
	}
}
