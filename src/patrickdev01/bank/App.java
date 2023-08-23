package patrickdev01.bank;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args){
        Bank santander = new Bank("0001");
        Scanner scanner = new Scanner(System.in);

        // C = criar conta
        // E = sair (exit)

        // Depositar, Sacar, Sair
        while (true){
            System.out.println("O que deseja fazer? C - Criar conta | E - Sair do programa");
            String op = scanner.nextLine().toString().toUpperCase();

            if(op.equals("C")){
                System.out.print("Digite seu Nome: ");
                String name = scanner.nextLine();
                Account account = santander.generateAccount(name);
                santander.insertAccount(account);

                operateAccount(account);
            }
            else if (op.equals("E")){
                break;
            }

            else{
                System.out.println("Comando inválido! tente novamente");
            }
        }

        List<Account> accountList = santander.getAccounts();

        // Nomenclatura para fazer loops dentro de objetos
        for (Account cc: accountList){
            System.out.println(cc);
        }

        santander.outputTotal();
    }

    static void operateAccount(Account account){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Qual o valor deseja fazer? D - Depósito | S - Saque | E - Sair da conta");
            String op = scanner.nextLine().toString().toUpperCase();

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
