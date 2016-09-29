import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller
{
    public static String site = "http://www.huffingtonpost.com/";
    public static Integer maxPages = 1000;
    public static Integer depth = 16;
    public static Integer numberOfCrawlers = 7;
    public static Integer politness = 500;

    public static void main(String[] args) throws Exception
    {
        String crawlStorageFolder = "/data/crawl/root";

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxPagesToFetch(Controller.maxPages);
        config.setMaxDepthOfCrawling(Controller.depth);
        config.setPolitenessDelay(Controller.politness);
        /*
         * Instantiate the controller for this crawl.
         */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig,
                pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher,
                robotstxtServer);

        /*
         * For each crawl, you need to add some seed urls. These are the first
         * URLs that are fetched and then the crawler starts following links
         * which are found in these pages
         */
        controller.addSeed(Controller.site);

        /*
         * Start the crawl. This is a blocking operation, meaning that your code
         * will reach the line after this only when crawling is finished.
         */
        controller.start(MyCrawler.class, Controller.numberOfCrawlers);
        Log.cleanup();
        // Log.flushAll();
    }
}