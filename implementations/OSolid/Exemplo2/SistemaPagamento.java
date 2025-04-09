package OSolid.Exemplo2;

public class SistemaPagamento {
    public void realizarPagamento(double valor, MetodoPagamento metodo) {
        metodo.realizarPagamento(valor);
    }
}