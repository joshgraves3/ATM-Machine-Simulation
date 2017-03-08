/**
  * Created by Josh on 12/8/16.
  */
class Savings(balance : Double, name : String) extends Account{

  override val accountBalance: Double = balance
  override val accountName: String = name

  //private access for important fields that will be changed
  private var protectedBalance = accountBalance
  private val protectedAccountName = accountName

  private var withdrawalsAccessed = 4

  override def deposit(amount: Double): Unit = {
    protectedBalance += amount
    println("Deposit successful.\n")
  }

  override def withdraw(amount: Double): Unit = {
    if (amount > protectedBalance) {
      println("Insufficient funds\n")
    }
    else if (withdrawalsAccessed <= 0){
      println("You have withdrawed from this account too many times.\n")
    }
    else {
      protectedBalance -= amount
      withdrawalsAccessed -= 1
      println("Withdrawal successful.\n")
    }
  }

  override def checkBalance(): Double = {
    protectedBalance
  }

}
