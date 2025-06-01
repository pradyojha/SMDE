package hub.experiment.strategy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MarketStreamDataCSVReader {

    public List<MarketStreamDataEntity> csvToMarketStreamData(String fileName)
        throws IOException {

          String[] HEADERS = { "ticker", "date","time","open","high","low","close","volume"};
          //BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
          Reader fileReader = new FileReader(fileName);

          CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
              .setHeader(HEADERS)
              .setSkipHeaderRecord(true)
              .build();
          CSVParser csvParser = new CSVParser(fileReader, csvFormat);

        List<MarketStreamDataEntity> marketStreamDataEntities = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();

        for (CSVRecord csvRecord : csvRecords) {
          MarketStreamDataEntity marketStreamDataEntity = new MarketStreamDataEntity(
              csvRecord.get("ticker"),
              csvRecord.get("date"),
              csvRecord.get("time"),
              Double.parseDouble(csvRecord.get("open")),
              Double.parseDouble(csvRecord.get("high")),
              Double.parseDouble(csvRecord.get("low")),
              Double.parseDouble(csvRecord.get("close")),
              Integer.parseInt(csvRecord.get("volume"))
          );
          marketStreamDataEntities.add(marketStreamDataEntity);
        }
        return marketStreamDataEntities;

    }
  }
