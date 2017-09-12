package bean;

public class Take {

    private String t_code;

    private String t_mid;

    private String t_kind;

    private String t_title;

    private String t_content;

    private String t_request_period_s;
    
    private String t_request_period_e;

    private String t_service_period;

    private String t_gender;

    private String t_age;

    private String t_personnel;

    private String t_etc;

    private String t_filename;

    private String t_date;
    
    private int t_hits;
    
    private String tm_name;
    
    private String tm_phone;
    
    private String tm_email;
    
    private String tm_birth;
    
    private String tm_gender;
    public Take(String id, String kind) {
    	t_mid=id;
    	t_kind=kind;
    }

	public Take() {
		// TODO Auto-generated constructor stub
	}

	public Take(String id, String kind, String title, String content, String t_request_period_s, String t_request_period_e, String s_period, String gender, String age, String personnel,
	         String etc, String t_fileName) {
	      this.t_mid = id;
	      this.t_kind = kind;
	      this.t_title = title;
	      this.t_content = content;
	      this.t_request_period_s = t_request_period_s;
	      this.t_request_period_e = t_request_period_e;
	      this.t_service_period = s_period;
	      this.t_gender = gender;
	      this.t_age = age;
	      this.t_personnel = personnel;
	      this.t_etc = etc;
	      this.t_filename = t_fileName;
	   }

	public String getTm_birth() {
		return tm_birth;
	}

	public void setTm_birth(String tm_birth) {
		this.tm_birth = tm_birth;
	}

	public String getTm_name() {
		return tm_name;
	}

	public void setTm_name(String tm_name) {
		this.tm_name = tm_name;
	}

	public String getTm_phone() {
		return tm_phone;
	}

	public void setTm_phone(String tm_phone) {
		this.tm_phone = tm_phone;
	}

	public String getTm_email() {
		return tm_email;
	}

	public void setTm_email(String tm_email) {
		this.tm_email = tm_email;
	}

	public String getTm_gender() {
		return tm_gender;
	}

	public void setTm_gender(String tm_gender) {
		this.tm_gender = tm_gender;
	}
    
    public int getT_hits() {
		return t_hits;
	}

	public void setT_hits(int t_hits) {
		this.t_hits = t_hits;
	}

	public String getT_code() {
        return t_code;
    }

    public String getT_request_period_s() {
		return t_request_period_s;
	}

	public void setT_request_period_s(String t_request_period_s) {
		this.t_request_period_s = t_request_period_s;
	}

	public String getT_request_period_e() {
		return t_request_period_e;
	}

	public void setT_request_period_e(String t_request_period_e) {
		this.t_request_period_e = t_request_period_e;
	}


	public void setT_code(String t_code) {
        this.t_code = t_code;
    }

    public String getT_mid() {
        return t_mid;
    }

    public void setT_mid(String t_mid) {
        this.t_mid = t_mid;
    }

    public String getT_kind() {
        return t_kind;
    }

    public void setT_kind(String t_kind) {
        this.t_kind = t_kind;
    }

    public String getT_title() {
        return t_title;
    }

    public void setT_title(String t_title) {
        this.t_title = t_title;
    }

    public String getT_content() {
        return t_content;
    }

    public void setT_content(String t_content) {
        this.t_content = t_content;
    }


    public String getT_service_period() {
        return t_service_period;
    }

    public void setT_service_period(String t_service_period) {
        this.t_service_period = t_service_period;
    }

    public String getT_gender() {
        return t_gender;
    }

    public void setT_gender(String t_gender) {
        this.t_gender = t_gender;
    }

    public String getT_age() {
        return t_age;
    }

    public void setT_age(String t_age) {
        this.t_age = t_age;
    }

    public String getT_personnel() {
        return t_personnel;
    }

    public void setT_personnel(String t_personnel) {
        this.t_personnel = t_personnel;
    }

    public String getT_etc() {
        return t_etc;
    }

    public void setT_etc(String t_etc) {
        this.t_etc = t_etc;
    }

    public String getT_filename() {
        return t_filename;
    }

    public void setT_filename(String t_filename) {
        this.t_filename = t_filename;
    }

    public String getT_date() {
        return t_date;
    }

    public void setT_date(String t_date) {
        this.t_date = t_date;
    }

}
