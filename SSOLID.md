
# Relatório SOLID - SSOLID (Princípio da Responsabilidade Única)

## Pacote: SSolid.Exemplo2

### Problema Identificado
A classe `ProcessadorEncomendas` original violava o Princípio da Responsabilidade Única (SRP) ao ter duas responsabilidades distintas:
1. Coletar entrada do usuário e calcular o frete.
2. Salvar os dados em um arquivo (`encomendas.txt`).

Isso significa que mudanças na lógica de cálculo ou no formato de salvamento exigiriam alterações na mesma classe, aumentando a complexidade e o risco de erros.

#### Código Antes
```java
package SSolid.Exemplo2;

import java.io.*;
import java.util.Scanner;

public class ProcessadorEncomendas {
    public void processar() {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Digite o ID da encomenda: ");
            String idEncomenda = sc.nextLine();
            System.out.println("Digite o peso (em kg): ");
            double peso = sc.nextDouble();
            double valorFrete = peso * 10;
            System.out.println("Valor do frete calculado: " + valorFrete);
            salvarEmArquivo(idEncomenda, valorFrete);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarEmArquivo(String idEncomenda, double valorFrete) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("encomendas.txt", true))) {
            bw.write("ID: " + idEncomenda + " - Frete: " + valorFrete);
            bw.newLine();
            System.out.println("Salvo no arquivo encomendas.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```
## Solução Aplicada

Para corrigir a violação do SRP, as responsabilidades foram separadas em duas classes:
- `ProcessadorEncomendas`: Lida com entrada do usuário e cálculo do frete.
- `GravadorEncomendas`: Lida com a persistência dos dados em arquivo.

### Código Depois

#### ProcessadorEncomendas
```java
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
```

## GravadorEncomendas

```java
package SSolid.Exemplo2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GravadorEncomendas {
    public void salvar(String idEncomenda, double valorFrete) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("encomendas.txt", true))) {
            bw.write("ID: " + idEncomenda + " - Frete: " + valorFrete);
            bw.newLine();
            System.out.println("Salvo no arquivo encomendas.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Justificativa

- **Responsabilidade Única**: Cada classe agora tem uma única razão para mudar.
- **Manutenção**: Alterações no cálculo do frete ou na persistência podem ser feitas isoladamente.
- **Reusabilidade**: `GravadorEncomendas` pode ser usado por outras classes, se necessário.

## Como Usar

- Copie esse código Markdown e cole no seu arquivo (ex.: `README.md`) no repositório público.
- Se quiser incluir a parte anterior ("Problema Identificado"), é só pedir que eu a converto também!
- Para os outros pacotes (`OSOLID`, `LSOLID`, `ISOLID`), você pode seguir o mesmo formato quando refatorá-los.

Está pronto para subir ao GitHub assim! Se precisar de mais ajustes, é só avisar.

---

### Observações
- Mantive a estrutura exatamente como você passou, incluindo o título "GravadorEncomendas" e a seção "Justificativa".
- O bloco de código Java foi corretamente delimitado com ```java para destacar a sintaxe no Markdown.
- A seção "Como Usar" foi preservada como instrução final, formatada em Markdown.
