package patrickdev01.bank;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        // 9854 - 72944 | 804 - 64999
        Bank bank = new Bank(0, "");
        Log log = new Log();

        while (true){
            Scanner scanner = new Scanner(System.in);
            bank.setBanks();

            System.out.println("O que deseja fazer? C - Criar conta | E - Entrar na minha conta | " +
                    "L - Listar contas | X - Sair do programa");
            String op = scanner.nextLine().toUpperCase();
            log.separator();

            if(op.equals("C")){
                List<Bank> availableBanks = bank.getBanks();
                for (Bank b: availableBanks){
                    System.out.println("" + b.cod_bank + " - " + b.bankname);
                }
                log.separator();
                System.out.print("Qual banco deseja criar sua conta?");
                int bank_id = Integer.parseInt(scanner.nextLine());
                System.out.print("Digite seu Nome Completo: ");
                String name = scanner.nextLine();
                System.out.println("Crie uma senha de 8 digitos: ");
                String password = scanner.nextLine();
                bank.createNewAccount(name, password, bank_id);
            }

            else if(op.equals("E")){
                System.out.print("Digite sua Agência: ");
                String ag = scanner.nextLine();
                System.out.print("Digite o n° da conta: ");
                String cc = scanner.nextLine();
                System.out.print("Digite a senha da conta: ");
                String password = scanner.nextLine();

                Account account = bank.accountLogin(ag, cc, password);

                if(account != null){
                    operateAccount(account);
                }
            }

            else if(op.equals("L")){
                bank.setAccounts();
                List<Account> accounts = bank.getAccounts();

                log.out("" + accounts);
                for (Account acc: accounts){

                }
            }

            else if (op.equals("X")){
                break;
            }
        }
    }

    static void operateAccount(Account account){
        Scanner scanner = new Scanner(System.in);
        Log log = new Log();

        while(true) {
            System.out.println("O que deseja fazer? B - Saldo | D - Depósito | S - Saque | T - Transferir | " +
                    "E - Sair da conta");
            String op = scanner.nextLine().toUpperCase();

            if (op.equals("D")) {
                System.out.println("Qual o valor deseja depositar?");
                int value = Integer.parseInt(scanner.nextLine());
                account.deposit(value);
            }
            else if (op.equals("B")) {
                log.separator();
                System.out.println("Seu Saldo: R$" + account.getBalance());
                log.separator();
            }

            else if(op.equals("S")){
                System.out.println("Qual o valor deseja sacar?");
                int value = scanner.nextInt();
                account.drawOut(value);
            }

            else if(op.equals("T")){
                System.out.print("Digite a Agência: ");
                String ag = scanner.nextLine();
                System.out.print("Digite o n° da conta: ");
                String cc = scanner.nextLine();
                System.out.print("Valor: ");
                int transferValue = Integer.parseInt(scanner.nextLine());
                log.separator();
                account.transfer(ag, cc, transferValue);
                log.separator();
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
