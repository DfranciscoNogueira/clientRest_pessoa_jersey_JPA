package br.com.diego.programa;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.diego.model.Pessoa;
import br.com.diego.service.ServiceClient;

public class App {
    
    public static void main(String[] args) {
        
        String opcao = JOptionPane.showInputDialog(null, "Escolha uma opção: \n" + " 1-CADASTRAR | 2-CONSULTAR | 3-EXCLUIR | 4-ALTERAR | 5-LISTAR TODOS", "OPÇÕES", JOptionPane.PLAIN_MESSAGE);

        if (opcao == null)
            System.exit(0);

        switch (Integer.parseInt(opcao)) {
        case 1:
            cadastrar();
            break;
        case 2:
            consultar();
            break;
        case 3:
            excluir();
            break;
        case 4:
            alterar();
            break;
        case 5:
            listarTodos();
        default:
            JOptionPane.showMessageDialog(null, "Opção inválida!");
            main(null);
            break;
        }
    }
        
    public static void cadastrar() {

        ServiceClient client = new ServiceClient();

        String nome = JOptionPane.showInputDialog(null, "Nome:", "Novo Cadastro", JOptionPane.PLAIN_MESSAGE);
        String sexo = JOptionPane.showInputDialog(null, "Sexo: (M ou F)", "Novo Cadastro", JOptionPane.PLAIN_MESSAGE);

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(nome);
        pessoa.setSexo(sexo);

        String resultado = client.cadastrarPessoa(pessoa);

        JOptionPane.showMessageDialog(null, resultado);

        main(null);
    }

    public static void consultar() {

        ServiceClient client = new ServiceClient();

        String codigo = JOptionPane.showInputDialog(null, "Informe o código para consulta:", "Consultar", JOptionPane.PLAIN_MESSAGE);

        Pessoa pessoa = client.buscarPessoaPorId(Integer.parseInt(codigo));

        if (pessoa == null) {

            JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
            main(null);

        } else {

            String resultado = null;

            resultado = "Código: " + String.valueOf(pessoa.getId()) + "\n";
            resultado += "Nome:   " + pessoa.getNome() + "\n";
            resultado += "Sexo:   " + pessoa.getSexo();

            JOptionPane.showMessageDialog(null, resultado);

            main(null);
        }
    }

    public static void excluir() {

        ServiceClient client = new ServiceClient();

        String codigo = JOptionPane.showInputDialog(null, "Informe o código para excluir:", "Excluir", JOptionPane.PLAIN_MESSAGE);

        String resultado = client.excluirPessoa(Integer.parseInt(codigo));

        JOptionPane.showMessageDialog(null, resultado);

        main(null);
    }

    public static void alterar() {

        ServiceClient client = new ServiceClient();

        String codigo = (String) JOptionPane.showInputDialog(null, "Informe o código para alteração:", "Alterar", JOptionPane.PLAIN_MESSAGE);

        Pessoa pessoa = client.buscarPessoaPorId(Integer.parseInt(codigo));

        if (pessoa == null) {
            JOptionPane.showMessageDialog(null, "Pessoa não encontrada!");
            // chamando o metodo principal para começar a executar as opcoes novamente
            main(null);
        } else {

            String nome = (String) JOptionPane.showInputDialog(null, "Nome:", "Alterar registro - Código:" + pessoa.getId(), JOptionPane.PLAIN_MESSAGE, null, null, pessoa.getNome());
            String sexo = (String) JOptionPane.showInputDialog(null, "Sexo:", "Alterar registro - Código:" + pessoa.getId(), JOptionPane.PLAIN_MESSAGE, null, null, pessoa.getSexo());

            // atualizando os dados da pessoa que consultamos
            pessoa.setNome(nome);
            pessoa.setSexo(sexo);
            String resultado = client.alterarPessoa(pessoa);

            JOptionPane.showMessageDialog(null, resultado);

            // chamando o metodo principal para começar a executar as opcoes novamente
            main(null);
        }
    }

    public static void listarTodos() {

        // declarando o objeto da classe que acessa o serviço rest
        ServiceClient client = new ServiceClient();

        List<Pessoa> pessoas = client.buscarTodasPessoas();

        StringBuilder stringBuiderDetalhesPessoa = new StringBuilder();

        for (Pessoa pessoa : pessoas) {
            stringBuiderDetalhesPessoa.append("Código: ");
            stringBuiderDetalhesPessoa.append(pessoa.getId());
            stringBuiderDetalhesPessoa.append(" Nome: ");
            stringBuiderDetalhesPessoa.append(pessoa.getNome());
            stringBuiderDetalhesPessoa.append(" Sexo: ");
            stringBuiderDetalhesPessoa.append(pessoa.getSexo());
            stringBuiderDetalhesPessoa.append("\n\n");
        }

        JOptionPane.showMessageDialog(null, stringBuiderDetalhesPessoa.toString());

        // chamando o metodo principal para começar a executar as opcoes novamente
        main(null);
    }

}