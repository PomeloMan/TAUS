package pomeloman.taus.vo;

public class Segment {

	private String id;
	private ResultMap source_lang;
	private ResultMap target_lang;
	private String source;
	private String target;
	private ResultMap industry;
	private ResultMap content_type;
	private ResultMap owner;
	private ResultMap product;
	private ResultMap provider;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ResultMap getSource_lang() {
		return source_lang;
	}

	public void setSource_lang(ResultMap source_lang) {
		this.source_lang = source_lang;
	}

	public ResultMap getTarget_lang() {
		return target_lang;
	}

	public void setTarget_lang(ResultMap target_lang) {
		this.target_lang = target_lang;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public ResultMap getIndustry() {
		return industry;
	}

	public void setIndustry(ResultMap industry) {
		this.industry = industry;
	}

	public ResultMap getContent_type() {
		return content_type;
	}

	public void setContent_type(ResultMap content_type) {
		this.content_type = content_type;
	}

	public ResultMap getOwner() {
		return owner;
	}

	public void setOwner(ResultMap owner) {
		this.owner = owner;
	}

	public ResultMap getProduct() {
		return product;
	}

	public void setProduct(ResultMap product) {
		this.product = product;
	}

	public ResultMap getProvider() {
		return provider;
	}

	public void setProvider(ResultMap provider) {
		this.provider = provider;
	}

}
