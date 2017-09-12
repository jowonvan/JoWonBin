package bean;

public class Review {

    private String r_code;

    private String r_mid;

    private String r_title;

    private String r_name;

    private String r_when;

    private String r_content;

    private String r_place;

    private String r_feeling;

    private String r_filename1;
    
    private String r_filename2;
    
    private String r_filename3;
    
    private String r_date;
    
    private int r_hits;

    public Review(String mid, String title, String name, String when, String content, String place, String feeling,
			String filename1, String filename2, String filename3) {
    	this.r_mid = mid;
		this.r_title=title;
		this.r_name =name;
		this.r_when=when;
		this.r_content = content;
		this.r_place=place;
		this.r_feeling = feeling;
		this.r_filename1=filename1;
		this.r_filename2=filename2;
		this.r_filename3=filename3;
    }

	public Review() {

	}

	public int getR_hits() {
		return r_hits;
	}

	public void setR_hits(int r_hits) {
		this.r_hits = r_hits;
	}

	public String getR_code() {
        return r_code;
    }

    public void setR_code(String r_code) {
        this.r_code = r_code;
    }

    public String getR_mid() {
        return r_mid;
    }

    public void setR_mid(String r_mid) {
        this.r_mid = r_mid;
    }

    public String getR_title() {
        return r_title;
    }

    public void setR_title(String r_title) {
        this.r_title = r_title;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_when() {
        return r_when;
    }

    public void setR_when(String r_when) {
        this.r_when = r_when;
    }

    public String getR_content() {
        return r_content;
    }

    public void setR_content(String r_content) {
        this.r_content = r_content;
    }

    public String getR_place() {
        return r_place;
    }

    public void setR_place(String r_place) {
        this.r_place = r_place;
    }

    public String getR_feeling() {
        return r_feeling;
    }

    public void setR_feeling(String r_feeling) {
        this.r_feeling = r_feeling;
    }

	public String getR_filename1() {
		return r_filename1;
	}

	public void setR_filename1(String r_filename1) {
		this.r_filename1 = r_filename1;
	}

	public String getR_filename2() {
		return r_filename2;
	}

	public void setR_filename2(String r_filename2) {
		this.r_filename2 = r_filename2;
	}

	public String getR_filename3() {
		return r_filename3;
	}

	public void setR_filename3(String r_filename3) {
		this.r_filename3 = r_filename3;
	}

	public String getR_date() {
		return r_date;
	}

	public void setR_date(String r_date) {
		this.r_date = r_date;
	}

  

}
