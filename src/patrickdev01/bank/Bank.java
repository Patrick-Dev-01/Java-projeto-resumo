package patrickdev01.bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
    public int cod_bank;
    public String bankname;
    private List<Bank> banks;
    private Database database = new Database();
    private Log log = new Log();
    private List<Account> accounts = new ArrayList<>();
    public Bank(){}
    public Bank(int cod_bank, String bankname){
        this.cod_bank = cod_bank;
        this.bankname = bankname;
    }

    public void createNewAccount(String name, String password, int cod_bank){
        Connection connection = database.connect();
        Random random = new Random();
        String ag = "" + random.nextInt(9999);
        String cc = "" + random.nextInt(99999);

        try{
            String strSQL = String.format("INSERT INTO accounts (ag, cc, username, password, balance, Cod_bank) VALUES ('%s', '%s', '%s', '%s'" +
                    ", %d, %d);", ag, cc, name, password, 0, cod_bank);
            PreparedStatement stament = connection.prepareStatement(strSQL);
            stament.executeUpdate();

            System.out.println("Conta criada com sucesso!");
            log.separator();
            System.out.println("Agência: " + ag + " | Conta: " + cc);
            log.separator();

            connection.close();
        }

        catch(SQLException ex){
            System.out.println("An Error ocurred while create a new account: " + ex);

        }
    }

    public Account accountLogin(String ag, String cc, String password){
        Connection connection = database.connect();
        ResultSet rs;
        Account account = null;

        try {
            String strSQL = String.format("SELECT a.id_account, a.ag, a.cc, a.username, a.balance, b.bankname FROM accounts AS a JOIN " +
                    "banks AS b WHERE a.ag = '%s' AND a.cc = '%s' AND a.password = '%s'" +
                    "AND a.Cod_bank = b.cod_bank;", ag, cc, password);
            PreparedStatement statement = connection.prepareStatement(strSQL);
            rs = statement.executeQuery();

            log.separator();
            if(rs.next()){
                account = new Account(rs.getInt(1));

                System.out.println("Autenticação feita com sucesso!");
                log.separator();
                System.out.println(String.format("Usuário: %s | Agência: %s | Conta: %s | Banco: %s",
                        rs.getString(4), rs.getString(2), rs.getString(3),
                        rs.getString(6)));
                log.separator();
            }

            else{
                System.out.println("Dados incorretos!");
                log.separator();
            }

            connection.close();
        }

        catch (SQLException ex){
            System.out.println("Error ocurred while try login in account: " + ex);
        }

        return account;
    }

    public void setBanks() {
        Connection connection = database.connect();
        List<Bank> banks = new ArrayList<>();
        ResultSet rs;

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

    public void setAccounts(){
        Connection connection = database.connect();
        ResultSet rs;

        try{
            String strSQL = "SELECT acc.id_account, acc.ag, acc.cc, acc.username, acc.balance, b.bankname " +
                    "FROM accounts as acc join banks as b WHERE acc.Cod_bank = b.cod_bank;";

            PreparedStatement statement = connection.prepareStatement(strSQL);
            rs = statement.executeQuery();

            while (rs.next()){
                accounts.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getDouble(5), rs.getString(6)));
            }
        }

        catch(SQLException ex){
            log.out("Error while get accounts: " + ex);
        }
    }

    public List<Account> getAccounts(){
        return this.accounts;
    }

    public List<Bank> getBanks(){
        return this.banks;
    }
}
