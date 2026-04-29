package com.GPTemplate.loan;

import java.util.Scanner;

	 class TestLoanTemplate {

			private double annualInterestRate;

			private int numberOfYears;

			private double loanAmount;

			private java.util.Date loanDate;
			

			/** No-arg constructor */

			public TestLoanTemplate() {

				this(2.5, 1, 1000);

			}


			/** Construct a loan with specified annual interest rate,

			number of years, and loan amount

			 */

			public TestLoanTemplate(double annualInterestRate, int numberOfYears,

			double loanAmount) {

				this.annualInterestRate = annualInterestRate;
	
				this.numberOfYears = numberOfYears;

				this.loanAmount = loanAmount;

				loanDate = new java.util.Date();

			}


			/** Return annualInterestRate */

			public double getAnnualInterestRate() {

				return annualInterestRate;

			}


			/** Set a new annualInterestRate */

			public void setAnnualInterestRate(double annualInterestRate) {

				this.annualInterestRate = annualInterestRate;

			}


			/** Return numberOfYears */

			public int getNumberOfYears() {

				return numberOfYears;

			}


			/** Set a new numberOfYears */
			
			public void setNumberOfYears(int numberOfYears) {
			
				this.numberOfYears = numberOfYears;
			
			}


			/** Return loanAmount */

			public double getLoanAmount() {

				return loanAmount;

			}


			/** Set a newloanAmount */

			public void setLoanAmount(double loanAmount) {

				this.loanAmount = loanAmount;

			}


			/** Find monthly payment */

			public double getMonthlyPayment() {

				double monthlyInterestRate = annualInterestRate / 1200;

				double monthlyPayment = loanAmount * monthlyInterestRate / (1 -

						(1 / Math.pow(1 + monthlyInterestRate, numberOfYears * 12)));

				return monthlyPayment;

			}


			/** Find total payment */

			public double getTotalPayment() {

				double totalPayment = getMonthlyPayment() * numberOfYears * 12;

				return totalPayment;

			}


			// 11/9

			/** Find total interest */

			public double getTotalInterest() {

				// totalInterest is the difference of total payment minus loan amount
				double totalInterest = getTotalPayment() - loanAmount; 

				return totalInterest;

			}


			/** Return loan date */

			public java.util.Date getLoanDate() {

				return loanDate;

			}


	}

	public class LoanTemplate {

		/** Main method */

		public static void main(String[] args) {

			// Create a Scanner

			Scanner input = new Scanner(System.in);


			// Enter yearly interest rate

			System.out.print(

					"Enter annual interest rate, for example, 8.25: ");

			double annualInterestRate = input.nextDouble();


			// Enter number of years

			System.out.print("Enter number of years as an integer: ");

			int numberOfYears = input.nextInt();


			// Enter loan amount

			System.out.print("Enter loan amount, for example, 120000.95: ");

			double loanAmount = input.nextDouble();


			//11/9

			// Enter extra payment amount

			System.out.print("Enter extra payment amount, for example, 1000.00: ");

			double extraPaymentAmount = input.nextDouble();


			// Create Loan object

			TestLoanTemplate loan =

					new TestLoanTemplate(annualInterestRate, numberOfYears, loanAmount);


			// Display loan date, monthly payment, and total payment, and total interest

			System.out.printf("The loan was created on %s%n" +

				"The monthly payment is %.2f%nThe total payment is %.2f%nThe total interest is %.2f%n%n",

				loan.getLoanDate().toString(),

				loan.getMonthlyPayment(),

				loan.getTotalPayment(),

				loan.getTotalInterest());


				extraPayFirstMonth(loan, extraPaymentAmount);

				System.out.println();

				extraPayEveryMonth(loan, extraPaymentAmount);

		}


		/** Extra payment first month only */

		public static void extraPayFirstMonth(TestLoanTemplate l, double extraPay) {
	
			System.out.printf("Extra payment only first month $%.2f%n", extraPay);

			double myLoanAmount =l.getLoanAmount();

			double myMonthlyInterestRate = l.getAnnualInterestRate()/1200;

			double myMonthlyPayment = l.getMonthlyPayment();

			int month= 0;

			double interest = 0.0;

			double principal = 0.0;

			double totalInterest = 0.0;


			while (myLoanAmount >= myMonthlyPayment) {

				// calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
				interest = myLoanAmount * myMonthlyInterestRate;
				// calculate the principal paid this month from myMonthlyPayment minus the new month's interest
				principal = myMonthlyPayment - interest; 
				if (month == 0) {
					myLoanAmount -= (principal + extraPay);
				}
				else {
					myLoanAmount -= principal;
				}
				totalInterest += interest;
				if (myLoanAmount < 0) myLoanAmount = 0;
				// increment the month count
				month++ ; 
			}


			if (myLoanAmount < myMonthlyPayment) {
				// calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
				interest = myLoanAmount * myMonthlyInterestRate;
				totalInterest += interest;
				myLoanAmount = 0;
				// increment the month count
				month++; 
			}

			double interestSaved = l.getTotalInterest() - totalInterest;

			/*

	System.out.println("total interest " + totalInterest);

	System.out.println("interest saved " + interestSaved);

	System.out.println("total month " + month);

			 */

			System.out.printf("Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",

					totalInterest, interestSaved, month);

		}


		/** Extra payment every month */

		public static void extraPayEveryMonth(TestLoanTemplate l, double extraPay) {

			System.out.printf("Extra payment every month $%.2f%n", extraPay);

			double myLoanAmount =l.getLoanAmount();

			double myMonthlyInterestRate = l.getAnnualInterestRate()/1200;

			double myMonthlyPayment = l.getMonthlyPayment();

			int month= 0;

			double interest = 0.0;

			double principal = 0.0;

			double totalInterest = 0.0;


			while (myLoanAmount >= (myMonthlyPayment + extraPay)) {

				// calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
				interest = myLoanAmount * myMonthlyInterestRate;
				// calculate the principal paid this month from myMonthlyPayment minus the new month's interest
				principal = myMonthlyPayment - interest;
				// calculate the new myLoanAmount after minus this month's principal, then minus extra payment
				myLoanAmount = myLoanAmount - principal - extraPay;
				// add the interest paid this month to totalInterest
				totalInterest += interest;
				// increment the month count
				month++;

			}

			while (myLoanAmount >= myMonthlyPayment) {

				// calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
				interest = myLoanAmount * myMonthlyInterestRate;

				// calculate the principal paid this month from myMonthlyPayment minus the new month's interest
				principal = myMonthlyPayment - interest;

				// calculate the new myLoanAmount after minus this month's principal
				myLoanAmount = myLoanAmount - principal;

				// add the interest paid this month to totalInterest
				totalInterest += interest;
				// increment the month count
				month++; 
			}

			if (myLoanAmount < myMonthlyPayment) {
				
				// calculate the new month's interest based on myLoanAmount and myMonthlyInterestRate
				interest = myLoanAmount * myMonthlyInterestRate;
				// add the interest paid this month to totalInterest
				totalInterest += interest;
				// increment the month count
				month++;
			}

			double interestSaved = l.getTotalInterest() - totalInterest;

			System.out.printf("Total interest $%.2f%nTotal interest saved $%.2f%nTotal months %d %n",

					totalInterest, interestSaved, month);

		}
 
	}

