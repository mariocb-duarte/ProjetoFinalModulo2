import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GitHub {

        private String url;
        private String slugProfile;

        public GitHub(String url) {
            this.url = url;
            setSlugProfile(url);
        }

        public String getSlugProfile() {
            return slugProfile;
        }

        public void setSlugProfile(String url) {
            Pattern pattern = Pattern.compile("github\\.com/([a-zA-Z0-9-]+)/?$");
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


