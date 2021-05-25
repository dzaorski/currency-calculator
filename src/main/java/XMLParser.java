import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Get currency rates from xml file.
 * @author Dawid Zaorski
 */
class XMLParser {
    static final String PATH = "src/main/resources/eurofxref-daily.xml";
    static HashMap<String, Double> currencyRates = new HashMap<String, Double>();

    static HashMap<String, Double> getCurrencyRates(String[] args) throws IOException {
        currencyRates.put("EUR", 1.0);

        File file = new File(PATH);
        FileInputStream fis = new FileInputStream(file);
        Document doc = Jsoup.parse(fis, "UTF-8", "", Parser.xmlParser());

        for(Element e: doc.getElementsByAttribute("currency")){
            currencyRates.put(e.attr("currency"), Double.valueOf(e.attr("rate")));
        }
        return currencyRates;
    }
}
