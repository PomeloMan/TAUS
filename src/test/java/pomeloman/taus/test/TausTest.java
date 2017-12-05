package pomeloman.taus.test;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Test;

import pomeloman.taus.service.ITausService;
import pomeloman.taus.service.impl.TausService;
import pomeloman.taus.vo.ResultMap;
import pomeloman.taus.vo.Segment;

import com.google.gson.JsonSyntaxException;

public class TausTest {

	private ITausService service = new TausService();

	@Test
	public void search() throws JsonSyntaxException, ClientProtocolException, IOException {
		List<Segment> list = service.search("你好", "zh-CN", "en-us", true, 100);
		System.out.println(list);
	}

	@Test
	public void getLang() throws JsonSyntaxException, ClientProtocolException, IOException {
		List<ResultMap> list = service.getLanguage(null);
		System.out.println(list.size());
	}
}
