package fnconf.trading

import java.util.{ Date, Calendar }

trait OrderModel {this: RefModel =>
  case class LineItem(ins: Instrument, qty: BigDecimal, price: BigDecimal)
  case class Order(no: String, date: Date, customer: Customer, items: List[LineItem])

  type ClientOrder = Map[String, String]

  def fromClientOrders: List[ClientOrder] => ErrorOr[List[Order]] = { cos =>
    Right(
      cos.map {co =>
        val ins = co("instrument").split("-")
        val lineItems = ins map {in =>
          val arr = in.split("/")
          LineItem(arr(0), BigDecimal(arr(1)), BigDecimal(arr(2)))
        }
        Order(co("no"), Calendar.getInstance.getTime, co("customer"), lineItems.toList)
      }
    )
  }
}
