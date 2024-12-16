package SPP.SPP_kurz;

import java.util.regex.Pattern;

public class LinkChecker
{
    private static final String YOUTUBE_PATTERN =
            "^(https?://)?(www\\.)?(youtube\\.com/watch\\?v=|youtu\\.be/)([\\w-]{11})([?|&][\\w=-]*)*$";
    private static final Pattern pattern = Pattern.compile(YOUTUBE_PATTERN);

    public static boolean isYouTubeVideoLink(String messageText)
    {
        return pattern.matcher(messageText).matches();
    }
}
