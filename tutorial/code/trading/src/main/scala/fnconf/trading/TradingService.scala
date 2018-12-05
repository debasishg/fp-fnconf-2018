package fnconf.trading

import cats.data._
import cats.implicits._
import Kleisli._

import TradeModel._

trait TradingService { 

  def clientOrders: Kleisli[ErrorOr, List[ClientOrder], List[Order]]
  def execute(market: Market, brokerAccount: Account): Kleisli[ErrorOr, List[Order], List[Execution]]
  def allocate(accounts: List[Account]): Kleisli[ErrorOr, List[Execution], List[Trade]]
  
  def tradeGeneration(market: Market, broker: Account, clientAccounts: List[Account]) = {
    clientOrders               andThen    
    execute(market, broker)    andThen   
    allocate(clientAccounts)
  }
}

