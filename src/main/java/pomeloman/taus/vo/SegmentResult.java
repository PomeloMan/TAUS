package pomeloman.taus.vo;

import java.util.List;

public class SegmentResult {

	private String status;
	private String reason;
	private List<Segment> segment;

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

	public List<Segment> getSegment() {
		return segment;
	}

	public void setSegment(List<Segment> segment) {
		this.segment = segment;
	}

}
