package bean;

public class Notice {

    private String n_code;

    private String n_title;

    private String n_content;
    
    private String n_date;
    
    private int n_hits;

    public int getN_hits() {
		return n_hits;
	}

	public void setN_hits(int n_hits) {
		this.n_hits = n_hits;
	}

	public String getN_date() {
		return n_date;
	}

	public void setN_date(String n_date) {
		this.n_date = n_date;
	}

	public String getN_code() {
        return n_code;
    }

    public void setN_code(String n_code) {
        this.n_code = n_code;
    }

    public String getN_title() {
        return n_title;
    }

    public void setN_title(String n_title) {
        this.n_title = n_title;
    }

    public String getN_content() {
        return n_content;
    }

    public void setN_content(String n_content) {
        this.n_content = n_content;
    }

}
