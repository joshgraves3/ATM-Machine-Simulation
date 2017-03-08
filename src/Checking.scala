/**
  * Created by Josh on 12/8/16.
  */
class Checking(balance : Double, name : String) extends Account {

  override val accountBalance: Double = balance

  override val accountName: String = name

  //private access for important fields that will be changed
  private var protectedBalance = accountBalance
  private var protectedAccountName = accountName

  override def deposit(amount: Double): Unit = {
    protectedBalance += amount
    println("Deposit successful.\n")
  }

  override def withdraw(amount: Double): Unit = {
    if (amount > protectedBalance) {
      println("Insufficient funds.\n")
    }
    else {
      protectedBalance -= amount
      println("Withdrawal successful.\n")
    }
  }

  override def checkBalance(): Double = {
    protectedBalance
  }

}
