package patrickdev01.bank;

public class Account {
    private static final int MAX_LENGTH = 12;
    private String ag;
    private String cc;
    private String name;
    private double balance;
    private Log log;

    public Account(String ag, String cc, String name){
        this.ag = ag;
        this.cc = cc;
        setName(name);
        log = new Log();
    }

    public void setName(String name){
        if(name.length() > MAX_LENGTH){
            this.name = name.substring(0, MAX_LENGTH);
        }

        else{
            this.name = name;
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
