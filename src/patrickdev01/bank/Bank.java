package patrickdev01.bank;

import javax.swing.text.html.parser.Parser;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    public int cod_bank;
    public String bankname;
    private List<Bank> banks;
    private Database database = new Database();

    public Bank(int cod_bank, String bankname){
        this.cod_bank = cod_bank;
        this.bankname = bankname;
    }

    public void setBanks() {
        Connection connection = database.connect();
        List<Bank> banks = new ArrayList<>();
        ResultSet rs = null;

        if(connection == null){
            System.out.println("Não foi possivel listar os bancos, tente novamente mais tarde");
        }

        else{
            try{
                String strSql = "SELECT *FROM BANKS;";
                PreparedStatement statement = connection.prepareStatement(strSql);

                rs = statement.executeQuery();

                while(rs.next()){
                    Bank bank = new Bank(rs.getInt(1), rs.getString(2));
                    banks.add(bank);
                }

                connection.close();

                this.banks = banks;
            }

            catch (SQLException ex){
                System.out.println("Error ocurred when trying execute a query in database: " + ex);
                ex.printStackTrace();
            }
        }
    }

    public List<Bank> getBanks(){
        return this.banks;
    }

    public void getAccount(String ag, String cc, String password){
        Connection connection = database.connect();

        if(connection == null){
            System.out.println("Não foi possivel criar uma conta, tente novamente mais tarde");
        }

        else {

        }
    }

    /*public Account generateAccount(String name){
        Account account = new Account(ag, "" + lastAccount , name);
        lastAccount++;
        return account;
    }*/
}
