package br.com.alura.screenmatch.Exercicios.EvoluindoProjetoScreenMatch;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;

public class Exercicio01 {
    public static void main(String[] args) {

        //1 - Imagine que você tem uma lista de strings. Algumas das strings são números, mas outras não. Queremos
        // converter a lista de strings para uma lista de números. Se a conversão falhar, você deve ignorar o valor.
        // Por exemplo, na lista a seguir, a saída deve ser [10, 20]:
        System.out.println("\nExercício 01");
        List<String> input = Arrays.asList("10", "abc", "20", "30x");

        input.stream()
                .map(str -> {try {
                return Optional.of(Integer.parseInt(str));
                } catch (NumberFormatException e) {
                    return Optional.<Integer>empty();
                }
                })
                .filter(Optional::isPresent)
                .map(Optional::get).toList()
                .forEach(System.out::println);

        //2 - Implemente um método que recebe um número inteiro dentro de um Optional. Se o número estiver presente e
        // for positivo, calcule seu quadrado. Caso contrário, retorne Optional.empty.
        System.out.println("\nExercicio 02");

        System.out.println(processaNumero(Optional.of(5))); // Saída: Optional[25]
        System.out.println(processaNumero(Optional.of(-3))); // Saída: Optional.empty
        System.out.println(processaNumero(Optional.empty())); // Saída: Optional.empty

        //3 - Implemente um método que recebe uma String representando um nome completo separado por espaços. O método
        // deve retornar o primeiro e o último nome após remover os espaços desnecessários.
        System.out.println("\nExercício 03");

        System.out.println(obterPrimeiroEUltimoNome("  João Carlos Silva   ")); // Saída: "João Silva"
        System.out.println(obterPrimeiroEUltimoNome("Maria   ")); // Saída: "Maria"
        System.out.println(obterPrimeiroEUltimoNome("Thiago Koiti Nozaki Ueda  "));

        // 4 - Implemente um método que verifica se uma frase é um palíndromo. Um palíndromo é uma palavra/frase que,
        // quando lida de trás pra frente, é igual à leitura normal. Um exemplo:
        System.out.println("\nExercício 04");

        System.out.println(ehPalindromo("A base do teto desaba")); // Saída: true
        System.out.println(ehPalindromo("Java")); // Saída: false

        // 5 - Implemente um método que recebe uma lista de e-mails (String) e retorna uma nova lista onde cada
        // e-mail está convertido para letras minúsculas.
        System.out.println("\nEnercício 05");
        List<String> emails = Arrays.asList("TESTE@EXEMPLO.COM", "exemplo@Java.com ", "Usuario@teste.Com");
        System.out.println(converterEmails(emails));
        // Saída: ["teste@exemplo.com", "exemplo@java.com", "usuario@teste.com"]

        // Exercicio 06
        System.out.println("\nExercicio 06");
        System.out.println(Mes.FEVEREIRO.getNumeroDeDias()); // 28
        System.out.println(Mes.JULHO.getNumeroDeDias()); // 31

        // Exercício 07
        System.out.println("\nExercicio 07");
        System.out.println(Moeda.DOLAR.converterPara(100)); // 19.60 (aproximado)
        System.out.println(Moeda.EURO.converterPara(100)); // 18.18 (aproximado)

        // Exercício 08
        System.out.println("\nExercicio 08");
        System.out.println(CodigoErro.NOT_FOUND.getCodigo()); // 404
        System.out.println(CodigoErro.BAD_REQUEST.getDescricao()); // Requisição inválida

    }
    // Exercício 02
    public static Optional<Integer> processaNumero(Optional<Integer> numero) {
        // Implementar aqui
        return  numero.filter(n -> n >0).map(n -> n * n);
    }

    // Exercício 03
    public static String obterPrimeiroEUltimoNome(String nomeCompleto) {
        // Implementar aqui
        String[] nomes = nomeCompleto.trim().split("\\s+");
        if(nomes.length == 1) {
            return nomes[0];
        }
        return nomes[0] + " " + nomes[nomes.length -1];
    }

    // Exercício 04
    public static boolean ehPalindromo(String palavra) {
        // Implementar aqui
        String semEspaco = palavra.replace(" ", "").toLowerCase();
        return new StringBuilder(semEspaco).reverse().toString().equalsIgnoreCase(semEspaco);
    }

    // Exercício 05
    public static List<String> converterEmails(List<String> emails) {
        // Implementar aqui
        return emails.stream()
                .map(email -> email.trim().toLowerCase())
                .toList();
    }

    // 6 - Crie um enum Mes que represente os meses do ano. Adicione um método que retorna o número de dias de
    // cada mês, considerando anos não bissextos.
    public enum Mes {
        JANEIRO(31), FEVEREIRO(28), MARCO(31), ABRIL(30),
        MAIO(31), JUNHO(30), JULHO(31), AGOSTO(31),
        SETEMBRO(30), OUTUBRO(31), NOVEMBRO(30), DEZEMBRO(31);

        private final int dias;

        Mes(int dias) {
            this.dias = dias;
        }

        public int getNumeroDeDias() {
            return dias;
        }
    }

    // 7 - Crie um enum Moeda com valores como DOLAR, EURO, REAL. Cada moeda deve ter uma taxa de conversão para reais.
    // Adicione um método que recebe um valor em reais e retorna o valor convertido para a moeda.
    public enum Moeda {
        // Definir os valores
        DOLAR(5.10),
        EURO(5.50),
        REAL(1.0);

        private final double taxa;
        Moeda(double taxa) {
            this.taxa = taxa;
        }

        public double converterPara(double valorEmReais) {
            // Implementar aqui
            return valorEmReais / taxa;
        }
    }

    // 8 - Crie um enum CodigoErro com valores de erros HTTP, como NOT_FOUND, BAD_REQUEST, INTERNAL_SERVER_ERROR.
    // Cada valor deve ter um código numérico e uma descrição associados. Adicione métodos para acessar o código e a
    // descrição. Dica: consulte o site https://http.cat/ para conhecer os vários erros HTTP e conseguir descrevê-los melhor.

    public enum CodigoErro {
        // Definir os valores
        NOT_FOUND(404, "Recurso não encontrado"),
        BAD_REQUEST(400, "Requisição inválida"),
        INTERNAL_SERVER_ERROR(500, "Erro interno do servidor");

        private final int codigo;
        private final String descricao;

        CodigoErro(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }

        public int getCodigo() {
            // Implementar aqui
            return codigo;
        }

        public String getDescricao() {
            // Implementar aqui
            return descricao;
        }
    }

}
