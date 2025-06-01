package hub.experiment.strategy;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {

  public static void main(String[] args) throws IOException {
    /*
    Approach:
    - Read the market stream data for a stock, hold it in a collection[map: timeStamp and Other details Entity]
    - Build strategy rule engine dynamically [separate project for this] but it is static for now
    - Iterate over the collection of market stream data
    - Apply the strategy for the current Input market data , if pass then place order for BUY or SELL
    - Once the ORDER is placed, now re-iterate the collection[map] and execute the order based on the condition met now onwards
    */

    //Read the market stream CSV data
    String csvFileLocation = "/Users/pradyumnaojha/Downloads/SMDS/code/java/market-emulator/src/main/resources/stockName-date-interval.csv";
    MarketStreamDataCSVReader csvReader = new MarketStreamDataCSVReader();
    List<MarketStreamDataEntity> marketStreamDataEntityList = csvReader.csvToMarketStreamData(csvFileLocation);
    System.out.println("Market Data CSV reading is complete.");
    //create the map
    TreeMap<Integer,MarketStreamDataEntity> mapTimeMarketStreamData = new TreeMap<>();

    for (MarketStreamDataEntity marketStreamDataEntity:marketStreamDataEntityList){
      Integer readTimeInterval = TimeUtility.convertToSeconds(marketStreamDataEntity.getTradeTime());
      mapTimeMarketStreamData.put(readTimeInterval,marketStreamDataEntity);
    }
    System.out.println("map of time interval and marketStreamDataEntity creation is complete.");
    
    //Create a Strategy
    //TradingStrategy tradingStrategy = new TradingStrategy(0.6,0.6);
    //System.out.println("Strategy creation is complete.");

    //Provide input for INPUT BUY and SELL Price
    /*but till some time defined, also add condition to see the shares bought at higher price with certain amount of high price
    * This should be allowed till certain time of the day, buy upto 3 o clock, then afterwards sell when 15 mins will allow price going higher*/
    double entryBuyPrice = 411.00;

    /*Advance Sell  first if the market price is going higer and plan to see these many later at lower price,
    this agin should be allowed till certain period of time, sell advance can occur at any time at the beginning of the day
    but the corresponding buy has to happen at lower price in the same day before market closes.*/
    double entrySellPrice = 416.00;

    //provide the quantities to buy
    int quantityToBuy = 2;
    AtomicBoolean buyOrderIsPlaced = new AtomicBoolean(false);
    int quantityToSell = 2;
    AtomicBoolean sellOrderIsPlaced = new AtomicBoolean(false);

    //Order Placement Initialization
    OrderPlacement orderPlacement = new OrderPlacement(mapTimeMarketStreamData);
    System.out.println("orderPlacement instance creation is complete.");

    //iterate over the collection
    mapTimeMarketStreamData.forEach((tradeTime, marketStreamDataEntity) -> {
      //Apply the strategy on the last traded close price
      double lastTradedPrice = marketStreamDataEntity.getStockClosePrice();

      //When to place BUY ORDER
      if( lastTradedPrice <= entryBuyPrice && !buyOrderIsPlaced.get()) {
        System.out.println("Buy Order is placed for entryBuyPrice = " + entryBuyPrice + " when ltp = " + lastTradedPrice + " at time = " + TimeUtility.convertToTime(tradeTime));
        Integer simulatedTradeTime = tradeTime; // later can add delay to test
        orderPlacement.buyStocks(simulatedTradeTime, quantityToBuy,entryBuyPrice,marketStreamDataEntity.getStockName());
        buyOrderIsPlaced.set(true);
      }

      //when to pace SELL ORDER
      if(lastTradedPrice >= entrySellPrice && !sellOrderIsPlaced.get()){
        System.out.println("Sell Order is placed for entrySellPrice = " + entrySellPrice + " when ltp = " + lastTradedPrice + " at time = " + TimeUtility.convertToTime(tradeTime));
        Integer simulatedTradeTime = tradeTime ; //later can add delay to test
        orderPlacement.sellStocks(simulatedTradeTime, quantityToSell,entrySellPrice,marketStreamDataEntity.getStockName());
        sellOrderIsPlaced.set(true);
        

      }
    });
  }


}