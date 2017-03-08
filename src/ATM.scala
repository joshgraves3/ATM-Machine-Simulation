/**
  * Created by Josh on 12/8/16.
  */

import scala.io.StdIn.{readInt, readDouble, readLine}
import scala.collection.mutable.ArrayBuffer

class ATM {

  def simulate(): Unit = {

    val accountList = new ArrayBuffer[Account]()

    val savings = new Savings(10000, "Savings_1")
    val checking = new Checking(2000, "Checking_1")
    val business = new Business(100000, "Business_1")

    accountList.append(savings)
    accountList.append(checking)
    accountList.append(business)

    var state = 0
    var accountNo = 0
    var amt : Double = 0
    var exit = false
    var exists = false


    while(!exit) {

      println("Welcome to the ATM Machine.\n" +
        "Would you like to\n" +
        "1) Go to an account\n" +
        "2) Make a transfer between accounts\n" +
        "3) Open a new account\n" +
        "4) Exit\n")

      try {
        state = readInt()

        state match {
          case 1 =>
            var accountManagement = true
            while(accountManagement) {
              println("Which account would you like to access?")
              var count = 1
              for (account <- accountList) {
                println(count + ") " + account.accountName)
                count += 1
              }
              println(count + ") Exit.")
              accountNo = readInt()

              if(accountNo < accountList.size + 1) {
                println("You have selected account: " + accountList(accountNo -1).accountName + "\n")

                println("Would you like to:\n" +
                  "1) Deposit\n" +
                  "2) Make a Withdrawal\n" +
                  "3) Check Balance\n" +
                  "4) Exit Account Edit\n"
                )

                val action = readInt()
                action match {
                  case 1 =>
                    print("Enter deposit amount: ")
                    amt = readDouble()
                    accountList(accountNo - 1).deposit(amt)
                  case 2 =>
                    print("Enter withdrawal amount: ")
                    amt = readDouble()
                    accountList(accountNo - 1).withdraw(amt)
                  case 3 =>
                    println("Available balance: " + accountList(accountNo - 1).checkBalance() + "\n")
                  case 4 =>
                    accountManagement = false
                    println("Exiting account management.\n")
                }
              }

              else {
                accountManagement = false
                println("Exiting account management.\n")
              }

            }

          case 2 =>
            println("Available accounts: ")

            var count = 1
            for (account <- accountList) {
              println(count + ") " + account.accountName)
              count += 1
            }

            println("Enter number of the account to pull from: ")
            val account1 = readInt()
            println("Enter the account to transfer to: ")
            val account2 = readInt()
            print("Enter transfer amount: ")
            val transferAmount = readDouble()
            transferFunds(accountList(account1 - 1), accountList(account2 - 1), transferAmount)

          case 3 =>
            print("Enter account type (savings, checking, business): ")
            val t = readLine()

            print("Enter account name: ")
            val n = readLine()

            for(account <- accountList) {
              if (account.accountName.equals(n)) {
                println("Account name already exists. Exiting.")
                exists = true
              }
            }
            if (!exists) {
              print("Enter initial deposit amount (for your sake enter a double please): ")
              val d = readDouble()

              val makeTheAccount = makeNewAccount(d, n, t)

              if (makeTheAccount != null) {
                accountList.append(makeTheAccount)
              }
            }
          case 4 =>
            println("Thank you for using the ATM machine!")
            exit = true
        }
      } catch  {
        case numFormat: java.lang.NumberFormatException =>
          println("Please enter a number value.\n")
        case idx: java.lang.IndexOutOfBoundsException =>
          println("That is not a valid account number. Exiting.\n")
        case s: scala.MatchError =>
          println("That is not a valid action.\n")
      }
    }
  }

  def transferFunds(a1 : Account, a2 : Account, transferAmt : Double): Unit = {
    if (a1.checkBalance() < transferAmt) {
      println("Account pulling from has insufficient funds")
    }
    else {
      a1.withdraw(transferAmt)
      a2.deposit(transferAmt)
    }
  }

  def makeNewAccount(initialDeposit : Double, accountName : String, accountType : String): Account = {
    if (accountType.toLowerCase.equals("savings")) {
      val newAccount = new Savings(initialDeposit, accountName)
      newAccount
    }
    else if (accountType.toLowerCase.equals("checking")) {
      val newAccount = new Checking(initialDeposit, accountName)
      newAccount
    }
    else if (accountType.toLowerCase.equals("business")) {
      val newAccount = new Business(initialDeposit, accountName)
      newAccount
    }
    else {
      println("Bad account type. Exiting account creation.")
      null
    }
  }
}
