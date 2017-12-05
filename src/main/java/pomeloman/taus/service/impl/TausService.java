package pomeloman.taus.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.Asserts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import pomeloman.taus.service.ITausService;
import pomeloman.taus.vo.LanguageResult;
import pomeloman.taus.vo.ResultMap;
import pomeloman.taus.vo.Segment;
import pomeloman.taus.vo.SegmentResult;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class TausService implements ITausService {

	private final static Logger logger = Logger.getLogger(TausService.class);

	private Gson gson = new GsonBuilder().create();
	private final String apiUrl = "https://data-app.taus.net/api/";
	private final String username = "chao.feng5@pactera.com";
	private final String password = "password";

	private final boolean default_highlight = false;
	private final int default_limit = 20;

	private enum RequestType {
		Get, Post
	}

	private String execRequest(String uri, RequestType type,
			Map<String, Object> input) throws ClientProtocolException,
			IOException {
		Asserts.notEmpty(uri, "URI");
		String content = null;
		CloseableHttpClient client = getBasicHttpClient(username, password);
		try {
			HttpResponse httpResponse = null;
			uri = apiUrl + uri;
			if (type == RequestType.Get) {
				if (input != null && input.size() != 0) {
					List<NameValuePair> params = new ArrayList<NameValuePair>();
					input.keySet().stream().forEach(k -> params.add(new BasicNameValuePair(k, input.get(k).toString())));
					uri += "?" + EntityUtils.toString(new UrlEncodedFormEntity(params, Consts.UTF_8));
				}
				HttpGet httpRequest = new HttpGet(uri);
				httpResponse = client.execute(httpRequest);
			} else if (type == RequestType.Post) {
				HttpPost httpRequest = new HttpPost(uri);
				if (input != null && input.size() != 0) {
					httpRequest.setEntity(new StringEntity(gson.toJson(input), ContentType.APPLICATION_JSON));
				}
				httpResponse = client.execute(httpRequest);
			} else {
				return null;
			}
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity);
			}
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				Map<String, Object> result = gson.fromJson(content,
						new TypeToken<Map<String, Object>>() {
						}.getType());
				throw new RuntimeException(result.get("message").toString());
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Request uri " + uri);
				logger.debug("Response status '" + httpResponse.getStatusLine().getStatusCode() + "'");
				logger.debug(content);
			}
		} finally {
			IOUtils.closeQuietly(client);
		}
		return content;
	}

	private CloseableHttpClient getBasicHttpClient(String username,
			String password) {
		CredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
		return HttpClients.custom().setDefaultCredentialsProvider(provider).build();
	}

	@Override
	public List<ResultMap> getLanguage(String id) throws JsonSyntaxException,
			ClientProtocolException, IOException {
		String uri = "lang";
		if (StringUtils.isNotEmpty(id)) {
			uri += "/" + id;
		}
		uri += ".json";
		LanguageResult result = gson.fromJson(
				execRequest(uri, RequestType.Get, null),
				new TypeToken<LanguageResult>() {
				}.getType());
		return result.getLang();
	}

	@Override
	public List<Segment> search(String query, String source_lang,
			String target_lang) throws JsonSyntaxException,
			ClientProtocolException, IOException {
		return search(query, source_lang, target_lang, null, null, null, default_highlight, default_limit);
	}

	@Override
	public List<Segment> search(String query, String source_lang,
			String target_lang, boolean highlight, int limit) throws JsonSyntaxException,
			ClientProtocolException, IOException {
		return search(query, source_lang, target_lang, null, null, null, highlight, limit);
	}

	@Override
	public List<Segment> search(String query, String source_lang,
			String target_lang, String industry, String content_type,
			String owner, boolean highlight, int limit) throws JsonSyntaxException, ClientProtocolException,
			IOException {
		Asserts.notEmpty(query, "Query text");
		Asserts.notEmpty(source_lang, "Source language");
		Asserts.notEmpty(target_lang, "Target language");
		Map<String, Object> input = new HashMap<String, Object>();
		input.put("q", query);
		input.put("source_lang", source_lang);
		input.put("target_lang", target_lang);
		if (StringUtils.isNotEmpty(industry))
			input.put("industry", industry);
		if (StringUtils.isNotEmpty(content_type))
			input.put("content_type", content_type);
		if (StringUtils.isNotEmpty(owner))
			input.put("owner", owner);
		input.put("highlight", highlight);
		input.put("limit", limit);
		SegmentResult result = gson.fromJson(
				execRequest("segment.json", RequestType.Get, input),
				new TypeToken<SegmentResult>() {
				}.getType());
		return result.getSegment();
	}

}
