import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMLParserTest {
    final String PATH = "src/main/resources/eurofxref-daily.xml";

    @Test
    void returnedMapShouldContains33Pairs() throws IOException {
        int size = XMLParser.getCurrencyRates(PATH).size();
        assertEquals(33, size);
    }

    @Test
    void wrongPathShouldThrowAFileNotFoundException() throws IOException {

        assertThrows(FileNotFoundException.class, () -> { XMLParser.getCurrencyRates("src/wrong/path.xml"); });
    }
}