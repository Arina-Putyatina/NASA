import com.fasterxml.jackson.annotation.JsonProperty;

public class AstronomyPicture {

    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String media;
    private final String version;
    private final String title;
    private final String url;

    public AstronomyPicture(
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String media,
            @JsonProperty("service_version") String version,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url) {
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media = media;
        this.version = version;
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
