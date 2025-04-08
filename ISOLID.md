# Relatório SOLID - ISOLID (Princípio da Segregação de Interfaces)

## Pacote: ISolid.Exemplo2

### Problema Identificado
O código original violava o Princípio da Segregação de Interfaces (ISP) porque a interface `Veiculo` era muito ampla, forçando a classe `Carro` a implementar métodos que não usava (`voar` e `navegar`). Isso resultava em exceções (`UnsupportedOperationException`) e acoplamento desnecessário, contrariando o ISP, que exige que os clientes não dependam de interfaces que não utilizam.

#### Código Antes
**Veiculo**
```java
package ISolid.Exemplo2;

public interface Veiculo {
    void dirigir();
    void voar();
    void navegar();
}
```
### Carro

```java
package ISolid.Exemplo2;

public class Carro implements Veiculo {
    @Override
    public void dirigir() {
        System.out.println("Carro está dirigindo na estrada...");
    }

    @Override
    public void voar() {
        throw new UnsupportedOperationException("Carro não voa!");
    }

    @Override
    public void navegar() {
        throw new UnsupportedOperationException("Carro não navega!");
    }
}
```
Solução Aplicada

Para corrigir a violação do ISP, a interface Veiculo foi dividida em três interfaces específicas:

  - **Dirigivel: Para veículos que dirigem.**
  - **Voador: Para veículos que voam.**
  - **Navegavel: Para veículos que navegam. A classe Carro agora implementa apenas Dirigivel, refletindo seu comportamento real.**

## Código Depois
### Interface Dirigivel

```java
package ISolid.Exemplo2;

public interface Dirigivel {
    void dirigir();
}
```
### Interface Voador
```java
package ISolid.Exemplo2;

public interface Voador {
    void voar();
}
```
### Interface Navegavel
```java
package ISolid.Exemplo2;

public interface Navegavel {
    void navegar();
}
```
### Carro
```java
package ISolid.Exemplo2;

public class Carro implements Dirigivel {
    @Override
    public void dirigir() {
        System.out.println("Carro está dirigindo na estrada...");
    }
}
```

Justificativa
    
  - **Segregação de Interfaces: Interfaces menores e específicas garantem que as classes implementem apenas o que precisam.**
  - **Eliminação de Exceções Desnecessárias: Carro não é mais forçado a lidar com voar ou navegar, evitando código "falso".**
  - **Flexibilidade: Outros veículos (ex.: Aviao, Barco) podem combinar interfaces conforme necessário, mantendo o sistema modular.**
