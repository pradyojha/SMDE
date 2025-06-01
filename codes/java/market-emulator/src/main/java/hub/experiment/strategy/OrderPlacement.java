package hub.experiment.strategy;

import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicBoolean;




public class OrderPlacement {
  private TreeMap<Integer,MarketStreamDataEntity> mapTimeMarketStreamData;
  //private TradingStrategy tradingStrategy;

  public OrderPlacement(TreeMap<Integer,MarketStreamDataEntity> mapTimeMarketStreamData){
    this.mapTimeMarketStreamData = mapTimeMarketStreamData;
    //this.tradingStrategy = tradingStrategy;
  }

  public boolean buyStocks(int orderPlacedTime, int quantity, double buyTargetPrice, String stockName){
    AtomicBoolean buyResult = new AtomicBoolean(false);
    AtomicBoolean buyOrderExecuted = new AtomicBoolean(false);
    System.out.println("Buy Order Placed Time  = " + TimeUtility.convertToTime(orderPlacedTime));

    mapTimeMarketStreamData.forEach((timeInterval, marketStreamDataEntity) -> {
      //System.out.println("Check Stream Data Time for buy Order execution = " + TimeUtility.convertToTime(timeInterval));
      if (timeInterval >= orderPlacedTime && !buyOrderExecuted.get()) {
        //double buyingPriceUponStrategy = tradingStrategy.evaluateBuyingPrice(marketStreamDataEntity.getStockClosePrice());
        if (buyTargetPrice <= marketStreamDataEntity.getStockClosePrice()) {
          buyResult.set(true);
          System.out.println(
              "Buy Order is executed at price " + marketStreamDataEntity.getStockClosePrice() + " at time "
                  + TimeUtility.convertToTime(timeInterval));
          buyOrderExecuted.set(true);
        }
      }
    });

    return buyResult.get();
  }

  public boolean sellStocks(int orderPlacedTime, int quantity, double sellTargetPrice, String stockName){
    AtomicBoolean result = new AtomicBoolean(false);
    AtomicBoolean sellOrderExecuted = new AtomicBoolean(false);
    System.out.println("Sell Order Placed Time  = " + TimeUtility.convertToTime(orderPlacedTime));
    mapTimeMarketStreamData.forEach((timeInterval, marketStreamDataEntity) -> {
      //System.out.println("Check Stream Data Time for sell order execution = " + TimeUtility.convertToTime(timeInterval));
      if (timeInterval >= orderPlacedTime && !sellOrderExecuted.get()) {
        //double sellingPriceUponStrategy = tradingStrategy.evaluateSellingPrice(marketStreamDataEntity.getStockClosePrice());
        if (sellTargetPrice <= marketStreamDataEntity.getStockClosePrice()) {
          result.set(true);
          System.out.println(
              "Sell Order is executed at price " + marketStreamDataEntity.getStockClosePrice() + " at time "
                  + TimeUtility.convertToTime(timeInterval));
          sellOrderExecuted.set(true);
        }
      }
    });

    return result.get();
  }
}
