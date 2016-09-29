import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

import java.util.Set;
import java.util.regex.Pattern;

public class MyCrawler extends WebCrawler
{
    private final static Pattern FILTERS = Pattern
            .compile(".*(\\.(css|js|mp3|zip|gz))$");

    /**
     * This method receives two parameters. The first parameter is the page
     * in which we have discovered this new url and the second parameter is
     * the new url. You should implement this function to specify whether
     * the given url should be crawled or not (based on your crawling logic).
     * In this example, we are instructing the crawler to ignore urls that
     * have css, js, git, ... extensions and to only accept urls that start
     * with "http://www.ics.uci.edu/". In this case, we didn't need the
     * referringPage parameter to make the decision.
     */
    @Override
    public boolean shouldVisit(Page referringPage, WebURL url)
    {
        String href = url.getURL().toLowerCase();
        return !FILTERS.matcher(href).matches() && href
                .startsWith(Controller.site);
    }

    /**
     * This function is called when a page is fetched and ready
     * to be processed by your program.
     */
    @Override
    public void visit(Page page)
    {
        // fetch output file
        String url = page.getWebURL().getURL();
        Integer statusCode = page.getStatusCode();
        Integer outGoingLinks = 0;
        Integer size = page.getContentData().length;

        String contentType = page.getContentType();

        if (page.getParseData() instanceof HtmlParseData)
        {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            Set<WebURL> links = htmlParseData.getOutgoingUrls();

            for(WebURL x : links)
            {
                if(x.getURL().startsWith(Controller.site))
                {
                    Log.urls(x.getURL(), IsDomainLink.OK);
                }
                else
                {
                    Log.urls(x.getURL(), IsDomainLink.N_OK);
                }
            }
            outGoingLinks = links.size();
        }

        // Writing to files
        Log.fetch(url, statusCode);
        Log.visit(url, size, outGoingLinks, contentType);

        // debugging
        System.out.println("URL: " + url);

    }
}
