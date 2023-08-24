package patrickdev01.bank;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Bank bank = new Bank(0, "");
        Account account = new Account();
        Scanner scanner = new Scanner(System.in);
        Log log = new Log();
        bank.setBanks();

        while (true){
            System.out.println("O que deseja fazer? C - Criar conta | E - Entrar na minha conta | X - Sair do programa");
            String op = scanner.nextLine().toUpperCase();
            log.breakLine();

            List<Bank> availableBanks = bank.getBanks();
            if(op.equals("C")){
                for (Bank b: availableBanks){
                    System.out.println("" + b.cod_bank + " - " + b.bankname);
                }
                log.breakLine();
                System.out.println("Qual banco deseja criar sua conta?");
                int bank_id = scanner.nextInt();
                System.out.println("Digite seu Nome Completo: ");
                String name = scanner.nextLine();
                System.out.println("Crie uma senha de 8 digitos: ");
                String password = scanner.nextLine();
                account.createNewAccount(name, password, bank_id);
            }

            if(op.equals("E")){
                System.out.print("Digite sua Agência: ");
                String ag = scanner.nextLine();
                System.out.print("Digite o n° da conta: ");
                String cc = scanner.nextLine();
                System.out.print("Digite a senha da conta: ");
                String password = scanner.nextLine();
            }

            else if (op.equals("X")){
                break;
            }

            else{
                System.out.println("Comando inválido! tente novamente");
            }
        }
    }

    static void operateAccount(Account account){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Qual o valor deseja fazer? D - Depósito | S - Saque | E - Sair da conta");
            String op = scanner.nextLine().toUpperCase();

            if (op.equals("D")) {
                System.out.println("Qual o valor deseja depositar?");
                double value = scanner.nextDouble();
                account.deposit(value);
            }

            else if(op.equals("S")){
                System.out.println("Qual o valor deseja sacar?");
                double value = scanner.nextDouble();
                if(!account.drawOut(value)){
                    System.out.println("Ops! Não foi possivel fazer o saque1");
                }
            }

            else if(op.equals("E")){
                break;
            }

            else {
                System.out.println("Comando inválido! tente novamente");
            }

            scanner = new Scanner(System.in);
        }
    }
}
