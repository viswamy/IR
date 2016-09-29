
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ConcurrentLinkedQueue;

enum IsDomainLink
{
    OK,
    N_OK
}

public class Log
{

    private static ConcurrentLinkedQueue<String> listFetch;
    private static ConcurrentLinkedQueue<String> listVisit;
    private static ConcurrentLinkedQueue<String> listUrls;
    private static PrintWriter fetchWriter;
    private static PrintWriter visitWriter;
    private static PrintWriter urlsWriter;
    static
    {
            listFetch = new ConcurrentLinkedQueue<String>();
            listVisit = new ConcurrentLinkedQueue<String>();
            listUrls = new ConcurrentLinkedQueue<String>();

        try
        {
            fetchWriter = new PrintWriter("fetch_NewSite1.txt", "UTF-8");
            visitWriter = new PrintWriter("visit_NewSite1.txt", "UTF-8");
            urlsWriter = new PrintWriter("urls_NewSite1.txt", "UTF-8");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
    }

    public static void fetch(String url, Integer status)
    {
        //listFetch.add(url + "," + status);
        fetchWriter.println(url + "," + status);
    }

    public static void visit(String url, Integer size, Integer outLinks,
            String contentType)
    {
        //listVisit.add(url + "," + size + "," + outLinks + "," + contentType);
        visitWriter.println(url + "," + size + "," + outLinks + "," + contentType);
    }

    public static void urls(String url, IsDomainLink isDomainLink)
    {
        //listUrls.add(url + "," + isDomainLink);
        urlsWriter.println(url + "," + isDomainLink);
    }


    public static void flushAll()
            throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter fetchWriter = new PrintWriter("fetch_NewSite1.txt", "UTF-8");;
        PrintWriter visitWriter = new PrintWriter("visit_NewSite1.txt", "UTF-8");;
        PrintWriter urlsWriter = new PrintWriter("urls_NewSite1.txt", "UTF-8");;

        for(String item : listFetch)
            fetchWriter.println(item);

        for(String item : listVisit)
            visitWriter.println(item);

        for(String item : listUrls)
            urlsWriter.println(item);

        fetchWriter.flush();
        fetchWriter.close();

        visitWriter.flush();
        visitWriter.close();

        urlsWriter.flush();
        urlsWriter.close();
    }

    public static void cleanup()
    {
        fetchWriter.flush();
        fetchWriter.close();

        visitWriter.flush();
        visitWriter.close();

        urlsWriter.flush();
        urlsWriter.close();
    }

}
