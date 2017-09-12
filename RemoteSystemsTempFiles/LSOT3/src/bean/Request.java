package bean;

public class Request {

    private String q_code;

    private String q_mid;

    private String q_tcode;
    
    private int q_progress;
    
    private String q_ttitle;
    
    private String q_tdate;
    
    private String q_tkind;
    

	public Request(String id, String t_code) {
		q_mid=id;
		q_tcode=t_code;
		
	}

	public Request() {
		// TODO Auto-generated constructor stub
	}

	public String getQ_tkind() {
		return q_tkind;
	}

	public void setQ_tkind(String q_tkind) {
		this.q_tkind = q_tkind;
	}

	public String getQ_ttitle() {
		return q_ttitle;
	}

	public void setQ_ttitle(String q_ttitle) {
		this.q_ttitle = q_ttitle;
	}

	public String getQ_tdate() {
		return q_tdate;
	}

	public void setQ_tdate(String q_tdate) {
		this.q_tdate = q_tdate;
	}

	public int getQ_progress() {
		return q_progress;
	}

	public void setQ_progress(int q_progress) {
		this.q_progress = q_progress;
	}

	public String getQ_code() {
        return q_code;
    }

    public void setQ_code(String q_code) {
        this.q_code = q_code;
    }

    public String getQ_mid() {
        return q_mid;
    }

    public void setQ_mid(String q_mid) {
        this.q_mid = q_mid;
    }

    public String getQ_tcode() {
        return q_tcode;
    }

    public void setQ_tcode(String q_tcode) {
        this.q_tcode = q_tcode;
    }

}
