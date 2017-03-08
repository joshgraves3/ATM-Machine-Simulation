/**
  * Created by Josh on 12/8/16.
  */

import scala.io.StdIn.readLine

class Business(balance : Double, name : String) extends Account{

  override val accountBalance: Double = balance
  override val accountName: String = name

  //private access for important fields that will be changed
  private var protectedBalance = accountBalance
  private var protectedAccountName = accountName

  private var minBalance = 50000

  override def deposit(amount: Double): Unit = {
    protectedBalance += amount
    println("Deposit successful.\n")
  }

  override def withdraw(amount: Double): Unit = {
    if (amount > protectedBalance) {
      println("Insufficient funds.\n")
    }
    else if ((protectedBalance - amount) < minBalance) {
      println("WARNING: This transaction will take your account balance below the requirement and a fee will be assessed.\n" +
        "Do you wish to continue (Enter 'y' for yes and 'n' for no)?")
      val answer = readLine()

      if (answer.equals("y")) {
        protectedBalance -= amount
      }
      else if (answer.equals("n")) {
        println("The withdrawal was cancelled.\n")
      }
      else {
        println("Bad input. The withdrawal did not go through.\n")
      }
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
