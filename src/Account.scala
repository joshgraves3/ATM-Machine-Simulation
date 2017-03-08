/**
  * Created by Josh on 12/8/16.
  */
abstract class Account {

  protected val accountBalance : Double

  val accountName : String

  def withdraw(amount : Double)

  def deposit(amount : Double)

  def checkBalance():Double

}
