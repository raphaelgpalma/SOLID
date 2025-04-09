package LSolid.Exemplo2;

public class ContaPoupanca implements ContaBasica {
    protected double saldo;

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
}