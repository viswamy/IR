import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

class Fetch
{
    String url;
    HttpStatus status;

    public Fetch(String url, HttpStatus status)
    {
        this.url = url;
        this.status = status;
    }
}

class Visit
{
    String url;
    String contentType;
    Integer size;
    Integer outLinks;

    public Visit(String url, String contentType, Integer size, Integer outLinks)
    {
        this.url = url;
        this.contentType = contentType;
        this.size = size;
        this.outLinks = outLinks;
    }
}

public class Stats
{
    public static ConcurrentHashMap<String, Fetch> fetch = new ConcurrentHashMap<String, Fetch>();
    public static ConcurrentHashMap<String, Visit> visit = new ConcurrentHashMap<String, Visit>();

}
