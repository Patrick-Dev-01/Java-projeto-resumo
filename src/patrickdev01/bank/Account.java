package patrickdev01.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class Account {
    private String ag;
    private String cc;
    private String name;
    private Database database = new Database();
    private double balance;
    private Log log = new Log();;

    public Account(){
    }

    public void createNewAccount(String name, String password, int cod_bank){
        Connection connection = database.connect();
        Random random = new Random();
        String ag = "" + random.nextInt(9999);
        String cc = "" + random.nextInt(99999);

        String strSQL = String.format("INSERT INTO accounts (ag, cc, username, password, balance, Cod_bank) VALUES (%s, %s, %s, %s" +
                ", %d, %d);", ag, cc, name, password, 0, cod_bank);
        try{
            PreparedStatement stament = connection.prepareStatement(strSQL);
            stament.executeUpdate();

            System.out.println("Conta criada com sucesso!");
            log.breakLine();
            System.out.println("Agência: " + ag + " | Conta: " + cc);
            log.breakLine();

            connection.close();
        }

        catch(SQLException ex){
            System.out.println("An Error ocurred while create a new account: " + ex);
            System.out.println(strSQL);

        }
    }

    public boolean drawOut(double value){
        if(balance < value){
            log.out("SAQUE - R$" + value + " Seu saldo atual é de R$" + balance);
            return false;
        }

        balance -= value;
        log.out("SAQUE - R$" + value + " Seu saldo atual é de R$" + balance);
        return true;
    }

    public void deposit(double value){
        balance += value;
        log.out("DEPOSITO - R$" + value + " Sua conta agora é de R$" + balance);
    }

    public double getBalance(){
        return  balance;
    }

    @Override
    public String toString(){
        String result = "A conta " + this.name + " " + this.ag + " / " + this.cc + " possui: R$ " + this.balance;

        return result;
    }
}
