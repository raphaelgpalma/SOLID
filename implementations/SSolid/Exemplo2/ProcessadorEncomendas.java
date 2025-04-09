package SSolid.Exemplo2;

import java.util.Scanner;

public class ProcessadorEncomendas {
    public void processar() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o ID da encomenda: ");
            String idEncomenda = sc.nextLine();
            System.out.println("Digite o peso (em kg): ");
            double peso = sc.nextDouble();
            double valorFrete = calcularFrete(peso);
            System.out.println("Valor do frete calculado: " + valorFrete);
            GravadorEncomendas gravador = new GravadorEncomendas();
            gravador.salvar(idEncomenda, valorFrete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private double calcularFrete(double peso) {
        return peso * 10;
    }
}