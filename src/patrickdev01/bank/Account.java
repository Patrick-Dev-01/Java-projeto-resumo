package patrickdev01.bank;

import java.sql.*;
import java.util.Random;

public class Account {
    private int id_account;
    private String ag;
    private String cc;
    private String name;
    private String bank;
    private double balance;
    private Database database = new Database();
    private Log log = new Log();
//String ag, String cc, String name, String bank, double balance
    public Account(int id_account){
        this.id_account = id_account;
        /*this.ag = ag;
        this.cc = cc;
        this.name = name;
        this.bank = bank;
        this.balance = balance;*/
    }

    public Account(){}

    public boolean drawOut(int value){
        int currentBalance = getBalance();
        Connection connection = database.connect();
        ResultSet rs;
        boolean isDrawedOut = false;

        if(currentBalance < value){
            log.out("Você não possui saldo suficiente para sacar | Saldo R$" + currentBalance +
                    " valor do saque R$" + value);

            isDrawedOut = false;
        }

        log.separator();

        try{
            String updateBalanceSQL = String.format("UPDATE accounts set balance = %d WHERE id_account = %d;"
                    ,currentBalance - value, this.id_account);

            PreparedStatement statement = connection.prepareStatement(updateBalanceSQL);
            statement.executeUpdate();

            log.out("SAQUE - R$" + value + " | Novo saldo R$" + (currentBalance - value));

            connection.close();

            isDrawedOut = true;
        }

        catch (SQLException ex){
            log.out("Error ocurred while cash out: " + ex);
        }

        log.separator();

        return isDrawedOut;
    }

    public void deposit(int value){
        int currentBalance = getBalance();
        Connection connection = database.connect();

        try{
            String updateBalanceSQL = String.format("UPDATE accounts set balance = %d WHERE id_account = %d;"
                    ,currentBalance + value, this.id_account);
            PreparedStatement statement = connection.prepareStatement(updateBalanceSQL);
            statement.executeUpdate(updateBalanceSQL);

            log.separator();
            log.out("Deposito feito com sucesso!");
            log.separator();

            connection.close();
        }

        catch (SQLException ex){
            log.out("Error while deposit new balance: " + ex);
        }
    }
    public int getBalance(){
        Connection connection = database.connect();
        ResultSet rs;
        int balance = 0;

        try{
            String strSQL = String.format("SELECT balance FROM accounts WHERE id_account = %d;", this.id_account);

            PreparedStatement statement = connection.prepareStatement(strSQL);
            rs = statement.executeQuery();

            if(rs.next()){
                balance = rs.getInt(1);
            }

            connection.close();
        }

        catch (SQLException ex){
            log.out("Error while get current balance: " + ex);
        }

        return balance;
    }

    @Override
    public String toString(){
        String result = "A conta " + this.name + " " + this.ag + " / " + this.cc + " possui: R$ " + this.balance;

        return result;
    }
}
