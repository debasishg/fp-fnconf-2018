package fnconf.trading

import cats.data._
import cats.instances.list._
import cats.instances.either._
import cats.syntax.traverse._

import TradeModel._

trait TradingServiceInterpreter extends TradingService {

  def clientOrders: Kleisli[ErrorOr, List[ClientOrder], List[Order]] = Kleisli(fromClientOrders)

  def execute(market: Market, brokerAccount: Account) = Kleisli[ErrorOr, List[Order], List[Execution]] { orders =>
    Right(
      for { 
        order <- orders
        item <- order.items
      } yield Execution(brokerAccount, item.ins, "e-123", market, item.price, item.qty)
    )
  }

  def allocate(accounts: List[Account]) = Kleisli[ErrorOr, List[Execution], List[Trade]] { executions =>
    (for {
      execution <- executions
      q = execution.quantity / accounts.size
      account <- accounts
    } yield makeTrade(account, execution.instrument, "t-123", execution.market, execution.unitPrice, q)).traverse(identity)
  }
}

object TradingServiceInterpreter extends TradingServiceInterpreter

