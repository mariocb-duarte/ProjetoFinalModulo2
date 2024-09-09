import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkedIn {
    private String url;
    private String slugProfile;

    public LinkedIn(String url) {
        this.url = url;
        setSlugProfile(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSlugProfile() {
        return slugProfile;
    }

    public void setSlugProfile(String url) {
        Pattern pattern = Pattern.compile("linkedin\\.com\\/in\\/([a-zA-Z0-9-]+)\\/?$");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            this.slugProfile = matcher.group(1);
        }
    }

    @Override
    public String toString() {
        return slugProfile;
    }
}
