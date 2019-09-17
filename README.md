# Revolut
Revolut Assignment

## Design

This has one API

http://localhost:8080/RevolutMoneyTransfer/Accounts/Transfer/{SourceAccount}/{TargetAccount}/{TransferAmount}

Example:

http://localhost:8080/RevolutMoneyTransfer/Accounts/Transfer/Account1/Account1/30

It can also have several other api such as-
1. To access a particular account
2. Check account balance

## How to use

This soultion can be used using the following steps - 

1. Run RMTServer class. 
Keep it running. It behaves as server

2. Run RMTClient class
This is a class having test case.

## Note

1. This does not have very comprehensive test case for each level.
