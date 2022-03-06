package study.heejin.chapter4.item17;

public class Address {
    private String jibun;
    private String road;
    private String post;

    public Address(String jibun, String road, String post) {
        this.jibun = jibun;
        this.road = road;
        this.post = post;
    }

    public void setJibun(String jibun) {this.jibun = jibun;}

    public void setRoad(String road) {this.road = road;}

    public void setPost(String post) {this.post = post;}

    public String getJibun() {return jibun;}

    public String getRoad() {return road;}

    public String getPost() {return post;}
}
