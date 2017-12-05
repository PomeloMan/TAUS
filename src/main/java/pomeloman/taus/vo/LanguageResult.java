package pomeloman.taus.vo;

import java.util.List;

public class LanguageResult {

	private String status;
	private String reason;
	private List<ResultMap> lang;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<ResultMap> getLang() {
		return lang;
	}

	public void setLang(List<ResultMap> lang) {
		this.lang = lang;
	}

}
