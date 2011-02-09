package com.unknown.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collection;
import java.util.List;
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

public class JsonHandler {
	private static String TAG = JsonHandler.class.getSimpleName();
	private static final int CONNECTION_TIMEOUT = 120000;
	private static final int SOCKET_TIMEOUT = 120000;

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
			throw new IOException("Failed to get stream for: " + url);
		}
		return response.getEntity().getContent();
	}

	public Collection<User> parseJSONUsers() throws ClientProtocolException, IOException {
		Gson gson = new Gson();
		String url = "http://unknown-entity.com:4848/GetUsersJSON";
		Reader r = new InputStreamReader(getInputStreamFromUrl(url));
		Type collectionType = new TypeToken<Collection<User>>() {}.getType();
		Collection<User> target = gson.fromJson(r, collectionType);
		Log.e(TAG, " :: " + target);
		for (User u : target) {
			System.out.println(u.getUsername());
			Log.e(TAG, " :: " + u.getUsername());
		}
		return target;
	}
}
