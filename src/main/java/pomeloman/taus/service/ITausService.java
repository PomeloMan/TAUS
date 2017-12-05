package pomeloman.taus.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;

import pomeloman.taus.vo.ResultMap;
import pomeloman.taus.vo.Segment;

import com.google.gson.JsonSyntaxException;

/**
 * @ClassName ITausService.java
 * @Description TAUS API 服务接口
 * @author Feng Chao
 */
public interface ITausService {

	/**
	 * @Description 获取语言对信息
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws JsonSyntaxException 
	 */
	public List<ResultMap> getLanguage(String id) throws JsonSyntaxException, ClientProtocolException, IOException;

	/**
	 * @Description TODO
	 * @param query 查询字串
	 * @param source_lang 源语言
	 * @param target_lang 目标语言
	 * @return
	 * @throws JsonSyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public List<Segment> search(String query, String source_lang,
			String target_lang) throws JsonSyntaxException,
			ClientProtocolException, IOException;

	/**
	 * @Description TODO
	 * @param query 查询字串
	 * @param source_lang 源语言
	 * @param target_lang 目标语言
	 * @param highlight 是否高亮，true 会在source中加<em>标签来高亮查询字段
	 * @param limit 查询结果大小，默认返回20个结果
	 * @return
	 * @throws JsonSyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public List<Segment> search(String query, String source_lang,
			String target_lang, boolean highlight, int limit)
			throws JsonSyntaxException, ClientProtocolException, IOException;

	/**
	 * @Description TODO
	 * @param query 查询字串
	 * @param source_lang 源语言
	 * @param target_lang 目标语言
	 * @param industry 行业（可选， 例：Automotive Manufacturing， Computer Hardware， Computer Software， Financials...）
	 * @param content_type 类别（可选， 例：Financial Documentation， Software Strings and Documentation， Undefined Content Type）
	 * @param owner 所有者（可选）
	 * @param highlight 是否高亮，true 会在source中加<em>标签来高亮查询字段
	 * @param limit 查询结果大小，默认返回20个结果
	 * @return
	 * @throws JsonSyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public List<Segment> search(String query, String source_lang,
			String target_lang, String industry, String content_type,
			String owner, boolean highlight, int limit)
			throws JsonSyntaxException, ClientProtocolException, IOException;
}
