# Relatório SOLID - LSOLID (Princípio da Substituição de Liskov)

## Pacote: LSolid.Exemplo2

### Problema Identificado
O código original violava o Princípio da Substituição de Liskov (LSP) porque a classe `ContaPoupanca` herdava de `ContaBancaria`, mas sobrescrevia o método `sacar` para lançar uma exceção. Isso impedia que `ContaPoupanca` fosse substituída por `ContaBancaria` em contextos que esperavam o comportamento de saque, quebrando a expectativa de substituição sem alterar o comportamento correto do programa.

#### Código Antes
**ContaBancaria**
```java
package LSolid.Exemplo2;

public class ContaBancaria {
    protected double saldo;

    public void depositar(double valor) {
        saldo += valor;
    }

    public void sacar(double valor) {
        saldo -= valor;
    }

    public double getSaldo() {
        return saldo;
    }
}
```

**ContaPoupanca**
```java
package LSolid.Exemplo2;

public class ContaPoupanca extends ContaBancaria {
    @Override
    public void sacar(double valor) {
        throw new UnsupportedOperationException("Resgate não é permitido direto.");
    }
}
```
Solução Aplicada

Para corrigir a violação do LSP, foram introduzidas interfaces específicas para separar os comportamentos esperados:

- **ContaBasica: Define operações básicas (depósito e consulta de saldo).**
- **ContaComSaque: Estende ContaBasica para contas que permitem saques.**
- **ContaBancaria implementa ContaComSaque, enquanto ContaPoupanca implementa apenas ContaBasica, removendo a herança problemática.**

Código Depois
### Interface ContaBasica
```java
package LSolid.Exemplo2;

public interface ContaBasica {
    void depositar(double valor);
    double getSaldo();
}
```
### Interface ContaComSaque
```java
package LSolid.Exemplo2;

public interface ContaComSaque extends ContaBasica {
    void sacar(double valor);
}
```
### ContaBancaria
```java
package LSolid.Exemplo2;

public class ContaBancaria implements ContaComSaque {
    protected double saldo;

    @Override
    public void depositar(double valor) {
        saldo += valor;
    }

    @Override
    public void sacar(double valor) {
        saldo -= valor;
    }

    @Override
    public double getSaldo() {
        return saldo;
    }
}
```
### ContaPoupanca
```java
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
```
## Justificativa

- **Substituição Garantida: ContaBancaria pode ser usada onde ContaComSaque é esperado, e ContaPoupanca onde ContaBasica é esperado, sem exceções inesperadas.**
- **Separação de Comportamentos: A remoção da herança evita que subclasses violem o contrato da superclasse.**
- **Flexibilidade: O sistema agora suporta diferentes tipos de contas sem comprometer a integridade do LSP.**
