# Relatório SOLID - OSOLID (Princípio Aberto/Fechado)

## Pacote: OSolid.Exemplo2

### Problema Identificado
A classe `SistemaPagamento` original violava o Princípio Aberto/Fechado (OCP) porque estava fechada para extensão e aberta para modificação. A lógica de pagamento era implementada com uma estrutura `if-else` que verificava o tipo de método de pagamento. Adicionar um novo método (ex.: "TRANSFERENCIA") exigiria alterar diretamente o método `realizarPagamento`, o que contraria o OCP.

#### Código Antes
```java
package OSolid.Exemplo2;

public class SistemaPagamento {
    public void realizarPagamento(double valor, String metodo) {
        if ("CARTAO".equalsIgnoreCase(metodo)) {
            System.out.println("Pagamento de R$" + valor + " realizado com CARTÃO.");
        } else if ("PIX".equalsIgnoreCase(metodo)) {
            System.out.println("Pagamento de R$" + valor + " realizado via PIX.");
        } else if ("BOLETO".equalsIgnoreCase(metodo)) {
            System.out.println("Pagamento de R$" + valor + " realizado via BOLETO.");
        } else {
            System.out.println("Método de pagamento não suportado!");
        }
    }
}
```
Solução Aplicada

Para corrigir a violação do OCP, foi introduzida uma interface MetodoPagamento e classes específicas para cada tipo de pagamento. A classe SistemaPagamento agora delega a execução para o método implementado, tornando o sistema aberto para extensão (novas classes) e fechado para modificação.
Código Depois
Interface MetodoPagamento
```java
package OSolid.Exemplo2;

public interface MetodoPagamento {
    void realizarPagamento(double valor);
}

```
### PagamentoCartao
```java
package OSolid.Exemplo2;

public class PagamentoCartao implements MetodoPagamento {
    @Override
    public void realizarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado com CARTÃO.");
    }
}
```
### PagamentoPix
```java
package OSolid.Exemplo2;

public class PagamentoPix implements MetodoPagamento {
    @Override
    public void realizarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado via PIX.");
    }
}
```
### PagamentoBoleto
```java
package OSolid.Exemplo2;

public class PagamentoBoleto implements MetodoPagamento {
    @Override
    public void realizarPagamento(double valor) {
        System.out.println("Pagamento de R$" + valor + " realizado via BOLETO.");
    }
}
```
### SistemaPagamento
```java
package OSolid.Exemplo2;

public class SistemaPagamento {
    public void realizarPagamento(double valor, MetodoPagamento metodo) {
        metodo.realizarPagamento(valor);
    }
}
```
## Justificativa

- **Aberto para Extensão**: Novos métodos de pagamento podem ser adicionados criando novas classes que implementem `MetodoPagamento`, sem alterar `SistemaPagamento`.
- **Fechado para Modificação**: A classe `SistemaPagamento` não precisa ser modificada para suportar novos métodos, eliminando a necessidade de `if-else`.
- **Flexibilidade**: O sistema agora é mais modular e fácil de manter ou expandir.
